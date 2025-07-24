package controller;

import entity.Staffs;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Vector;
import model.DAOStaffs;

@WebServlet(name = "StaffController", urlPatterns = {"/StaffControllerURL"})
public class StaffController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOStaffs dao = new DAOStaffs();
        String service = request.getParameter("service");
        HttpSession session = request.getSession(true);
        if (service == null) {
            service = "listAll";
        }
        
        if (service.equals("listAll")) {
            //call model
            Vector<Staffs> vector = dao.getAll("SELECT * FROM staffs");
            //send values to view (JSP)
            request.setAttribute("data", vector);
            request.setAttribute("pageTitle", "Staffs");
            request.setAttribute("tableTitle", "List of Staff");
            //select view
            RequestDispatcher dispth = request.getRequestDispatcher("jsp/StaffManage.jsp");
            //run, show
            dispth.forward(request, response);
        }
        if(service.equals("login")){
                 //String check = (String)session.getAttribute("username");
                 String username = request.getParameter("username");
                 String password = request.getParameter("pass");
                 boolean flag=dao.login(username, password);
                 if(flag){
                     session.setAttribute("username", username);
//                     out.print("OK");
                    RequestDispatcher dis = request.getRequestDispatcher("/index.html");
                    dis.forward(request, response);
                 }
            }
        if (service.equals("update")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                int id = Integer.parseInt(request.getParameter("id"));
                Vector<Staffs> vector = dao.getAll("SELECT * FROM staffs WHERE staff_id = " + id);
                Staffs st = vector.get(0);
                request.setAttribute("st", st);
                dispth(request, response, "jsp/updateStaff.jsp");
            } else {
                int staff_id = Integer.parseInt(request.getParameter("staff_id"));
                String fName = request.getParameter("fName");
                String lName = request.getParameter("lName");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                int active = Integer.parseInt(request.getParameter("active"));
                int store_id = Integer.parseInt(request.getParameter("store_id"));
                int manager_id = Integer.parseInt(request.getParameter("manager_id"));

                Staffs st = new Staffs(staff_id, fName, lName, email, phone, active, store_id, manager_id);
                int n = dao.updateStaff(st);
                response.sendRedirect("StaffControllerURL");
            }
        }
        if(service.equals("delete")){
            int staff_id = Integer.parseInt(request.getParameter("id"));
            dao.removeStaff(staff_id);
            response.sendRedirect("StaffControllerURL");
        }
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet StaffController</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet StaffController at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
        }
    }

    public void dispth(HttpServletRequest request, HttpServletResponse response, String url)
            throws ServletException, IOException {
        RequestDispatcher dispth = request.getRequestDispatcher(url);
        dispth.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

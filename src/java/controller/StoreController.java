package controller;

import entity.Stores;
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
import model.DAOStores;

@WebServlet(name = "StoreController", urlPatterns = {"/StoreControllerURL"})
public class StoreController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        DAOStores dao = new DAOStores();
        String service = request.getParameter("service");
        HttpSession session = request.getSession(true);
        if (service == null) {
            service = "listAll";
        }
        if (service.equals("listAll")) {
            //call model
            Vector<Stores> vector = dao.getAll("SELECT * FROM stores");
            //send values to view (JSP)
            request.setAttribute("data", vector);
            request.setAttribute("pageTitle", "Stores");
            request.setAttribute("tableTitle", "List of Stores");
            //select view
            RequestDispatcher dispth = request.getRequestDispatcher("jsp/StoreManage.jsp");
            //run and show
            dispth.forward(request, response);
        }
        if(service.equals("update")){
            String submit = request.getParameter("submit");
            if(submit == null){
                int id = Integer.parseInt(request.getParameter("id"));
                Vector<Stores> vector = dao.getAll("SELECT * FROM stores WHERE store_id = " + id);
                Stores str = vector.get(0);
                request.setAttribute("str", str);
                dispth(request, response, "jsp/updateStore.jsp");
            } else{
                int store_id = Integer.parseInt(request.getParameter("store_id"));
                String store_name = request.getParameter("store_name");
                String phone = request.getParameter("phone");
                String email = request.getParameter("email");
                String street = request.getParameter("street");
                String city = request.getParameter("city");
                String state = request.getParameter("state");
                String zip_code = request.getParameter("zip_code");

                Stores st = new Stores(store_id, store_name, phone, email, street, city, state, zip_code);
                dao.updateStore(st);
                response.sendRedirect("StoreControllerURL");
            }
        }
        if(service.equals("delete")){
            int store_id = Integer.parseInt(request.getParameter("id"));
            dao.removeStore(store_id);
            response.sendRedirect("StoreControllerURL");
        }
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet StoreController</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet StoreController at " + request.getContextPath() + "</h1>");
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

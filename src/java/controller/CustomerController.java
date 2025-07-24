package controller;

import entity.Customers;
import entity.Orders;
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
import model.DAOCustomers;
import model.DAOOrders;

@WebServlet(name = "CustomerController", urlPatterns = {"/CustomerControllerURL"})
public class CustomerController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOCustomers dao = new DAOCustomers();
        String service = request.getParameter("service");
        HttpSession session = request.getSession(true);
        if (service == null) {
            service = "listAll";
        }
        if (service.equals("listAll")) {
            //call model
            Vector<Customers> vector = dao.getAll("SELECT * FROM customers");
            //send values to view (JSP)
            request.setAttribute("data", vector);
            request.setAttribute("pageTitle", "Customers");
            request.setAttribute("tableTitle", "List of Customers");
            //select view
            RequestDispatcher dispth = request.getRequestDispatcher("jsp/CustomerManage.jsp");
            //run, show
            dispth.forward(request, response);
        }
        if (service.equals("update")) {
            //check submit
            String submit = request.getParameter("submit");
            if (submit == null) { //show form
                //get Customer with ID
                int id = Integer.parseInt(request.getParameter("id"));
                Vector<Customers> vector = dao.getAll("SELECT * FROM customers WHERE customer_id = " + id);
                Customers cust = vector.get(0);
                //show Customer by form
                request.setAttribute("cust", cust);
                dispth(request, response, "jsp/updateCustomer.jsp");
            } else { //update
                int id = Integer.parseInt(request.getParameter("cid"));
                String fName = request.getParameter("fName");
                String lName = request.getParameter("lName");
                String phone = request.getParameter("phone");
                String email = request.getParameter("email");
                String street = request.getParameter("street");
                String city = request.getParameter("city");
                String state = request.getParameter("state");
                String zip_code = request.getParameter("zip_code");

                Customers cust = new Customers(id, fName, lName, phone, email, street, city, state, zip_code);
                int n = dao.updateCustomer(cust);
                response.sendRedirect("CustomerControllerURL");
            }
        }
        if(service.equals("delete")){
                int customer_id = Integer.parseInt(request.getParameter("id"));
                dao.removeCustomer(customer_id);
                response.sendRedirect("CustomerControllerURL");
            }
        if (service.equals("showOrder")) {
            
            String customer_id = request.getParameter("id");
            //call model
            DAOOrders dao1 = new DAOOrders();
            Vector<Orders> vector1 = dao1.getAll("SELECT * FROM orders WHERE customer_id = " + customer_id);
            //send values to view (JSP)
            request.setAttribute("data", vector1);
            request.setAttribute("pageTitle", "Orders");
            request.setAttribute("tableTitle", "List of Orders");
            //select view
            RequestDispatcher dispth = request.getRequestDispatcher("jsp/showOrder.jsp");
            //run, show
            dispth.forward(request, response);
        }
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet CustomerController</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet CustomerController at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
        }
    }

    public void dispth(HttpServletRequest request, HttpServletResponse response, String url)
            throws ServletException, IOException {
        RequestDispatcher dispth = request.getRequestDispatcher(url);
        //run, show
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

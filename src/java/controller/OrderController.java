package controller;

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
import model.DAOOrders;

@WebServlet(name = "OrderController", urlPatterns = {"/OrderControllerURL"})
public class OrderController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOOrders dao = new DAOOrders();
        String service = request.getParameter("service");
        HttpSession session = request.getSession(true);
        if (service == null) {
            service = "listAll";
        }
        if (service.equals("listAll")) {
            //call model
            Vector<Orders> vector = dao.getAll("SELECT * FROM orders");
            //send values to view (JSP)
            request.setAttribute("data", vector);
            request.setAttribute("pageTitle", "Orders");
            request.setAttribute("tableTitle", "List of Orders");
            //select view
            RequestDispatcher dispth = request.getRequestDispatcher("jsp/OrderManage.jsp");
            //run, show
            dispth.forward(request, response);
        }
        if(service.equals("update")){
            String submit = request.getParameter("submit");
            if(submit == null){
                int id = Integer.parseInt(request.getParameter("id"));
                Vector<Orders> vector = dao.getAll("SELECT * FROM orders WHERE order_id = " + id);
                Orders o = vector.get(0);
                request.setAttribute("o", o);
                dispth(request, response, "jsp/updateOrder.jsp");
            } else{
                int order_id = Integer.parseInt(request.getParameter("order_id"));
                int customer_id = Integer.parseInt(request.getParameter("customer_id"));
                int status = Integer.parseInt(request.getParameter("status"));
                String o_date = request.getParameter("oDate");
                String r_date = request.getParameter("rDate");
                String s_date = request.getParameter("sDate");
                int store_id = Integer.parseInt(request.getParameter("store_id"));
                int staff_id = Integer.parseInt(request.getParameter("staff_id"));

                Orders o = new Orders(order_id, customer_id, status, o_date, r_date, s_date, store_id, staff_id);
                int n = dao.updateOrder(o);
                response.sendRedirect("OrderControllerURL");
            }
        }
        if(service.equals("delete")){
            int order_id = Integer.parseInt(request.getParameter("id"));
            dao.removeOrder(order_id);
            response.sendRedirect("OrderControllerURL");
        }
        if (service.equals("insert")) {
            // check sumit
            String submit = request.getParameter("submit");
            if (submit == null) {
                dispth(request, response, "jsp/insertProduct.jsp");
            } else {
                //get data
                int order_id = Integer.parseInt(request.getParameter("order_id"));
                int customer_id = Integer.parseInt(request.getParameter("customer_id"));
                int status = Integer.parseInt(request.getParameter("status"));
                String o_date = request.getParameter("oDate");
                String r_date = request.getParameter("rDate");
                String s_date = request.getParameter("sDate");
                int store_id = Integer.parseInt(request.getParameter("store_id"));
                int staff_id = Integer.parseInt(request.getParameter("staff_id"));
                Orders o = new Orders(order_id, customer_id, status, o_date, r_date, s_date, store_id, staff_id);

                int n = dao.insertOrder(o);
                response.sendRedirect("OrderControllerURL");
            }
        }
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet OrderController</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet OrderController at " + request.getContextPath() + "</h1>");
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

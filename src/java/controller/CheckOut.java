/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAOOrders;
import entity.Orders;
import jakarta.servlet.http.HttpSession;
import entity.ProductCart;
import jakarta.servlet.RequestDispatcher;
import java.util.Random;
import model.DAOOrderItems;
import entity.OrderItems;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CheckOut", urlPatterns = {"/CheckOut"})
public class CheckOut extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOOrders dao = new DAOOrders();
        DAOOrderItems dao1 = new DAOOrderItems();
        HttpSession session = request.getSession(true);
        Random rand = new Random();
        String service = request.getParameter("service");

        try (PrintWriter out = response.getWriter()) {

            // check sumit
            String submit = request.getParameter("submit");
            if (submit == null) {
                dispath(request, response, "jsp/CheckOut.jsp");
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
                java.util.Enumeration em = session.getAttributeNames();
                while (em.hasMoreElements()) {
                    String key = em.nextElement().toString(); //get key

                    if (!key.equals("username")) {

                        ProductCart pro = (ProductCart) session.getAttribute(key); //get value
                        //get data

                        int item_id = rand.nextInt(10 - 1 + 1) + 1;
                        int product_id = pro.getProduct_id();
                        int quantity = pro.getQuantity();
                        double list_price = pro.getList_price();
                        double discount = 0;
                        OrderItems odi = new OrderItems(order_id, item_id, product_id,
                                quantity, list_price, discount);
                        int m = dao1.insertOrderItem(odi);
                        request.setAttribute("order_id", order_id);
                        session.invalidate();

                        RequestDispatcher dispth
                                = request.getRequestDispatcher("/jsp/CheckOut.jsp");
                        //run, show
                        dispth.forward(request, response);
                    }

                }
            }

            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CheckOut</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckOut at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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

    public void dispath(HttpServletRequest request, HttpServletResponse response, String url)
            throws ServletException, IOException {
        RequestDispatcher dispth = request.getRequestDispatcher(url);
        dispth.forward(request, response);
    }
}

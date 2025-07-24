/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.ProductCart;
import entity.Products;
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
import model.DAOProducts;

/**
 *
 * @author DELL
 */
@WebServlet(name = "Cart", urlPatterns = {"/Cart"})
public class Cart extends HttpServlet {

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
        HttpSession session = request.getSession(true);
        String service = request.getParameter("service");
        DAOProducts dao = new DAOProducts();
        if (service.equals("add2cart")) {
            //check first?
            String id = request.getParameter("id");
            ProductCart proCart
                    = (ProductCart) session.getAttribute(id);
            if (proCart == null) { // first --> quantity set to 1
                //get product from DB
                proCart = new ProductCart();
                Vector<Products> vector
                        = dao.getAll("select * from Products where product_id=" + id);
                Products pro = vector.get(0);
                proCart.setProduct_id(pro.getProduct_id());
                proCart.setProduct_name(pro.getProduct_name());
                proCart.setList_price(pro.getList_price());
                proCart.setQuantity(1);
                session.setAttribute(id, proCart);
            } else {// product exist
                ProductCart oldProCart = (ProductCart) session.getAttribute(id);
                oldProCart.setQuantity(oldProCart.getQuantity() + 1);
                session.setAttribute(id, oldProCart);
            }
            response.sendRedirect("ProductControllerURL");
        }
        if (service.equals("showCart")) {
            //select view
            RequestDispatcher dispth
                    = request.getRequestDispatcher("/jsp/ShowCart.jsp");
            //run, show
            dispth.forward(request, response);
        }
        if (service.equals("removeAll")){
            java.util.Enumeration em = session.getAttributeNames();
            while (em.hasMoreElements()) {
                String key = em.nextElement().toString(); //get key
                
                if (!key.equals("username")) {
                    
                    ProductCart pro=(ProductCart)session.getAttribute(key); //get value
                    String product_id = String.valueOf(pro.getProduct_id()) ;
                    session.removeAttribute(product_id);
                    
                }

            }
            response.sendRedirect("ProductControllerURL");
        }
        if (service.equals("remove")) {
            java.util.Enumeration em = session.getAttributeNames();
//        getkeys()
            while (em.hasMoreElements()) {
                String key = em.nextElement().toString(); //get key
                String product_id = request.getParameter("id");
                if (!key.equals("username")&& key.equals(product_id)) {
                    
                    ProductCart pro=(ProductCart)session.getAttribute(key); //get value
                    session.removeAttribute(product_id);
                    response.sendRedirect("Cart?service=showCart");
                }

            }
        }
        

//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet Cart</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet Cart at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
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
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
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
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
        
            () {
        return "Short description";
        }// </editor-fold>

    }

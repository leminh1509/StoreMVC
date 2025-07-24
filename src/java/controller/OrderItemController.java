package controller;

import entity.OrderItems;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOOrderItems;

@WebServlet(name = "OrderItemController", urlPatterns = {"/OrderItemControllerURL"})
public class OrderItemController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOOrderItems dao = new DAOOrderItems();
        String service = request.getParameter("service");
        if(service==null)service="listAll";
        if(service.equals("listAll")){
            //call model
            Vector<OrderItems> vector = dao.getAll("SELECT * FROM order_items");
            //send values to view (JSP)
            request.setAttribute("data", vector);
            request.setAttribute("pageTitle", "Order Items");
            request.setAttribute("tableTitle", "List of Order Items");
            //select view
            RequestDispatcher dispth = request.getRequestDispatcher("jsp/OrderItemManage.jsp");
            //run and show
            dispth.forward(request, response);
        }
        if(service.equals("update")){
            String submit = request.getParameter("submit");
            if(submit == null){
                int id1 = Integer.parseInt(request.getParameter("id1"));
                int id2 = Integer.parseInt(request.getParameter("id2"));
                Vector<OrderItems> vector = dao.getAll("SELECT * FROM order_items WHERE order_id = "+id1+" AND item_id = " + id2);
                OrderItems oi = vector.get(0);
                request.setAttribute("oi", oi);
                dispth(request, response, "jsp/updateOrderItem.jsp");
            }
            else{
                int order_id = Integer.parseInt(request.getParameter("order_id"));
                int item_id = Integer.parseInt(request.getParameter("item_id"));
                int product_id = Integer.parseInt(request.getParameter("product_id"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                double list_price = Double.parseDouble(request.getParameter("list_price"));
                double discount = Double.parseDouble(request.getParameter("discount"));

                OrderItems oi = new OrderItems(order_id, item_id, product_id, quantity, list_price, discount);
                dao.updateOrderItem(oi);
                response.sendRedirect("OrderItemControllerURL");
            }
        }
        if(service.equals("delete")){
            int order_id = Integer.parseInt(request.getParameter("id1"));
                int item_id = Integer.parseInt(request.getParameter("id2"));
                dao.removeOrderItem(order_id, item_id);
                response.sendRedirect("OrderItemControllerURL");
        }
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet OrderItemController</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet OrderItemController at " + request.getContextPath() + "</h1>");
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

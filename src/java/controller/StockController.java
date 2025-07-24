package controller;

import entity.Stocks;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOStocks;

@WebServlet(name = "StockController", urlPatterns = {"/StockControllerURL"})
public class StockController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOStocks dao = new DAOStocks();
        String service = request.getParameter("service");
        if (service == null) {
            service = "listAll";
        }
        if (service.equals("listAll")) {
            //call model
            Vector<Stocks> vector = dao.getAll("SELECT * FROM stocks");
            //send values to view (JSP)
            request.setAttribute("data", vector);
            request.setAttribute("pageTitle", "Stocks");
            request.setAttribute("tableTitle", "List of Stocks");
            //select view
            RequestDispatcher dispth = request.getRequestDispatcher("jsp/StockManage.jsp");
            //run and show
            dispth.forward(request, response);
        }
        if (service.equals("update")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                int id1 = Integer.parseInt(request.getParameter("id1"));
                int id2 = Integer.parseInt(request.getParameter("id2"));
                Vector<Stocks> vector = dao.getAll("SELECT * FROM stocks WHERE store_id = " + id1 + " AND product_id = " + id2);
                Stocks stk = vector.get(0);
                request.setAttribute("stk", stk);
                dispth(request, response, "jsp/updateStock.jsp");
            } else {
                int store_id = Integer.parseInt(request.getParameter("store_id"));
                int product_id = Integer.parseInt(request.getParameter("product_id"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));

                Stocks stk = new Stocks(store_id, product_id, quantity);
                dao.updateStock(stk);
                response.sendRedirect("StockControllerURL");
            }
        }
        if (service.equals("delete")) {
            int store_id = Integer.parseInt(request.getParameter("id1"));
            int product_id = Integer.parseInt(request.getParameter("id2"));
            dao.removeStock(store_id, product_id);
            response.sendRedirect("StockControllerURL");
        }
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet StockController</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet StockController at " + request.getContextPath() + "</h1>");
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

package controller;

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

@WebServlet(name = "ProductController", urlPatterns = {"/ProductControllerURL"})
public class ProductController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        DAOProducts dao = new DAOProducts();
        String service = request.getParameter("service");
        HttpSession session = request.getSession(true);
        if (service == null) {
            service = "listAll";
        }
        if (service.equals("listAll")) {
            //call model
            Vector<Products> vector = dao.getAll("SELECT * FROM Products");
            //send values to view (JSP)
            request.setAttribute("data", vector);
            request.setAttribute("pageTitle", "Products");
            request.setAttribute("tableTitle", "List of Products");
            //select view
            RequestDispatcher dispth = request.getRequestDispatcher("jsp/ProductManage.jsp");
            //run, show
            dispth.forward(request, response);
        }
        if (service.equals("searchByCategory")){
            
        }
        if (service.equals("insert")) {
            // check sumit
            String submit = request.getParameter("submit");
            if (submit == null) {
                dispath(request, response, "jsp/insertProduct.jsp");
            } else {
                //get data
                int product_id = Integer.parseInt(request.getParameter("pid"));
                String product_name = request.getParameter("pname");
                int model_year = Integer.parseInt(request.getParameter("model"));
                double list_price = Double.parseDouble(request.getParameter("price"));
                String brand_name = request.getParameter("brand"),
                        category_name = request.getParameter("cate");
                Products pro = new Products(product_id, product_name, model_year,
                        list_price, brand_name, category_name);
                int n = dao.addProduct(pro);
                response.sendRedirect("ProductControllerURL");
            }
        }
        if (service.equals("update")) {
            //check submit
            String submit = request.getParameter("submit");
            if (submit == null) { //show form
                //get Product with ID
                int id = Integer.parseInt(request.getParameter("id"));
                Vector<Products> vector = dao.getAll("SELECT * FROM products WHERE product_id = " + id);
                Products pro = vector.get(0);
                //show Product by form
                request.setAttribute("pro", pro);
                dispath(request, response, "jsp/updateProduct.jsp");
            } else { //update
                int product_id = Integer.parseInt(request.getParameter("pid"));
                String product_name = request.getParameter("pname");
                int model_year = Integer.parseInt(request.getParameter("model"));
                double list_price = Double.parseDouble(request.getParameter("price"));
                String brand_name = request.getParameter("brand");
                String category_name = request.getParameter("category");

                Products pro = new Products(product_id, product_name, model_year, list_price, brand_name, category_name);
                int n = dao.updateProduct(pro);
                response.sendRedirect("ProductControllerURL");
            }
        }
        if (service.equals("delete")) {
            int product_id = Integer.parseInt(request.getParameter("id"));
            dao.removeProduct(product_id);
            response.sendRedirect("ProductControllerURL");
        }
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ProductController</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ProductController at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
        }
    }

    public void dispath(HttpServletRequest request, HttpServletResponse response, String url)
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

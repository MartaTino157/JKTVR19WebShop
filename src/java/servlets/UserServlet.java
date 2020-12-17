/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Customer;
import entity.Product;
import entity.Purchase;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.CustomerFacade;
import session.ProductFacade;
import session.PurchaseFacade;

/**
 *
 * @author Alice
 */
@WebServlet(name = "UserServlet", urlPatterns = {

    "/listProducts",
    
    "/makeDealForm",
    "/makeDeal"
})
public class UserServlet extends HttpServlet {
    @EJB
    private ProductFacade productFacade;
    @EJB
    private CustomerFacade customerFacade;
    @EJB
    private PurchaseFacade purchaseFacade;
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
        request.setCharacterEncoding("UTF-8");
        HttpSession  httpSession = request.getSession(false);
        if(httpSession == null){
            request.setAttribute("info", "Авторизуйтесь, пожалуйста!");
            request.getRequestDispatcher("/loginForm").forward(request, response);
            return;
        }
        User authUser = (User) httpSession.getAttribute("user");
        if(authUser == null){
            request.setAttribute("info", "Авторизуйтесь, пожалуйста!");
            request.getRequestDispatcher("/loginForm").forward(request, response);
            return;
        }
        String path = request.getServletPath();
        switch (path) {
            case "/listProducts":
                List<Product> listProducts = productFacade.findAll();
                request.setAttribute("listProducts", listProducts);
                request.getRequestDispatcher("/WEB-INF/listProducts.jsp").forward(request, response);
                break;
            case "/makeDealForm":
                listProducts = productFacade.findAll();
                request.setAttribute("listProducts", listProducts);
                Customer customer = customerFacade.find(authUser.getCustomer().getId());
                request.setAttribute("customer", customer);
                request.getRequestDispatcher("/WEB-INF/makeDealForm.jsp").forward(request, response);
                break;
            case "/makeDeal":
                String productId = request.getParameter("productId");
                Product product = productFacade.find(Long.parseLong(productId));
                String customerId = request.getParameter("customerId");
                customer = customerFacade.find(Long.parseLong(customerId));
                double residual;
                residual = customer.getBalance() - product.getPrice();
                if(residual <0){
                    request.setAttribute("info", "Недостаточно средств для покупки");
                    request.getRequestDispatcher("/WEB-INF/makeDealForm.jsp").forward(request, response);
                    break;
                }
                customer.setBalance(residual);
                Purchase purchase = new Purchase(customer, product, new GregorianCalendar().getTime());
                customerFacade.edit(customer);
                purchaseFacade.create(purchase);
                request.setAttribute("customerId", customerId);
                request.setAttribute("info", "Товар \""+product.getName() + "\" приобретен покупателем " + customer.getFirstname() + " " + customer.getLastname());
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            default:
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

}

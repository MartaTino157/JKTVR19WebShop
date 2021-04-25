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
import session.UserFacade;
import session.UserRolesFacade;

/**
 *
 * @author Alice
 */
@WebServlet(name = "UserServlet", urlPatterns = {
    "/profile",
    "/editCustomerForm",
    "/editCustomer",
    "/addMoneyForm",
    "/addMoney",
    "/makeDealForm",
    "/makeDeal"
})
public class CustomerServlet extends HttpServlet {
    @EJB
    private ProductFacade productFacade;
    @EJB
    private CustomerFacade customerFacade;
    @EJB
    private PurchaseFacade purchaseFacade;
    @EJB
    private UserRolesFacade userRolesFacade;
    @EJB
    private UserFacade userFacade;
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
        boolean isRole = userRolesFacade.isRole("CUSTOMER", authUser); 
        if(!isRole){
            request.setAttribute("info", "У вас нет прав для доступа!");
            request.getRequestDispatcher("/loginForm").forward(request, response);
            return;
        }
        String path = request.getServletPath();
        switch (path) {
            case "/profile":
                User user = userFacade.find(authUser.getId());
                request.setAttribute("user", user);
                request.getRequestDispatcher(LoginServlet.pathToJsp.getString("profile")).forward(request, response);
                break;
            case "/editCustomerForm":
                String customerId = request.getParameter("customerId");
                Customer customer = customerFacade.find(Long.parseLong(customerId));
                request.setAttribute("customer", customer);
                request.getRequestDispatcher(LoginServlet.pathToJsp.getString("editCustomer")).forward(request, response);
                break;
            case "/editCustomer":
                customerId = request.getParameter("customerId");
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                String phone = request.getParameter("phone");
                if("".equals(firstname) || firstname == null
                        ||"".equals(lastname) || lastname == null
                        ||"".equals(phone) || phone == null){
                    request.setAttribute("info", "Заполните все поля");
                    request.getRequestDispatcher("/editCustomerForm").forward(request, response);
                    break;
                } 
                customer = customerFacade.find(Long.parseLong(customerId));
                customer.setFirstname(firstname);
                customer.setLastname(lastname);
                customer.setPhone(phone);
                customerFacade.edit(customer);
                request.setAttribute("customerId", customerId);
                request.setAttribute("info", "Данные покупателя изменены");
                request.getRequestDispatcher("/profile").forward(request, response);
                break;
            case "/addMoneyForm":
                customerId = request.getParameter("customerId");
                customer = customerFacade.find(Long.parseLong(customerId));
                request.setAttribute("customer", customer);
                request.getRequestDispatcher(LoginServlet.pathToJsp.getString("addMoney")).forward(request, response);
                break;
            case "/addMoney":
                customerId = request.getParameter("customerId");
                String strMoney = request.getParameter("money");
                double index = strMoney.indexOf(",");
                if(index >= 0){
                    strMoney = strMoney.replace(",", ".");
                }
                if("".equals(strMoney) || strMoney == null){
                    request.setAttribute("money", strMoney);
                    request.setAttribute("info", "Вы нисколько не ввели");
                    request.getRequestDispatcher("/addMoneyForm").forward(request, response);
                    break;
                }
                customer = customerFacade.find(Long.parseLong(customerId));
                double money = Double.parseDouble(strMoney);
                double balance = money + customer.getBalance();
                customer.setBalance(balance);
                customerFacade.edit(customer);
                request.setAttribute("customerId", customerId);
                request.setAttribute("info", "Счет пополнен");
                request.getRequestDispatcher("/profile").forward(request, response);
                break;
            case "/makeDealForm":
                List<Product> listProducts = productFacade.findAll();
                request.setAttribute("listProducts", listProducts);
                customer = customerFacade.find(authUser.getCustomer().getId());
                request.setAttribute("customer", customer);
                request.getRequestDispatcher(LoginServlet.pathToJsp.getString("makeDeal")).forward(request, response);
                break;
            case "/makeDeal":
                String productId = request.getParameter("productId");
                Product product = productFacade.find(Long.parseLong(productId));
                customerId = request.getParameter("customerId");
                customer = customerFacade.find(Long.parseLong(customerId));
                double residual;
                residual = customer.getBalance() - product.getPrice();
                if(residual <0){
                    request.setAttribute("info", "Недостаточно средств для покупки");
                    request.getRequestDispatcher("/profile").forward(request, response);
                    break;
                }
                customer.setBalance(residual);
                Purchase purchase = new Purchase(customer, product, new GregorianCalendar().getTime());
                customerFacade.edit(customer);
                purchaseFacade.create(purchase);
                request.setAttribute("customerId", customerId);
                request.setAttribute("info", "Товар \""+product.getName() + "\" приобретен покупателем " + customer.getFirstname() + " " + customer.getLastname());
                request.getRequestDispatcher(LoginServlet.pathToJsp.getString("index")).forward(request, response);
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

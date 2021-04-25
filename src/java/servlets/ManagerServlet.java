/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Customer;
import entity.Product;
import entity.User;
import java.io.IOException;
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
import session.UserRolesFacade;

/**
 *
 * @author Alice
 */
@WebServlet(name = "MenegerServlet", urlPatterns = {
    
    "/editCustomerForm",
    "/editCustomer",
    "/addMoneyForm",
    "/addMoney",
    
    "/addProductForm",
    "/createProduct",
    "/editProductForm",
    "/editProduct"

})
public class ManagerServlet extends HttpServlet {
    @EJB
    private ProductFacade productFacade;
    @EJB
    private CustomerFacade customerFacade;
    @EJB
    private UserRolesFacade userRolesFacade;
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
        boolean isRole = userRolesFacade.isRole("MANAGER", authUser); 
        if(!isRole){
            request.setAttribute("info", "У вас нет прав для доступа!");
            request.getRequestDispatcher("/loginForm").forward(request, response);
            return;
        }
        String path = request.getServletPath();
        switch (path) {
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
                request.setAttribute("info", "Данные покупателя \"" + customer.getFirstname() + " " + customer.getLastname() + "\" изменены");
                request.getRequestDispatcher("/listCustomers").forward(request, response);
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
                request.setAttribute("info", "Счет клиента \"" + customer.getFirstname() + " " + customer.getLastname() + "\" пополнен");
                request.getRequestDispatcher("/listCustomers").forward(request, response);
                break;
            case "/addProductForm":
                request.getRequestDispatcher(LoginServlet.pathToJsp.getString("addProduct")).forward(request, response);
                break;
            case "/createProduct": 
                String name = request.getParameter("name");
                String country = request.getParameter("country");
                String strPrice = request.getParameter("price");
                Product product = productFacade.findByName(name);
                if(product != null){
                    request.setAttribute("info", "Такой товар уже существует!");
                    request.getRequestDispatcher("/addProductForm").forward(request, response);
                    break;
                }
                index = strPrice.indexOf(",");
                if(index >= 0){
                    strPrice = strPrice.replace(",", ".");
                }
                if("".equals(name) || name == null
                        ||"".equals(country) || country == null
                        ||"".equals(strPrice) || strPrice == null){
                    request.setAttribute("name", name);
                    request.setAttribute("country", country);
                    request.setAttribute("price", strPrice);
                    request.setAttribute("info", "Заполните все поля");
                    request.getRequestDispatcher("/addProductForm").forward(request, response);
                    break;
                } 
                double price = Double.parseDouble(strPrice);
                product = new Product(name, country, price);
                productFacade.create(product);
                request.setAttribute("info", "Данные товара \"" + product.getName() + "\" получены");
                request.getRequestDispatcher(LoginServlet.pathToJsp.getString("index")).forward(request, response);
                break;
            case "/editProductForm":
                String productId = request.getParameter("productId");
                product = productFacade.find(Long.parseLong(productId));
                request.setAttribute("product", product);
                request.getRequestDispatcher(LoginServlet.pathToJsp.getString("editProduct")).forward(request, response);
                break;
            case "/editProduct":
                productId = request.getParameter("productId");
                name = request.getParameter("name");
                country = request.getParameter("country");
                strPrice = request.getParameter("price");
                product = productFacade.findByName(name);
                if(product != null){
                    request.setAttribute("info", "Товар с таким именем уже существует!");
                    request.getRequestDispatcher("/editProductForm").forward(request, response);
                    break;
                }
                index = strPrice.indexOf(",");
                if(index >= 0){
                    strPrice = strPrice.replace(",", ".");
                }
                if("".equals(name) || name == null
                        ||"".equals(country) || country == null
                        ||"".equals(strPrice) || strPrice == null){
                    request.setAttribute("info", "Заполните все поля");
                    request.getRequestDispatcher("/editProductForm").forward(request, response);
                    break;
                } 
                product = productFacade.find(Long.parseLong(productId));
                product.setName(name);
                product.setCountry(country);
                product.setPrice(Double.parseDouble(strPrice));
                productFacade.edit(product);
                request.setAttribute("productId", productId);
                request.setAttribute("info", "Данные о товаре \"" + product.getName() + "\" изменены");
                request.getRequestDispatcher("/listProducts").forward(request, response);
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

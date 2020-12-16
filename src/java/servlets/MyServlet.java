/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Customer;
import entity.Product;
import entity.Purchase;
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
import session.CustomerFacade;
import session.ProductFacade;
import session.PurchaseFacade;

/**
 *
 * @author Alice
 */
@WebServlet(name = "MyServlet", urlPatterns = {
    
    "/addCustomerForm",
    "/createCustomer",
    "/listCustomers",
    "/editCustomerForm",
    "/editCustomer",
    "/addMoneyForm",
    "/addMoney",
    
    "/addProductForm",
    "/createProduct",
    "/listProducts",
    "/editProductForm",
    "/editProduct",
    
    "/makeDealForm",
    "/makeDeal"
})
public class MyServlet extends HttpServlet {
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
        String path = request.getServletPath();
        switch (path) {
            case "/addCustomerForm":
                request.getRequestDispatcher("/WEB-INF/addCustomerForm.jsp").forward(request, response);
                break;
            case "/createCustomer": 
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                String phone = request.getParameter("phone");
                String strBalance = request.getParameter("balance");
                int index = strBalance.indexOf(",");
                if(index >= 0){
                    strBalance = strBalance.replace(",", ".");
                }
                if("".equals(firstname) || firstname == null
                        ||"".equals(lastname) || lastname == null
                        ||"".equals(phone) || phone == null
                        ||"".equals(strBalance) || strBalance == null){
                    request.setAttribute("firstname", firstname);
                    request.setAttribute("lastname", lastname);
                    request.setAttribute("phone", phone);
                    request.setAttribute("balance", strBalance);
                    request.setAttribute("info", "Заполните все поля");
                    request.getRequestDispatcher("/WEB-INF/addCustomerForm.jsp").forward(request, response);
                    break;
                } 
                double balance = Double.parseDouble(strBalance);
                Customer customer = new Customer(firstname, lastname, phone, balance);
                customerFacade.create(customer);
                request.setAttribute("info", "Покупатель \"" + customer.getFirstname() + " " + customer.getLastname() + "\" добавлен");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "/listCustomers":
                List<Customer> listCustomers = customerFacade.findAll();
                request.setAttribute("listCustomers", listCustomers);
                request.getRequestDispatcher("/WEB-INF/listCustomers.jsp").forward(request, response);
                break;
            case "/editCustomerForm":
                String customerId = request.getParameter("customerId");
                customer = customerFacade.find(Long.parseLong(customerId));
                request.setAttribute("customer", customer);
                request.getRequestDispatcher("/WEB-INF/editCustomerForm.jsp").forward(request, response);
                break;
            case "/editCustomer":
                customerId = request.getParameter("customerId");
                firstname = request.getParameter("firstname");
                lastname = request.getParameter("lastname");
                phone = request.getParameter("phone");
                if("".equals(firstname) || firstname == null
                        ||"".equals(lastname) || lastname == null
                        ||"".equals(phone) || phone == null){
                    request.setAttribute("info", "Заполните все поля");
                    request.getRequestDispatcher("/WEB-INF/editCustomerForm.jsp").forward(request, response);
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
                request.getRequestDispatcher("/WEB-INF/addMoneyForm.jsp").forward(request, response);
                break;
            case "/addMoney":
                customerId = request.getParameter("customerId");
                String strMoney = request.getParameter("money");
                index = strMoney.indexOf(",");
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
                balance = money + customer.getBalance();
                customer.setBalance(balance);
                customerFacade.edit(customer);
                request.setAttribute("customerId", customerId);
                request.setAttribute("info", "Счет клиента \"" + customer.getFirstname() + " " + customer.getLastname() + "\" пополнен");
                request.getRequestDispatcher("/listCustomers").forward(request, response);
                break;
            case "/addProductForm":
                request.getRequestDispatcher("/WEB-INF/addProductForm.jsp").forward(request, response);
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
                    request.getRequestDispatcher("/WEB-INF/addProductForm.jsp").forward(request, response);
                    break;
                } 
                double price = Double.parseDouble(strPrice);
                product = new Product(name, country, price);
                productFacade.create(product);
                request.setAttribute("info", "Данные товара \"" + product.getName() + "\" получены");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "/listProducts":
                List<Product> listProducts = productFacade.findAll();
                request.setAttribute("listProducts", listProducts);
                request.getRequestDispatcher("/WEB-INF/listProducts.jsp").forward(request, response);
                break;
            case "/editProductForm":
                String productId = request.getParameter("productId");
                product = productFacade.find(Long.parseLong(productId));
                request.setAttribute("product", product);
                request.getRequestDispatcher("/WEB-INF/editProductForm.jsp").forward(request, response);
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
                    request.getRequestDispatcher("/WEB-INF/addProductForm.jsp").forward(request, response);
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
            case "/makeDealForm":
                listProducts = productFacade.findAll();
                request.setAttribute("listProducts", listProducts);
                listCustomers = customerFacade.findAll();
                request.setAttribute("listCustomers", listCustomers);
                request.getRequestDispatcher("/WEB-INF/makeDealForm.jsp").forward(request, response);
                break;
            case "/makeDeal":
                productId = request.getParameter("productId");
                product = productFacade.find(Long.parseLong(productId));
                customerId = request.getParameter("customerId");
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

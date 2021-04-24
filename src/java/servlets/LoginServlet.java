/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Customer;
import entity.Role;
import entity.User;
import entity.UserRoles;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.CustomerFacade;
import session.RoleFacade;
import session.UserFacade;
import session.UserRolesFacade;
import tools.EncryptPassword;

/**
 *
 * @author Alice
 */
@WebServlet(name = "LoginServlet", loadOnStartup = 1, urlPatterns = {
    "/loginForm",
    "/login",
    "/logout",
    "/registrationForm",
    "/registration"
})
public class LoginServlet extends HttpServlet {
    @EJB
    private UserFacade userFacade;
    @EJB
    private CustomerFacade customerFacade;
    @EJB
    private RoleFacade roleFacade;
    @EJB
    private UserRolesFacade userRolesFacade;
    EncryptPassword encryptPassword = new EncryptPassword();
    
    @Override
    public void init() throws ServletException {
        super.init(); 
        if(userFacade.findAll().size() > 0) return;
        Customer customer = new Customer("Jack", "Nickolson", "45871254", 500.0);
        customerFacade.create(customer);
        String salt = encryptPassword.createSalt();
        String hashPassword = encryptPassword.createHash("12345", salt);
        User user = new User("admin", hashPassword, salt, customer);
        userFacade.create(user);
        
        Role role = new Role("ADMIN");
        roleFacade.create(role);
        UserRoles userRoles = new UserRoles(role, user);
        userRolesFacade.create(userRoles);
        
        role = new Role("MANAGER");
        roleFacade.create(role);
        userRoles = new UserRoles(role, user);
        userRolesFacade.create(userRoles);
        
        role = new Role("CUSTOMER");
        roleFacade.create(role);
        userRoles = new UserRoles(role, user);
        userRolesFacade.create(userRoles);
    }
    
    

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
            case "/loginForm":
                request.getRequestDispatcher("/loginForm.jsp").forward(request, response);
                break;
            case "/login":
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                User user = userFacade.findByLogin(login);
                if(user == null){
                    request.setAttribute("info", "Неправильный логин или пароль");
                    request.getRequestDispatcher("/loginForm").forward(request, response);
                    break;
                }
                String hashPassword = encryptPassword.createHash(password, user.getSalt());
                if(!password.equals(user.getPassword())){
                    request.setAttribute("info", "Неправильный логин или пароль");
                    request.getRequestDispatcher("/loginForm").forward(request, response);
                    break;                    
                }
                HttpSession httpSession = request.getSession(true);
                httpSession.setAttribute("user", user);
                request.setAttribute("info", "Вы вошли как " + user.getCustomer().getFirstname() + " " + user.getCustomer().getLastname());
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "/logout":
                httpSession = request.getSession(false);
                if(httpSession != null){
                    httpSession.invalidate();
                    request.setAttribute("info", "Вы вышли!");
                }
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "/registrationForm":
                httpSession = request.getSession(false);
                User authUser = (User) httpSession.getAttribute("user");
                if(authUser != null){
                    request.setAttribute("info", "Пожалуйста, выйдите из текущего аккаунта");
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                }
                request.getRequestDispatcher("/WEB-INF/registrationForm.jsp").forward(request, response);
                break;
            case "/registration":
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                String phone = request.getParameter("phone");
                String strBalance = request.getParameter("balance");
                login = request.getParameter("login");
                password = request.getParameter("password");
                int index = strBalance.indexOf(",");
                if(index >= 0){
                    strBalance = strBalance.replace(",", ".");
                }
                if("".equals(firstname) || firstname == null
                        ||"".equals(lastname) || lastname == null
                        ||"".equals(phone) || phone == null
                        ||"".equals(strBalance) || strBalance == null
                        ||"".equals(login) || login == null
                        ||"".equals(password) || password == null){
                    request.setAttribute("firstname", firstname);
                    request.setAttribute("lastname", lastname);
                    request.setAttribute("phone", phone);
                    request.setAttribute("balance", strBalance);
                    request.setAttribute("login", login);
                    request.setAttribute("info", "Заполните все поля");
                    request.getRequestDispatcher("/WEB-INF/registrationForm.jsp").forward(request, response);
                    break;
                } 
                double balance = Double.parseDouble(strBalance);
                Customer customer = new Customer(firstname, lastname, phone, balance);
                customerFacade.create(customer);
                String salt = encryptPassword.createSalt();
                hashPassword = encryptPassword.createHash(password, salt);
                user = new User(login, hashPassword, salt, customer);
                userFacade.create(user);
                Role role = roleFacade.findByName("CUSTOMER");
                UserRoles userRoles = new UserRoles(role, user);
                userRolesFacade.create(userRoles);
                request.setAttribute("info", "Пользователь \"" + customer.getFirstname() + " " + customer.getLastname() + "\" добавлен");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            
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

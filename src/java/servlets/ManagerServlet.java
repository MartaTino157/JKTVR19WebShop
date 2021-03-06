/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Product;
import entity.User;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import session.ProductFacade;
import session.UserRolesFacade;

/**
 *
 * @author Alice
 */
@WebServlet(name = "MenegerServlet", urlPatterns = {
    
    "/addProductForm",
    "/createProduct",
    "/editProductForm",
    "/editProduct"

})
//@MultipartConfig()
public class ManagerServlet extends HttpServlet {
    @EJB
    private ProductFacade productFacade;
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
//        String imagesFolder = "C:\\JKTVR19WebShop/Images";
//        List<Part> fileParts;
//        fileParts = request
//                .getParts()
//                .stream()
//                .filter(part -> "file".equals(part.getName()))
//                .collect(Collectors.toList());
//        for(Part filePart : fileParts){
//            String item = imagesFolder+File.separator+getFileName(filePart);
//            File file = new File(item);
//            try(InputStream fileContent = filePart.getInputStream()){
//                Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
//            }
//        }
        String path = request.getServletPath();
        switch (path) {
            case "/addProductForm":
                request.setAttribute("active", "addProductForm");
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
                double index = strPrice.indexOf(",");
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
//    private String getFileName(Part part) {
//        final String partHeader = part.getHeader("content-disposition");
//        for (String content : part.getHeader("content-disposition").split(";")){
//            if(content.trim().startsWith("filename")){
//                return content
//                        .substring(content.indexOf('=')+1)
//                        .trim()
//                        .replace("\"", "");
//            }
//        }
//        return null;
//    }

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

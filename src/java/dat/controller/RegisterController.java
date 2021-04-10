/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat.controller;

import dat.dao.UserDAO;
import dat.dto.UserDTO;
import dat.dto.UserErrorDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author macbook
 */
@WebServlet(name = "RegisterController", urlPatterns = {"/RegisterController"})
public class RegisterController extends HttpServlet {
    
    private static final String ERROR = "register.jsp";
    private static final String SUCCESS = "login.jsp";

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
        String url = ERROR;
        try {
            String email = request.getParameter("txtEmail");
            String name = request.getParameter("txtName");
            String password = request.getParameter("txtPassword");
            String confirm = request.getParameter("txtConfirm");
            boolean flag = true;
            UserErrorDTO error = new UserErrorDTO();
            if (!email.contains("@")) {
                flag = false;
                error.setUserIDError("Please include an '@' in the email adrress");
            }
            if (name.trim().equals("")) {
                flag = false;
                error.setNameError("Please input your name");
            }
            if (password.trim().equals("")) {
                flag = false;
                error.setPasswordError("Please enter a password");
            }
            if (confirm.trim().isEmpty()) {
                flag = false;
                error.setConfirmPasswordError("Does not match the password");
            }
            if (flag) {
                UserDAO dao = new UserDAO();
                UserDTO dto = new UserDTO(email, DigestUtils.sha256Hex(password), name);
                int result = dao.register(dto);
                if (result == 1) {
                    url = SUCCESS;
                } else {
                    error.setUserIDError("The email already exists");
                    request.setAttribute("ERROR", error);
                }
            } else {
                request.setAttribute("ERROR", error);
            }
        } catch (Exception e) {
             log("RegisterController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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

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
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author macbook
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    private static final String ERROR = "login.jsp";
    private static final String US = "UserController";
    private static final String AD = "ManagerController";

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
            String userID = request.getParameter("txtUserID");
            String password = request.getParameter("txtPassword");
            UserErrorDTO error = new UserErrorDTO();
            boolean flag = true;
            if (userID.trim().equals("")) {
                error.setUserIDError("UserID can not null");
                flag = false;
            }
            if (password.trim().isEmpty()) {
                error.setPasswordError("Password can not null");
                flag = false;
            }
            if (flag) {
                UserDAO dao = new UserDAO();
                UserDTO user = dao.checkLogin(userID, DigestUtils.sha256Hex(password));
                if (user != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("LOGIN_USER", user);
                    if (user.getRoleID().equals("AD")) {
                        url = AD;
                    } else {
                        url = US;
                    }
                } else {
                    error.setPasswordError("Invalid userID or password");
                    request.setAttribute("ERROR", error);
                }
            } else {
                request.setAttribute("ERROR", error);
            }
        } catch (Exception e) {
            log("loginController: " + e.getMessage());
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

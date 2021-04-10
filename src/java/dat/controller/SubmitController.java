/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat.controller;

import dat.dao.QuizDAO;
import dat.dto.QuestionDTO;
import dat.dto.QuizCartDTO;
import dat.dto.UserDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author macbook
 */
@WebServlet(name = "SubmitController", urlPatterns = {"/SubmitController"})
public class SubmitController extends HttpServlet {

    private static final String URL = "result.jsp";

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
        String url = URL;
        try {
            HttpSession session = request.getSession();
            int index = Integer.parseInt(request.getParameter("txtIndex"));
            List<QuestionDTO> list = (List<QuestionDTO>) session.getAttribute("LIST_QUESTION");
            QuizCartDTO cart = (QuizCartDTO) session.getAttribute("CART");
            String answer = request.getParameter("rdoAnswer") == null ? "" : request.getParameter("rdoAnswer");
            QuestionDTO dto = list.get(index);
            QuestionDTO dto2 = new QuestionDTO(index, dto.getQuestionID(), dto.getQuestion_content(), dto.getAnswer_correct(), answer, dto.getMark(), dto.getSubjectID());

            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            if (cart == null) {
                cart = new QuizCartDTO(user.getUserID(), null);
            }
            cart.add(dto2);
            request.setAttribute("MARK", cart.getTotal());
            request.setAttribute("NUMBER_CORRECT", cart.getNumOfCorrect());
            request.setAttribute("SUBJECT_ID", cart.getSubjectID());
            QuizDAO dao = new QuizDAO();
            dao.save(cart);
            dao.setStatus(list);
            session.removeAttribute("LIST_QUESTION");
        } catch (Exception e) {
            log("SubmitController: " + e.getMessage());
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

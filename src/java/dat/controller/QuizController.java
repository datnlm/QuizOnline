/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat.controller;

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
@WebServlet(name = "QuizController", urlPatterns = {"/QuizController"})
public class QuizController extends HttpServlet {

    public static final String SUBMIT = "SubmitController";
    public static final String QUIZ = "quiz.jsp";

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
        String url = QUIZ;
        try {
            String btnAction = request.getParameter("btnAction") == null ? "" : request.getParameter("btnAction");
            HttpSession session = request.getSession();
            int index = Integer.parseInt(request.getParameter("txtIndex"));

            List<QuestionDTO> list = (List<QuestionDTO>) session.getAttribute("LIST_QUESTION");
            QuizCartDTO cart = (QuizCartDTO) session.getAttribute("CART");
            if (btnAction.equals("Submit")) {
                url = SUBMIT;
            } else if (btnAction.equals("Next") && index >= list.size() - 1) {
                request.setAttribute("ERROR", "You cannot next");
                QuestionDTO dto = list.get(index);
                request.setAttribute("INDEX", index);
                request.setAttribute("QUESTIONS", dto);
            } else if (btnAction.equals("Back") && index <= 0) {
                request.setAttribute("ERROR", "You cannot back");
                QuestionDTO dto = list.get(0);
                request.setAttribute("INDEX", 0);
                request.setAttribute("QUESTIONS", dto);
            } else {
                QuestionDTO dto = list.get(index);
                if (btnAction.equals("Next")) {
                    String your_answer = "";
                    request.setAttribute("INDEX", index + 1);
                    if (cart != null && cart.getCart().containsKey(String.valueOf(dto.getId()))) {
                        for (QuestionDTO cartDTO : cart.getCart().values()) {
                            if ((index + 1) == cartDTO.getId()) {
                                your_answer = cartDTO.getAnswer_content1();
                                break;
                            }
                        }
                        request.setAttribute("YOUR_ANSWER", your_answer);
                    }
                }
                if (btnAction.equals("Back")) {
                    String your_answer = "";
                    request.setAttribute("INDEX", index - 1);

                    for (QuestionDTO cartDTO : cart.getCart().values()) {
                        if ((index - 1) == cartDTO.getId()) {
                            your_answer = cartDTO.getAnswer_content1();
                            break;
                        }
                    }
                    request.setAttribute("YOUR_ANSWER", your_answer);

                }

                String answer = request.getParameter("rdoAnswer") == null ? "" : request.getParameter("rdoAnswer");

                QuestionDTO dto2 = new QuestionDTO(index, dto.getQuestionID(), dto.getQuestion_content(), dto.getAnswer_correct(), answer, dto.getMark(), dto.getSubjectID());

                UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
                if (cart == null) {
                    cart = new QuizCartDTO(user.getUserID(), null);
                }
                cart.add(dto2);
                request.setAttribute("QUESTIONS", dto);
                session.setAttribute("CART", cart);
            }
        } catch (Exception e) {
            log("QuizController: " + e.getMessage());
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

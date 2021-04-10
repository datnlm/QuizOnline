/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat.controller;

import dat.dao.QuestionsDAO;
import dat.dto.QuestionDTO;
import dat.dto.QuestionErrorDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author macbook
 */
@WebServlet(name = "UpdateController", urlPatterns = {"/UpdateController"})
public class UpdateController extends HttpServlet {

    private static final String URL = "ManagerController";

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

        try {
            int questionID = Integer.parseInt(request.getParameter("txtQuestionID"));
            String question_content = request.getParameter("txtQuestionContent");
            String answer_correct = "";
            String answer_content1 = request.getParameter("txtAnswerContent1");
            String answer_content2 = request.getParameter("txtAnswerContent2");
            String answer_content3 = request.getParameter("txtAnswerContent3");
            String answer_content4 = request.getParameter("txtAnswerContent4");
            String tmpMark = request.getParameter("txtMark");
            float mark = -1;
            String subjectID = request.getParameter("cbxSubject");
            String status = request.getParameter("cbxStatus");
            QuestionErrorDTO error = new QuestionErrorDTO();
            boolean flag = true;
            if (question_content.trim().isEmpty()) {
                flag = false;
                error.setQuestion_contentError("Question can not null");
            }

            if (answer_content1.trim().isEmpty()) {
                flag = false;
                error.setAnswer_content1Error("Answer can not null");
            }
            if (answer_content2.trim().isEmpty()) {
                flag = false;
                error.setAnswer_content2Error("Answer can not null");
            }
            if (answer_content3.trim().isEmpty()) {
                flag = false;
                error.setAnswer_content3Error("Answer can not null");
            }
            if (answer_content4.trim().isEmpty()) {
                flag = false;
                error.setAnswer_content4Error("Answer can not null");
            }
            if (tmpMark.trim().isEmpty()) {
                flag = false;
                error.setMarkError("Mark can not null");
            } else {
                mark = Float.parseFloat(tmpMark);
                if (mark <= 0) {
                    flag = false;
                    error.setMarkError("Mark > 0");
                }
            }
            if (answer_content1.equals(answer_content2)
                    || answer_content1.equals(answer_content3)
                    || answer_content1.equals(answer_content4)
                    || answer_content2.equals(answer_content3)
                    || answer_content2.equals(answer_content4)
                    || answer_content3.equals(answer_content4)) {
                request.setAttribute("ERROR", "The answer cannot be the same");
                flag = false;
            }
            if (flag) {
                answer_correct = request.getParameter("rdoUpdate");

                QuestionsDAO dao = new QuestionsDAO();
                QuestionDTO dto = new QuestionDTO(questionID, question_content, answer_correct, answer_content1, answer_content2, answer_content3, answer_content4, mark, subjectID, status);
                int result = dao.uppdateQuestion(dto);
                if (result == 0) {
                    request.setAttribute("ERROR", "Update failed");
                }
            } else {
                request.setAttribute("ERROR_QUESTION_UPDATE", error);
                request.setAttribute("questionID", questionID);
            }

        } catch (Exception e) {
            log("UpdateController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(URL).forward(request, response);
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

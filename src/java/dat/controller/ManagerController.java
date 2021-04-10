/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat.controller;

import dat.dao.QuestionsDAO;
import dat.dto.QuestionDTO;
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
@WebServlet(name = "ManagerController", urlPatterns = {"/ManagerController"})
public class ManagerController extends HttpServlet {

    private static final String ERROR = "invalid.html";
    private static final String SUCCESS = "manager.jsp";

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
            int index = 1;
            String tmp = request.getParameter("txtIndex");
            if (tmp != null) {
                index = Integer.parseInt(tmp);
            }

            QuestionsDAO dao = new QuestionsDAO();
            String search = request.getParameter("txtSearch") == null ? "" : request.getParameter("txtSearch");
            String question_content = request.getParameter("txtQuestionName");
            String status = request.getParameter("cbxStatusSearch");
            String subject = request.getParameter("cbxSubjectSearch");
            String value = "";
            if (search.equals("SearchStatus")) {
                value = status;
            } else if (search.equals("SearchSubject")) {
                value = subject;
            } else {
                value = question_content == null ? "" : question_content;
            }
            List<QuestionDTO> list = dao.getQuestions((index - 1) * 20, search, value);
            List<String> listSubject = dao.getSubject();
            List<String> listStatus = dao.getStatus();
            int paging = dao.getPage(index, search, value);
            if (!list.isEmpty()) {
                request.setAttribute("LIST_QUESTIONS", list);
                url = SUCCESS;
            } else {
                request.setAttribute("NOT_FOUND", "Not found");
                url = SUCCESS;
            }
            HttpSession session = request.getSession();
            session.setAttribute("LIST_SUBJECT", listSubject);
            session.setAttribute("LIST_STATUS", listStatus);
            request.setAttribute("NUMBER_PAGE", paging);
        } catch (Exception e) {
            log("ManagerController: " + e.getMessage());
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

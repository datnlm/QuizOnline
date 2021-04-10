/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat.controller;

import dat.dao.HistoryDAO;
import dat.dto.HistoryDTO;
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
@WebServlet(name = "ViewHistoryController", urlPatterns = {"/ViewHistoryController"})
public class ViewHistoryController extends HttpServlet {

    private static final String HISTORY = "history.jsp";
    private static final String VIEW_DETAIL = "historyDetail.jsp";

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
        String url = HISTORY;
        try {
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            HistoryDAO dao = new HistoryDAO();
            String btnAction = request.getParameter("btnAction") == null ? "" : request.getParameter("btnAction");
            String subjectName = request.getParameter("cbxSubject");
            if (btnAction.equals("ViewDetail")) {
                int historyID = Integer.parseInt(request.getParameter("txtHistoryID"));
                List<HistoryDTO> list = dao.getHistoryDetail(historyID);
                request.setAttribute("LIST_DETAIL", list);
                url = VIEW_DETAIL;
            } else if (btnAction.equals("SearchSubject")) {
                List<HistoryDTO> list = dao.getHistory(user.getUserID(), subjectName);
                request.setAttribute("LIST", list);
            } else {
                List<HistoryDTO> list = dao.getHistory(user.getUserID());
                request.setAttribute("LIST", list);
            }
        } catch (Exception e) {
            log("ViewHistoryController: " + e.getMessage());
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

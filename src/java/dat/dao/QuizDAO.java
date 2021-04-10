/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat.dao;

import dat.dto.QuestionDTO;
import dat.dto.QuizCartDTO;
import dat.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author macbook
 */
public class QuizDAO {

    public void save(QuizCartDTO cart) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "insert into tblHistory(number_of_correct, totalMark, subjectID, userID, createDate) values(?, ?, ?, ?, CURRENT_TIMESTAMP)";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, cart.getNumOfCorrect());
                pst.setFloat(2, cart.getTotal());
                pst.setString(3, cart.getSubjectID());
                pst.setString(4, cart.getUserID());

                pst.executeUpdate();

                for (QuestionDTO dto : cart.getCart().values()) {
                    String sql2 = "insert into tblHistoryDetail(question_content, answer_content, mark, historyID) select question_content, ?, ?, historyID\n"
                            + "from (select Max(historyID) as historyID\n"
                            + "    from tblHistory)tmp, tblQuestions\n"
                            + "where tblQuestions.questionID = ?";
                    pst = cn.prepareStatement(sql2);
                    pst.setString(1, dto.getAnswer_content1());
                    pst.setFloat(2, dto.getMark());
                    pst.setInt(3, dto.getQuestionID());
                    pst.executeUpdate();

                }
            }
        } catch (Exception e) {
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }

    }

    public void setStatus(List<QuestionDTO> list) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                for (QuestionDTO x : list) {
                    String sql = "Update tblQuestions\n"
                            + "set current_quiz = 0\n"
                            + "where questionID = ?";
                    pst = cn.prepareStatement(sql);
                    pst.setInt(1, x.getQuestionID());
                    pst.executeUpdate();
                }
            }
        } catch (Exception e) {
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
    }
}

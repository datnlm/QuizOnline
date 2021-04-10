/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat.dao;

import dat.dto.HistoryDTO;
import dat.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author macbook
 */
public class HistoryDAO {

    public List<HistoryDTO> getHistory(String userID) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<HistoryDTO> list = new ArrayList<>();
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "select historyID, number_of_correct, userID, subjectID, totalMark, createDate\n"
                        + "from tblHistory\n"
                        + "where userID = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, userID);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int historyID = rs.getInt("historyID");
                    String subjectID = rs.getString("subjectID");
                    float total = rs.getFloat("totalMark");
                    int number_of_correct = rs.getInt("number_of_correct");
                    String createDate = rs.getString("createDate");
                    list.add(new HistoryDTO(historyID, userID, subjectID, number_of_correct, total, createDate));
                }
            }
        } catch (Exception e) {

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return list;
    }

    public List<HistoryDTO> getHistory(String userID, String subjectName) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<HistoryDTO> list = new ArrayList<>();
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "select historyID, number_of_correct, totalMark, h.subjectID as subjectID, createDate\n"
                        + "from tblHistory h join tblSubject s on h.subjectID = s.subjectID\n"
                        + "where subjectName = ? and userID = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, subjectName);
                pst.setString(2, userID);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int historyID = rs.getInt("historyID");
                    String subjectID = rs.getString("subjectID");
                    float total = rs.getFloat("totalMark");
                    int number_of_correct = rs.getInt("number_of_correct");
                    String createDate = rs.getString("createDate");
                    list.add(new HistoryDTO(historyID, userID, subjectID, number_of_correct, total, createDate));
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return list;
    }

    public List<HistoryDTO> getHistoryDetail(int historyID) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<HistoryDTO> list = new ArrayList<>();
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "select question_content, answer_content, mark\n"
                        + "from tblHistoryDetail\n"
                        + "where historyID = ?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, historyID);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String question_content = rs.getString("question_content");
                    String answer_content = rs.getString("answer_content");
                    float mark = rs.getFloat("mark");
                    list.add(new HistoryDTO(question_content, answer_content, mark));
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return list;
    }
}

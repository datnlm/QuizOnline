/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat.dao;

import dat.dto.QuestionDTO;
import dat.utils.DBUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author macbook
 */
public class QuestionsDAO {

    public List<QuestionDTO> getQuestions(int index, String search, String value) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<QuestionDTO> list = new ArrayList<>();
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                boolean flag = false;
                String sql = "";
                if (search.equals("SearchSubject")) {
                    sql = "select questionID, question_content, answer_correct, answer_content1, answer_content2, answer_content3, answer_content4, mark, subjectID, createDate, status\n"
                            + "from tblQuestions\n"
                            + "where subjectID = ? and current_quiz = 0\n"
                            + "order by subjectID asc, question_content asc\n"
                            + "offset ? rows\n"
                            + "fetch first 20 row only";
                } else if (search.equals("SearchStatus")) {
                    sql = "select questionID, question_content, answer_correct, answer_content1, answer_content2, answer_content3, answer_content4, mark, subjectID, createDate, status\n"
                            + "from tblQuestions\n"
                            + "where status = ? and current_quiz = 0\n"
                            + "order by subjectID asc, question_content asc\n"
                            + "offset ? rows\n"
                            + "fetch first 20 row only";
                } else {
                    flag = true;
                    sql = "select questionID, question_content, answer_correct, answer_content1, answer_content2, answer_content3, answer_content4, mark, subjectID, createDate, status\n"
                            + "from tblQuestions\n"
                            + "where question_content like ? and current_quiz = 0\n"
                            + "order by subjectID asc, question_content asc\n"
                            + "offset ? rows\n"
                            + "fetch first 20 row only";
                }
                pst = cn.prepareStatement(sql);
                if (flag) {
                    pst.setString(1, "%" + value + "%");
                } else {
                    pst.setString(1, value);
                }
                pst.setInt(2, index);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int questionID = rs.getInt("questionID");
                    String question_content = rs.getString("question_content");
                    String answer_correct = rs.getString("answer_correct");
                    String answer_content1 = rs.getString("answer_content1");
                    String answer_content2 = rs.getString("answer_content2");
                    String answer_content3 = rs.getString("answer_content3");
                    String answer_content4 = rs.getString("answer_content4");
                    float mark = rs.getFloat("mark");
                    String subjectID = rs.getString("subjectID");
                    Date createDate = rs.getDate("createDate");
                    String status = rs.getString("status");
                    list.add(new QuestionDTO(questionID, question_content, answer_correct, answer_content1, answer_content2, answer_content3, answer_content4, mark, createDate, subjectID, status));

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

    public List<String> getSubject() throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<String> list = new ArrayList<>();
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "Select subjectID from tblSubject";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String subject = rs.getString("subjectID");
                    list.add(subject);
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

    public List<String> getStatus() throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<String> list = new ArrayList<>();
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "Select distinct status from tblQuestions";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String status = rs.getString("status");
                    list.add(status);
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

    public int addQuestion(QuestionDTO dto) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        int result = -1;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "insert into tblQuestions(question_content, answer_correct, answer_content1, answer_content2, answer_content3, answer_content4, createDate, mark, status, subjectID) values(?, ?, ?, ?, ?, ?, getdate(), ?, 'Active', ?)";
                pst = cn.prepareStatement(sql);
                pst.setString(1, dto.getQuestion_content());
                pst.setString(2, dto.getAnswer_correct());
                pst.setString(3, dto.getAnswer_content1());
                pst.setString(4, dto.getAnswer_content2());
                pst.setString(5, dto.getAnswer_content3());
                pst.setString(6, dto.getAnswer_content4());
                pst.setFloat(7, dto.getMark());
                pst.setString(8, dto.getSubjectID());
                result = pst.executeUpdate();
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
        return result;
    }

    public int getPage(int index, String search, String value) throws ClassNotFoundException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int result = 0;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                boolean flag = false;
                String sql = "";
                if (search.equals("SearchSubject")) {
                    sql = "select count(questionID) as number\n"
                            + "from tblQuestions\n"
                            + "where subjectID = ?";
                } else if (search.equals("SearchStatus")) {
                    sql = "select count(questionID) as number\n"
                            + "from tblQuestions\n"
                            + "where status = ?";
                } else {
                    flag = true;
                    sql = "select count(questionID) as number\n"
                            + "from tblQuestions\n"
                            + "where question_content like ?";
                }
                pst = cn.prepareStatement(sql);
                if (flag) {
                    pst.setString(1, "%" + value + "%");
                } else {
                    pst.setString(1, value);
                }
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("number");
                    result = result % 20 == 0 ? result / 20 : result / 20 + 1;
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
        return result;
    }

    public int uppdateQuestion(QuestionDTO dto) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        int result = -1;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "UPDATE tblQuestions\n"
                        + "SET question_content = ?, answer_correct = ?, answer_content1 = ?,  answer_content2 = ?,  answer_content3 = ?,  answer_content4 = ?, mark = ?, status = ?, subjectID = ?\n"
                        + "WHERE questionID = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, dto.getQuestion_content());
                pst.setString(2, dto.getAnswer_correct());
                pst.setString(3, dto.getAnswer_content1());
                pst.setString(4, dto.getAnswer_content2());
                pst.setString(5, dto.getAnswer_content3());
                pst.setString(6, dto.getAnswer_content4());
                pst.setFloat(7, dto.getMark());
                pst.setString(8, dto.getStatus());
                pst.setString(9, dto.getSubjectID());
                pst.setInt(10, dto.getQuestionID());
                result = pst.executeUpdate();
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
        return result;
    }

    public int deleteQuestion(String questionID) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        int result = -1;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "UPDATE tblQuestions\n"
                        + "SET status = 'Inactive'\n"
                        + "WHERE questionID = ? and status = 'Active'";
                pst = cn.prepareStatement(sql);
                pst.setString(1, questionID);
                result = pst.executeUpdate();
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
        return result;
    }

    public List<QuestionDTO> getQuestions(int quantity, String subjectID) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<QuestionDTO> list = new ArrayList<>();
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "Update tblQuestions\n"
                        + "set current_quiz = 1\n"
                        + "where questionID in (\n"
                        + "            SELECT TOP " + quantity + " questionID\n"
                        + "            FROM tblQuestions\n"
                        + "            where subjectID = ? and status = 'Active'\n"
                        + "            ORDER BY NEWID())";
                pst = cn.prepareStatement(sql);
                pst.setString(1, subjectID);
                int result = pst.executeUpdate();
                if (result != 0) {
                    String sql2 = "SELECT questionID, question_content, answer_correct, answer_content1, answer_content2, answer_content3, answer_content4, mark\n"
                            + "FROM tblQuestions \n"
                            + "where current_quiz = 1 and subjectID = ? and status = 'Active'\n"
                            + "ORDER BY NEWID()";
                    pst = cn.prepareStatement(sql2);
                    pst.setString(1, subjectID);
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        int questionID = rs.getInt("questionID");
                        String question_content = rs.getString("question_content");
                        String answer_correct = rs.getString("answer_correct");
                        String answer_content1 = rs.getString("answer_content1");
                        String answer_content2 = rs.getString("answer_content2");
                        String answer_content3 = rs.getString("answer_content3");
                        String answer_content4 = rs.getString("answer_content4");
                        float mark = rs.getFloat("mark");
                        list.add(new QuestionDTO(questionID, question_content, answer_correct, answer_content1, answer_content2, answer_content3, answer_content4, mark, subjectID));
                    }
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

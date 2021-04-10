/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat.dto;

/**
 *
 * @author macbook
 */
public class HistoryDTO {

    private int id;
    private String userID;
    private String subjectID;
    private int number_of_correct;
    private float total;
    private String question_content;
    private String answer_content;
    private float mark;
    private String createDate;

    public HistoryDTO() {
    }

    public HistoryDTO(int id, String userID, String subjectID, int number_of_correct, float total, String createDate) {
        this.id = id;
        this.userID = userID;
        this.subjectID = subjectID;
        this.number_of_correct = number_of_correct;
        this.total = total;
        this.createDate = createDate;
    }

    public HistoryDTO(String question_content, String answer_content, float mark) {
        this.question_content = question_content;
        this.answer_content = answer_content;
        this.mark = mark;
    }

    public int getNumber_of_correct() {
        return number_of_correct;
    }

    public void setNumber_of_correct(int number_of_correct) {
        this.number_of_correct = number_of_correct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getQuestion_content() {
        return question_content;
    }

    public void setQuestion_content(String question_content) {
        this.question_content = question_content;
    }

    public String getAnswer_content() {
        return answer_content;
    }

    public void setAnswer_content(String answer_content) {
        this.answer_content = answer_content;
    }

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    

}

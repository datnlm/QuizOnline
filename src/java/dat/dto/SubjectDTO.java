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
public class SubjectDTO {

    private String subjectID;
    private String subjectName;
    private int time;
    private int quantity;

    public SubjectDTO() {
    }

    public SubjectDTO(String subjectID, String subjectName, int time, int quantity) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.time = time;
        this.quantity = quantity;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}

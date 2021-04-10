/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat.dto;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author macbook
 */
public class QuizCartDTO {

    private String userID;
    private Map<String, QuestionDTO> cart;
    private float total;
    private int numOfCorrect;
    private String subjectID;

    public QuizCartDTO() {
    }

    public QuizCartDTO(String userID, Map<String, QuestionDTO> cart) {
        this.userID = userID;
        this.cart = cart;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Map<String, QuestionDTO> getCart() {
        return cart;
    }

    public void setCart(Map<String, QuestionDTO> cart) {
        this.cart = cart;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getNumOfCorrect() {
        return numOfCorrect;
    }

    public void setNumOfCorrect(int numOfCorrect) {
        this.numOfCorrect = numOfCorrect;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public void add(QuestionDTO dto) {
        if (this.cart == null) {
            this.cart = new HashMap<>();
            subjectID = dto.getSubjectID();
        }
        String id = String.valueOf(dto.getId());
        if (cart != null) {
            if (cart.containsKey(id)) {
                if (!this.cart.get(id).getAnswer_content1().equals(dto.getAnswer_content1())) {
                    total -= dto.getMark();
                    numOfCorrect -= 1;
                }
            } else {
                cart.put(id, dto);
                if (this.cart.get(id).getAnswer_content1().equals(dto.getAnswer_correct())) {
                    total += dto.getMark();
                    numOfCorrect += 1;
                }
            }
        }
    }
}

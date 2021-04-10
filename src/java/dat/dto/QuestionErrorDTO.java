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
public class QuestionErrorDTO {

    private String question_contentError;
    private String answer_content1Error;
    private String answer_content2Error;
    private String answer_content3Error;
    private String answer_content4Error;
    private String markError;
    private String subjectIDError;

    public QuestionErrorDTO() {
    }

    public QuestionErrorDTO(String question_contentError, String answer_content1Error, String answer_content2Error, String answer_content3Error, String answer_content4Error, String markError, String subjectIDError) {
        this.question_contentError = question_contentError;
        this.answer_content1Error = answer_content1Error;
        this.answer_content2Error = answer_content2Error;
        this.answer_content3Error = answer_content3Error;
        this.answer_content4Error = answer_content4Error;
        this.markError = markError;
        this.subjectIDError = subjectIDError;
    }

    public String getQuestion_contentError() {
        return question_contentError;
    }

    public void setQuestion_contentError(String question_contentError) {
        this.question_contentError = question_contentError;
    }

    public String getAnswer_content1Error() {
        return answer_content1Error;
    }

    public void setAnswer_content1Error(String answer_content1Error) {
        this.answer_content1Error = answer_content1Error;
    }

    public String getAnswer_content2Error() {
        return answer_content2Error;
    }

    public void setAnswer_content2Error(String answer_content2Error) {
        this.answer_content2Error = answer_content2Error;
    }

    public String getAnswer_content3Error() {
        return answer_content3Error;
    }

    public void setAnswer_content3Error(String answer_content3Error) {
        this.answer_content3Error = answer_content3Error;
    }

    public String getAnswer_content4Error() {
        return answer_content4Error;
    }

    public void setAnswer_content4Error(String answer_content4Error) {
        this.answer_content4Error = answer_content4Error;
    }

    public String getMarkError() {
        return markError;
    }

    public void setMarkError(String markError) {
        this.markError = markError;
    }

    public String getSubjectIDError() {
        return subjectIDError;
    }

    public void setSubjectIDError(String subjectIDError) {
        this.subjectIDError = subjectIDError;
    }

  
}

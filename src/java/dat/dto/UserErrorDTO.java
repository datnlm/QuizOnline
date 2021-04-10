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
public class UserErrorDTO {

    private String userIDError;
    private String passwordError;
    private String nameError;
    private String confirmPasswordError;

    public UserErrorDTO() {
    }

    public UserErrorDTO(String userIDError, String passwordError) {
        this.userIDError = userIDError;
        this.passwordError = passwordError;
    }

    public UserErrorDTO(String userIDError, String passwordError, String nameError, String confirmPasswordError) {
        this.userIDError = userIDError;
        this.passwordError = passwordError;
        this.nameError = nameError;
        this.confirmPasswordError = confirmPasswordError;
    }

    public String getUserIDError() {
        return userIDError;
    }

    public void setUserIDError(String userIDError) {
        this.userIDError = userIDError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public String getConfirmPasswordError() {
        return confirmPasswordError;
    }

    public void setConfirmPasswordError(String confirmPasswordError) {
        this.confirmPasswordError = confirmPasswordError;
    }

}

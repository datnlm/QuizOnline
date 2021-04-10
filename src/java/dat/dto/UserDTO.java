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
public class UserDTO {

    private String userID;
    private String password;
    private String fullName;
    private String roleID;

    public UserDTO() {
    }

    public UserDTO(String userID, String password, String fullName) {
        this.userID = userID;
        this.password = password;
        this.fullName = fullName;
    }
    

    public UserDTO(String userID, String password, String fullName, String roleID) {
        this.userID = userID;
        this.password = password;
        this.fullName = fullName;
        this.roleID = roleID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat.dao;

import dat.dto.UserDTO;
import dat.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author macbook
 */
public class UserDAO {

    public UserDTO checkLogin(String userID, String password) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        UserDTO user = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "Select fullName, roleID from tblUsers where userID = ? and password = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, userID);
                pst.setString(2, password);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    user = new UserDTO(userID, password, fullName, roleID);
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
        return user;
    }

    public int register(UserDTO dto) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        int result = -1;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "insert into tblUsers(userID, password, fullName, roleID, status) values(?, ?, ?, 'Student', 'New')";
                pst = cn.prepareStatement(sql);
                pst.setString(1, dto.getUserID());
                pst.setString(2, dto.getPassword());
                pst.setString(3, dto.getFullName());
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
}

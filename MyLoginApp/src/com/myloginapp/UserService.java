package com.myloginapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    private static final String DB_URL = "jdbc:sqlite:resources/database.db";

    public UserService() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            if (conn != null) {
                String createTableSql = "CREATE TABLE IF NOT EXISTS users ("
                        + "id TEXT PRIMARY KEY,"
                        + "password TEXT NOT NULL,"
                        + "name TEXT NOT NULL,"
                        + "birthDate TEXT NOT NULL,"
                        + "gender TEXT NOT NULL,"
                        + "phoneNumber TEXT NOT NULL,"
                        + "role TEXT NOT NULL"
                        + ");";
                conn.createStatement().execute(createTableSql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean saveUser(User user) {
        String sql = "INSERT INTO users(id, password, name, birthDate, gender, phoneNumber, role) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getBirthDate());
            pstmt.setString(5, user.getGender());
            pstmt.setString(6, user.getPhoneNumber());
            pstmt.setString(7, user.getRole());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean authenticate(String id, String password) {
        String sql = "SELECT * FROM users WHERE id = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getUserRole(String id) {
        String sql = "SELECT role FROM users WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("role");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

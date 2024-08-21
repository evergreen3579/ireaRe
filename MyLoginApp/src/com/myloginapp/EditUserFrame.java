package com.myloginapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EditUserFrame extends JFrame {
    private JTextField nameField;
    private JTextField birthDateField;
    private JTextField phoneNumberField;
    private JComboBox<String> genderComboBox;
    private JComboBox<String> roleComboBox;
    private String userId;

    public EditUserFrame(String userId) {
        this.userId = userId;
        setTitle("사용자 수정");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 5, 5));

        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Birth Date (YYYY-MM-DD):"));
        birthDateField = new JTextField();
        panel.add(birthDateField);

        panel.add(new JLabel("Phone Number:"));
        phoneNumberField = new JTextField();
        panel.add(phoneNumberField);

        panel.add(new JLabel("Gender:"));
        genderComboBox = new JComboBox<>(new String[]{"남자", "여자"});
        panel.add(genderComboBox);

        panel.add(new JLabel("Role:"));
        roleComboBox = new JComboBox<>(new String[]{"USER", "ADMIN"});
        panel.add(roleComboBox);

        // Load user information
        loadUserInfo();

        JButton saveButton = new JButton("저장");
        saveButton.setPreferredSize(new Dimension(100, 30));
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String birthDate = birthDateField.getText();
                String phoneNumber = phoneNumberField.getText();
                String gender = (String) genderComboBox.getSelectedItem();
                String role = (String) roleComboBox.getSelectedItem();

                if (updateUserInfo(name, birthDate, phoneNumber, gender, role)) {
                    JOptionPane.showMessageDialog(null, "사용자 정보가 수정되었습니다.");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "사용자 정보 수정에 실패했습니다.");
                }
            }
        });

        JButton cancelButton = new JButton("취소");
        cancelButton.setPreferredSize(new Dimension(100, 30));
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        panel.add(buttonPanel);

        add(panel);
    }

    private void loadUserInfo() {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:resources/database.db");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                nameField.setText(rs.getString("name"));
                birthDateField.setText(rs.getString("birthDate"));
                phoneNumberField.setText(rs.getString("phoneNumber"));
                genderComboBox.setSelectedItem(rs.getString("gender"));
                roleComboBox.setSelectedItem(rs.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean updateUserInfo(String name, String birthDate, String phoneNumber, String gender, String role) {
        String sql = "UPDATE users SET name = ?, birthDate = ?, phoneNumber = ?, gender = ?, role = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:resources/database.db");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, birthDate);
            pstmt.setString(3, phoneNumber);
            pstmt.setString(4, gender);
            pstmt.setString(5, role);
            pstmt.setString(6, userId);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

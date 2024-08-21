package com.myloginapp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminIndexFrame extends JFrame {
    private JTable userTable;
    private DefaultTableModel tableModel;
    private UserService userService;

    public AdminIndexFrame() {
        userService = new UserService();

        setTitle("관리자 메인 화면");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // 사용자 목록 테이블
        tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Birth Date", "Gender", "Phone Number", "Role"}, 0);
        userTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(userTable);

        // 사용자 목록을 데이터베이스에서 불러오기
        loadUserTable();

        panel.add(scrollPane, BorderLayout.CENTER);

        // 사용자 수정 버튼
        JButton editButton = new JButton("수정");
        editButton.setPreferredSize(new Dimension(100, 30));
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = userTable.getSelectedRow();
                if (selectedRow >= 0) {
                    String id = (String) tableModel.getValueAt(selectedRow, 0);
                    new EditUserFrame(id).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "사용자를 선택하세요.");
                }
            }
        });

        // 로그아웃 버튼
        JButton logoutButton = new JButton("로그아웃");
        logoutButton.setPreferredSize(new Dimension(100, 30));
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginFrame().setVisible(true);
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(editButton);
        buttonPanel.add(logoutButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    private void loadUserTable() {
        String sql = "SELECT * FROM users";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:resources/database.db");
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String birthDate = rs.getString("birthDate");
                String gender = rs.getString("gender");
                String phoneNumber = rs.getString("phoneNumber");
                String role = rs.getString("role");

                tableModel.addRow(new Object[]{id, name, birthDate, gender, phoneNumber, role});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

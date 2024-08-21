package com.myloginapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IndexFrame extends JFrame {
    public IndexFrame() {
        setTitle("사용자 메인 화면");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));

        JButton membershipButton = new JButton("회원신청");
        membershipButton.setPreferredSize(new Dimension(200, 50));
        membershipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewUserFrame().setVisible(true);
            }
        });

        JButton membershipRenewButton = new JButton("회원연장");
        membershipRenewButton.setPreferredSize(new Dimension(200, 50));
        membershipRenewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "회원연장 기능은 준비 중입니다.");
            }
        });

        JButton lockerRequestButton = new JButton("라커신청");
        lockerRequestButton.setPreferredSize(new Dimension(200, 50));
        lockerRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "라커신청 기능은 준비 중입니다.");
            }
        });

        JButton lockerRenewButton = new JButton("라커연장");
        lockerRenewButton.setPreferredSize(new Dimension(200, 50));
        lockerRenewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "라커연장 기능은 준비 중입니다.");
            }
        });

        JButton logoutButton = new JButton("로그아웃");
        logoutButton.setPreferredSize(new Dimension(200, 50));
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginFrame().setVisible(true);
                dispose();
            }
        });

        panel.add(membershipButton);
        panel.add(membershipRenewButton);
        panel.add(lockerRequestButton);
        panel.add(lockerRenewButton);
        panel.add(logoutButton);

        add(panel);
    }
}

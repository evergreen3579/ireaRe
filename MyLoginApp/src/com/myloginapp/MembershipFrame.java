package com.myloginapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MembershipFrame extends JFrame {
    private UserService userService;
    private JTextField idField;
    private JPasswordField passwordField;
    private JTextField nameField;
    private JComboBox<String> yearComboBox;
    private JComboBox<String> monthComboBox;
    private JComboBox<String> dayComboBox;
    private JComboBox<String> genderComboBox;
    private JTextField phoneNumberField;

    public MembershipFrame() {
        userService = new UserService();

        setTitle("회원가입");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel idLabel = new JLabel("아이디:");
        idField = new JTextField();
        idField.setPreferredSize(new Dimension(200, 25));

        JLabel passwordLabel = new JLabel("비밀번호:");
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 25));

        JLabel nameLabel = new JLabel("이름:");
        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(200, 25));

        JLabel birthDateLabel = new JLabel("생년월일:");
        yearComboBox = new JComboBox<>(getYearOptions());
        monthComboBox = new JComboBox<>(getMonthOptions());
        dayComboBox = new JComboBox<>(getDayOptions());
        yearComboBox.setPreferredSize(new Dimension(70, 25));
        monthComboBox.setPreferredSize(new Dimension(60, 25));
        dayComboBox.setPreferredSize(new Dimension(60, 25));

        JLabel genderLabel = new JLabel("성별:");
        genderComboBox = new JComboBox<>(new String[]{"Male", "Female"});
        genderComboBox.setPreferredSize(new Dimension(200, 25));

        JLabel phoneNumberLabel = new JLabel("휴대폰번호:");
        phoneNumberField = new JTextField();
        phoneNumberField.setPreferredSize(new Dimension(200, 25));

        JButton createButton = new JButton("생성");
        createButton.setPreferredSize(new Dimension(100, 30));
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String password = new String(passwordField.getPassword());
                String name = nameField.getText();
                String birthDate = yearComboBox.getSelectedItem() + "/" + monthComboBox.getSelectedItem() + "/" + dayComboBox.getSelectedItem();
                String gender = (String) genderComboBox.getSelectedItem();
                String phoneNumber = phoneNumberField.getText();

                if (id.isEmpty() || password.isEmpty() || name.isEmpty() || birthDate.isEmpty() || gender.isEmpty() || phoneNumber.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "모든 필드를 입력하세요.");
                    return;
                }

                User newUser = new User(id, password, name, birthDate, gender, phoneNumber);
                if (userService.saveUser(newUser)) {
                    JOptionPane.showMessageDialog(null, "회원가입 성공!");
                    new LoginFrame().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "ID 또는 전화번호가 중복됩니다. 다시 시도하세요.");
                }
            }
        });

        JButton cancelButton = new JButton("취소");
        cancelButton.setPreferredSize(new Dimension(100, 30));
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginFrame().setVisible(true);
                dispose();
            }
        });

        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addComponent(idLabel)
                .addComponent(passwordLabel)
                .addComponent(nameLabel)
                .addComponent(birthDateLabel)
                .addComponent(genderLabel)
                .addComponent(phoneNumberLabel))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(idField)
                .addComponent(passwordField)
                .addComponent(nameField)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(yearComboBox)
                    .addComponent(monthComboBox)
                    .addComponent(dayComboBox))
                .addComponent(genderComboBox)
                .addComponent(phoneNumberField)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(createButton)
                    .addComponent(cancelButton)))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(idLabel)
                .addComponent(idField))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(passwordLabel)
                .addComponent(passwordField))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(nameLabel)
                .addComponent(nameField))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(birthDateLabel)
                .addComponent(yearComboBox)
                .addComponent(monthComboBox)
                .addComponent(dayComboBox))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(genderLabel)
                .addComponent(genderComboBox))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(phoneNumberLabel)
                .addComponent(phoneNumberField))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(createButton)
                .addComponent(cancelButton))
        );

        add(panel);
    }

    private String[] getYearOptions() {
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        String[] years = new String[100];
        for (int i = 0; i < 100; i++) {
            years[i] = Integer.toString(currentYear - i);
        }
        return years;
    }

    private String[] getMonthOptions() {
        String[] months = new String[12];
        for (int i = 0; i < 12; i++) {
            months[i] = String.format("%02d", i + 1);
        }
        return months;
    }

    private String[] getDayOptions() {
        String[] days = new String[31];
        for (int i = 0; i < 31; i++) {
            days[i] = String.format("%02d", i + 1);
        }
        return days;
    }
}

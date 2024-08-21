package com.myloginapp;

import com.toedter.calendar.JDateChooser; // JDateChooser import 추가

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewUserFrame extends JFrame {
    private JTextField nameField;
    private JTextField phoneNumberField;
    private JDateChooser birthDateChooser;
    private JComboBox<String> genderComboBox;
    private JDateChooser startDateChooser;
    private JComboBox<Integer> registrationPeriodComboBox;
    private JTextField expiryDateField;
    private JComboBox<String> proComboBox;
    private JComboBox<String> practiceUseComboBox;
    private JComboBox<String> golfLessonComboBox;
    private JComboBox<String> paymentInfoComboBox;
    private JTextField priceField;

    public NewUserFrame() {
        setTitle("회원신청");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(14, 2, 5, 5));

        // 이름
        panel.add(new JLabel("이름:"));
        nameField = new JTextField();
        panel.add(nameField);

        // 전화번호
        panel.add(new JLabel("전화번호:"));
        phoneNumberField = new JTextField();
        panel.add(phoneNumberField);

        // 생일
        panel.add(new JLabel("생일:"));
        birthDateChooser = new JDateChooser();
        panel.add(birthDateChooser);

        // 성별
        panel.add(new JLabel("성별:"));
        genderComboBox = new JComboBox<>(new String[]{"남자", "여자"});
        panel.add(genderComboBox);

        // 시작 날짜
        panel.add(new JLabel("시작 날짜:"));
        startDateChooser = new JDateChooser();
        panel.add(startDateChooser);

        // 등록 기간
        panel.add(new JLabel("등록 기간 (개월):"));
        registrationPeriodComboBox = new JComboBox<>(new Integer[]{1, 3, 6, 12});
        panel.add(registrationPeriodComboBox);

        // 만료일
        panel.add(new JLabel("만료일:"));
        expiryDateField = new JTextField();
        expiryDateField.setEditable(false);
        panel.add(expiryDateField);

        // 담당 프로
        panel.add(new JLabel("담당 프로:"));
        proComboBox = new JComboBox<>(new String[]{"A", "B", "C"});
        panel.add(proComboBox);

        // 연습장 이용
        panel.add(new JLabel("연습장 이용:"));
        practiceUseComboBox = new JComboBox<>(new String[]{"O", "X"});
        panel.add(practiceUseComboBox);

        // 골프 레슨
        panel.add(new JLabel("골프 레슨:"));
        golfLessonComboBox = new JComboBox<>(new String[]{"O", "X"});
        panel.add(golfLessonComboBox);

        // 결제 정보
        panel.add(new JLabel("결제 정보:"));
        paymentInfoComboBox = new JComboBox<>(new String[]{"카드", "현금", "기타"});
        panel.add(paymentInfoComboBox);

        // 가격
        panel.add(new JLabel("가격:"));
        priceField = new JTextField();
        panel.add(priceField);

        // 버튼 패널
        JButton confirmButton = new JButton("확인");
        confirmButton.setPreferredSize(new Dimension(100, 30));
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 데이터 처리 및 확인 메시지
                JOptionPane.showMessageDialog(null, "준비중입니다.");
            }
        });

        JButton cancelButton = new JButton("취소");
        cancelButton.setPreferredSize(new Dimension(100, 30));
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // 창 닫기
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        panel.add(buttonPanel);

        // 날짜 선택 후 만료일 자동 계산
        startDateChooser.addPropertyChangeListener(evt -> calculateExpiryDate());

        add(panel);
    }

    private void calculateExpiryDate() {
        if (startDateChooser.getDate() != null && registrationPeriodComboBox.getSelectedItem() != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDateChooser.getDate());
            int monthsToAdd = (Integer) registrationPeriodComboBox.getSelectedItem();
            calendar.add(Calendar.MONTH, monthsToAdd);
            Date expiryDate = calendar.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            expiryDateField.setText(sdf.format(expiryDate));
        }
    }
}

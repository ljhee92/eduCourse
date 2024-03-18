package eduCourse_prj.admin.design;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.util.ArrayList;
import java.util.List;

public class AdminAdminMgtDesign extends JDialog {
    AdminHomeDesign awd;
    List<JPanel> outerPanel1List; // outerPanel1에 속한 innerPanel 리스트를 저장하는 리스트
    List<JPanel> outerPanel2List; // outerPanel2에 속한 innerPanel 리스트를 저장하는 리스트
    JPanel outerPanel1;
    JPanel outerPanel2;
    int t = 0;
    JLabel jlBack ;//배경
    JLabel jlSistMark ;//쌍용마크
    JLabel jlSistTitle ;//쌍용타이틀
    JLabel jlTitleColor; // 수강신청 배경
    JLabel jlTitle1; // 수강신청 컬럼
    JLabel jlTitle2; // 바구니 컬럼
    JLabel jlTitle3; // 바구니 하단 컬럼
    JLabel jlTitle4; // 바구니 상단 컬럼
    
    

    public AdminAdminMgtDesign(AdminHomeDesign awd, String title) {
        super(awd, title, true);
        this.awd = awd;
        outerPanel1List = new ArrayList<>();
        outerPanel2List = new ArrayList<>();
        setLayout(null);

        outerPanel1 = new JPanel();

        outerPanel1.setLayout(new GridBagLayout()); // 외부패널1 GridBagLayout으로 변경

        ////////////////////////////////////////////////////////////////////////
        int numberOfInnerPanels1 = 10; // outerPanel1에 추가할 innerPanel의 수
        ///////////////////////////////////////////////////////////////////
        
        
        for (int i = 0; i < numberOfInnerPanels1; i++) {
            JPanel innerPanel = createInnerPanel(i + 1);
            outerPanel1List.add(innerPanel);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0; // innerPanel의 가로 크기를 outerPanel1에 맞춤
            gbc.insets = new Insets(5, 5, 5, 5); // 내부 간격 설정
            outerPanel1.add(innerPanel, gbc);
        }

        JScrollPane jsp1 = new JScrollPane(outerPanel1); // outerPanel1을 JScrollPane의 viewport에 추가
        jsp1.setBounds(15, 100, 950, 180);
        add(jsp1);

        outerPanel2 = new JPanel();
        outerPanel2.setLayout(new GridBagLayout()); // 외부패널2 GridBagLayout으로 변경

        JScrollPane jsp2 = new JScrollPane(outerPanel2); // outerPanel2을 JScrollPane의 viewport에 추가
        jsp2.setBounds(15, 350, 950, 200);
        add(jsp2);
        jlBack = new JLabel(new ImageIcon("C:/dev/image/common/back1.png"));
        jlBack.setBounds(0,0,984,620);
        
        jlSistMark = new JLabel(new ImageIcon("C:/dev/image/common/sistMark.png"));
        jlSistMark .setBounds(5,5,66,42);
        
        jlSistTitle = new JLabel("살려조쌍용대학교");
        jlSistTitle.setBounds(70,15,150,20);
        jlSistTitle.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD,20));
        jlSistTitle.setForeground(Color.white);
        
        
        jlTitleColor = new JLabel(new ImageIcon("C:/dev/image/admin/back.png"));
        jlTitleColor.setBounds(16, 44,949, 28);
        
        
        jlTitle1 = new JLabel(new ImageIcon("C:/dev/image/admin/title1.png"));
        jlTitle1.setBounds(16, 72,949, 28);
        
        jlTitle2 = new JLabel(new ImageIcon("C:/dev/image/admin/back1.png"));
        jlTitle2.setBounds(16, 294,949, 28);
        
        jlTitle3 = new JLabel(new ImageIcon("C:/dev/image/admin/back1.png"));
        jlTitle3.setBounds(16, 551,949, 40);
        
        jlTitle4 = new JLabel(new ImageIcon("C:/dev/image/admin/title1.png"));
        jlTitle4.setBounds(16, 322,949, 28);
        
        add(jlSistTitle);
        add(jlSistMark);
        
        add(jlTitle1);
        add(jlTitle2);
        add(jlTitle3);
        add(jlTitle4);
        
        add(jlTitleColor);
        add(jlBack);

        setSize(1000, 650);
        
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createInnerPanel(int panelNumber) {
        JPanel innerPanel = new JPanel(new GridBagLayout()); // GridBagLayout으로 변경

     // GridBagConstraints 설정
        GridBagConstraints gbcLabel = new GridBagConstraints();
        gbcLabel.gridx = 0;
        gbcLabel.gridy = 0;
        gbcLabel.weightx = 0.9; // 라벨이 한 행 내에서 버튼보다 약간 작도록 설정
        gbcLabel.fill = GridBagConstraints.HORIZONTAL; // 수평으로 확장되도록 설정
        gbcLabel.anchor = GridBagConstraints.WEST; // 왼쪽 정렬

        // 라벨 추가
        for (int i = 0; i < 6; i++) {
            JLabel label = new JLabel("Label " + (t + 1));
            t++;
            innerPanel.add(label, gbcLabel);
            innerPanel.setBackground(Color.lightGray);
            gbcLabel.gridx++; // 다음 열로 이동
        }

        // GridBagConstraints 설정
        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.gridx = 6;
        gbcButton.gridy = 0;
        gbcButton.fill = GridBagConstraints.HORIZONTAL; // 수평으로 확장되도록 설정
        gbcButton.weightx = 0.1; // 버튼이 innerPanel 내에서 수평으로 확장되도록 가로 크기 조정

        // 버튼 추가
        JButton moveButton = new JButton("담기");
        moveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton clickedButton = (JButton) e.getSource(); // 클릭된 버튼 가져오기
                JPanel parentPanel = (JPanel) clickedButton.getParent(); // 버튼이 속한 패널 가져오기

                if (outerPanel1List.contains(parentPanel)) {
                    // outerPanel1에서 outerPanel2로 이동
                    outerPanel2List.add(parentPanel);
                    JButton button = (JButton) clickedButton; // 클릭된 버튼을 가져옴
                    button.setText("취소"); // 버튼의 텍스트를 "취소"로 변경

                    outerPanel1List.remove(parentPanel);
                    outerPanel1.remove(parentPanel);

                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.gridx = 0;
                    gbc.gridy = outerPanel2List.size() - 1;
                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    gbc.weightx = 1.0; // innerPanel의 가로 크기를 outerPanel2에 맞춤
                    gbc.insets = new Insets(5, 5, 5, 5); // 내부 간격 설정
                    outerPanel2.add(parentPanel, gbc);
                } else if (outerPanel2List.contains(parentPanel)) {
                    // outerPanel2에서 outerPanel1로 이동
                    outerPanel1List.add(parentPanel);
                    JButton button = (JButton) clickedButton; // 클릭된 버튼을 가져옴
                    button.setText("담기"); // 버튼의 텍스트를 "담기"로 변경

                    outerPanel2List.remove(parentPanel);
                    outerPanel2.remove(parentPanel);

                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.gridx = 0;
                    gbc.gridy = outerPanel1List.size() - 1;
                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    gbc.weightx = 1.0; // innerPanel의 가로 크기를 outerPanel1에 맞춤
                    gbc.insets = new Insets(5, 5, 5, 5); // 내부 간격 설정
                    outerPanel1.add(parentPanel, gbc);
                }

                // 화면을 다시 그리기
                revalidate();
                repaint();
            }
        });
        innerPanel.add(moveButton, gbcButton);

        return innerPanel;
    }

}
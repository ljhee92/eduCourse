package eduCourse_prj.student.design;
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

public class StdntCrsRegDesign2 extends JDialog {
    StdntHomeDesign shd;
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
    
	JLabel jlTopLogin; // 우상단 로그인상태 확인창
	JLabel jlEnroll;//수강신청라벨
	JLabel jlDept1;//학과라벨
	JLabel jlCrs1;//과목라벨
	JLabel jlCrsCode1;//과목코드라벨
	JLabel jlLect1;//강의실라벨
	JLabel jlCapa1;//정원라벨
	JLabel jlCredit1;//학점라벨
	JLabel jlInCart1;//장바구니라벨
	
	JLabel jlCart;//장바구니라벨
	JLabel jlDept2;//학과라벨
	JLabel jlCrs2;//과목라벨
	JLabel jlCrsCode2;//과목코드라벨
	JLabel jlLect2;//강의실라벨
	JLabel jlCapa2;//정원라벨
	JLabel jlCredit2;//학점라벨
	JLabel jlInCart2;//장바구니라벨
	
	 
	
	
	JLabel jltotalcredit1;//학점라벨 라벨용
	JLabel jlSumCredit;//학점라벨 sum용 
	

	JButton jbtnEnroll ;//최종신청 버튼
	
	
    

    public StdntCrsRegDesign2(StdntHomeDesign shd, String title) {
    	
        super(shd, title,true);
        this.shd = shd; 
        
		String commonPath = "C:/dev/workspace/eduCourse_prj/src/eduCourse_prj/image/common/";
		String adminPath = "C:/dev/workspace/eduCourse_prj/src/eduCourse_prj/image/admin/";
        Font font = new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 15);

        outerPanel1List = new ArrayList<>();
        outerPanel2List = new ArrayList<>();
        setLayout(null);

        outerPanel1 = new JPanel();

        outerPanel1.setLayout(new GridBagLayout()); // 외부패널1 GridBagLayout으로 변경
        jlTopLogin = new JLabel(shd.getTopLogin().getText());
        jlTopLogin.setFont(font);
        jlTopLogin.setForeground(Color.WHITE);
        jlTopLogin.setBounds(720, 15, 200, 20);
        
        add(jlTopLogin);
        
    	jlEnroll = new JLabel("수강 신청");//수강신청라벨
    	jlEnroll.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 20));
    	jlEnroll.setBounds(450, 43, 100, 30);
    	add(jlEnroll);
    	
    	
    	jlDept1 = new JLabel("학과");//학과라벨
    	jlDept1.setFont(font);
    	jlDept1.setBounds(28,77,50,20);
    	add(jlDept1);
    	
    	jlCrs1 = new JLabel("과목");//과목라벨
    	jlCrs1.setFont(font);
    	jlCrs1.setBounds(170,77,50,20);
    	add(jlCrs1);
    	
    	jlCrsCode1 = new JLabel("과목코드");//과목코드라벨
    	jlCrsCode1.setFont(font);
    	jlCrsCode1.setBounds(300,77,70,20);
    	add(jlCrsCode1);
    	
    	jlLect1 = new JLabel("강의실");//강의실라벨
    	jlLect1.setFont(font);
    	jlLect1.setBounds(451,77,50,20);
    	add(jlLect1);
    	
    	jlCapa1 = new JLabel("정원");//정원라벨
    	jlCapa1.setFont(font);
    	jlCapa1.setBounds(590,77,50,20);
    	add(jlCapa1);
    	
    	jlCredit1 = new JLabel("학점");//학점라벨
    	jlCredit1.setFont(font);
    	jlCredit1.setBounds(735,77,50,20);
    	add(jlCredit1);
    	
    	jlInCart1 = new JLabel("장바구니");//장바구니라벨
    	jlInCart1.setFont(font);
    	jlInCart1.setBounds(880,77,80,20);
    	add(jlInCart1);    	
    	
		///////////////////////////////////////////////////////////
    	
    	
    	jlCart = new JLabel("장바구니");//수강신청라벨
    	jlCart.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 20));
    	jlCart.setBounds(450, 293, 100, 30);
    	add(jlCart);
    	
    	
    	
    	jlDept2 = new JLabel("학과");//학과라벨
    	jlDept2.setFont(font);
    	jlDept2.setBounds(28,328,50,20);
    	add(jlDept2);
    	
    	jlCrs2 = new JLabel("과목");//과목라벨
    	jlCrs2.setFont(font);
    	jlCrs2.setBounds(170,328,50,20);
    	add(jlCrs2);
    	
    	jlCrsCode2 = new JLabel("과목코드");//과목코드라벨
    	jlCrsCode2.setFont(font);
    	jlCrsCode2.setBounds(300,328,70,20);
    	add(jlCrsCode2);
    	
    	jlLect2 = new JLabel("강의실");//강의실라벨
    	jlLect2.setFont(font);
    	jlLect2.setBounds(451,328,50,20);
    	add(jlLect2);
    	
    	jlCapa2 = new JLabel("정원");//정원라벨
    	jlCapa2.setFont(font);
    	jlCapa2.setBounds(590,328,50,20);
    	add(jlCapa2);
    	
    	jlCredit2 = new JLabel("학점");//학점라벨
    	jlCredit2.setFont(font);
    	jlCredit2.setBounds(735,328,50,20);
    	add(jlCredit2);
    	
    	jlInCart2 = new JLabel("장바구니");//장바구니라벨
    	jlInCart2.setFont(font);
    	jlInCart2.setBounds(880,328,80,20);
    	add(jlInCart2);   
		
    	
    	jltotalcredit1 = new JLabel("총 학점");//학점라벨 라벨용
    	jltotalcredit1.setFont(font);
    	jltotalcredit1.setBounds(750,563,80,20);
    	add(jltotalcredit1);
    	
    	jlSumCredit = new JLabel(("0"));//학점라벨 sum용 
    	jlSumCredit.setFont(font);
    	jlSumCredit.setBounds(820,563,80,20);
    	add(jlSumCredit);
    	
    	
    	jbtnEnroll = new JButton("최종신청");
    	jbtnEnroll.setBounds(870,555,90,30);
    	add(jbtnEnroll);   
		
		
		
		


        
        
        
        
        
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
        jlBack = new JLabel(new ImageIcon(commonPath+"back1.png"));
        jlBack.setBounds(0,0,984,620);
        
        jlSistMark = new JLabel(new ImageIcon(commonPath+"sistMark.png"));
        jlSistMark .setBounds(5,5,66,42);
        
        jlSistTitle = new JLabel("살려조쌍용대학교");
        jlSistTitle.setBounds(70,15,150,20);
        jlSistTitle.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD,20));
        jlSistTitle.setForeground(Color.white);
        
        
        
        jlTitleColor = new JLabel(new ImageIcon(adminPath+"back.png"));
        jlTitleColor.setBounds(16, 44,949, 28);
        
        jlTitle1 = new JLabel(new ImageIcon(adminPath+"title1.png"));
        jlTitle1.setBounds(16, 72,949, 28);
        
        jlTitle2 = new JLabel(new ImageIcon(adminPath+"back1.png"));
        jlTitle2.setBounds(16, 294,949, 28);
        
        jlTitle3 = new JLabel(new ImageIcon(adminPath+"back1.png"));
        jlTitle3.setBounds(16, 551,949, 40);
        
        jlTitle4 = new JLabel(new ImageIcon(adminPath+"title1.png"));
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
package eduCourse_prj.student.design;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import eduCourse_prj.student.event.StdntTestPageEvent;

public class StdntTestPageDesign extends JDialog {
	
	private StdntTestSlctDesign stsd;
    private JLabel jlBack; //배경
    private JLabel topLogin; // 우상단 로그인상태 확인창
    private JLabel jlTestSlct, jlTestPage;
    private JButton jbtnSubmit;
    private JTable jtbTestPage;
    private DefaultTableModel dtmTestPage;
    
    public StdntTestPageDesign(StdntTestSlctDesign stsd, String title) {
    	super(stsd, title, true);
		this.stsd = stsd;
		
		setLayout(null);
		
		String commonPath = "src/eduCourse_prj/image/common/";
		String stdntPath = "src/eduCourse_prj/image/stud/";
		
		// 우상단 로그인상태 확인창 추가
		topLogin = new JLabel(stsd.getShd().getlVO().getName() + " 학생님 로그인 중");
		Font font = new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 15);
		topLogin.setFont(font);
		topLogin.setForeground(Color.WHITE);
		topLogin.setBounds(670, 30, 200, 20);
		add(topLogin);
		
		// 시험응시및결과, 시험 응시 상단 라벨 추가
		jlTestSlct = new JLabel(new ImageIcon(stdntPath + "TestSlct_Label.png"));
		jlTestPage = new JLabel(new ImageIcon(stdntPath + "TestPage_Label.png"));
	
		jlTestSlct.setBounds(10, 76, 967, 34);
		jlTestPage.setBounds(10, 110, 967, 43);
		
		add(jlTestSlct);
		add(jlTestPage);
		
		// 제출 버튼 추가
		jbtnSubmit = new JButton(new ImageIcon(stdntPath + "Submit.png"));
		jbtnSubmit.setBounds(450, 520, 95, 50);
		add(jbtnSubmit);
		
		// 테이블 추가
		String[] testPageColumn = {"번호", "문제", "답"};
		
		dtmTestPage = new DefaultTableModel(testPageColumn, 0) {
			public boolean isCellEditable(int row, int column){
			    return column == 2; // "답" 열만 셀 수정 가능하도록 설정
			} // isCellEditable
		};
		
		jtbTestPage = new JTable(dtmTestPage);
		JScrollPane jsp = new JScrollPane(jtbTestPage);
		
		////// 답 열만 셀 수정 가능한지 테스트용 ////
		Object[] test = {"1", "문제임", ""};
		dtmTestPage.addRow(test);
		/////////////////////////////////
		
		// 테이블 컬럼 가운데 정렬
		setTbHorizontal();
		
		jsp.setBounds(10, 150, 967, 250);
		add(jsp);
		
		// 테이블에 시험 문제 번호와 추가
		slctTestContent();
		
		// 이벤트 클래스 연결
		StdntTestPageEvent stpe = new StdntTestPageEvent(this);
		addWindowFocusListener(stpe);
		jbtnSubmit.addActionListener(stpe);
		
		// 배경 추가
        jlBack = new JLabel(new ImageIcon(commonPath + "Back.png"));
        jlBack.setBounds(0,0,984,620);
        add(jlBack);
		setSize(1000,650);
		setLocationRelativeTo(null);
		setVisible(true);
    } // StdntTestPageDesign

    /**
	 * 테이블의 컬럼을 가운데 정렬
	 */
	public void setTbHorizontal() {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm1 = jtbTestPage.getColumnModel();
		for(int i = 0; i < tcm1.getColumnCount(); i++) {
			tcm1.getColumn(i).setCellRenderer(dtcr);
		} // end for
	} // setTbHorizontal
	
	/**
	 * 테이블에 시험 문제 번호와 문제를 추가하는 method
	 */
	public void slctTestContent() {
		
	} // slctTestContent
    
	public StdntTestSlctDesign getStsd() {
		return stsd;
	}

	public JButton getJbtnSubmit() {
		return jbtnSubmit;
	}

	public JTable getJtbTestPage() {
		return jtbTestPage;
	}

	public DefaultTableModel getDtmTestPage() {
		return dtmTestPage;
	}
	
} // class
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

import eduCourse_prj.student.event.StdntCrsSlctEvent;

public class StdntCrsSlctDesign extends JDialog {
	
	private StdntHomeDesign shd;
    private JLabel jlBack; //배경
    private JLabel topLogin; // 우상단 로그인상태 확인창
    private JLabel jlCrsSlct, jlAllCreditTxt, jlAllCreditHour;
    private JButton jbtnOK;
    private JTable jtbCrsSlct;
    private DefaultTableModel dtmCrsSlct;
    
    public StdntCrsSlctDesign(StdntHomeDesign shd, String title) {
    	super(shd, title, true);
		this.shd = shd;
		
		setLayout(null);
		
		String commonPath = "src/eduCourse_prj/image/common/";
		String stdntPath = "src/eduCourse_prj/image/stud/";
		
		// 우상단 로그인상태 확인창 추가
		topLogin = new JLabel(shd.getlVO().getName() + " 학생님 로그인 중");
		Font font = new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 15);
		topLogin.setFont(font);
		topLogin.setForeground(Color.RED);
		topLogin.setBounds(670, 30, 200, 20);
		add(topLogin);
		
		// 수강신청내역 상단 라벨 추가
		jlCrsSlct = new JLabel(new ImageIcon(stdntPath + "CrsSlct_Label.png"));
		jlCrsSlct.setBounds(10, 76, 967, 34);
		add(jlCrsSlct);
		
		// 총학점 하단 라벨 추가
		jlAllCreditTxt = new JLabel("총 학점 : ");
		jlAllCreditHour = new JLabel("0");
		
		jlAllCreditTxt.setFont(font);
		jlAllCreditHour.setFont(font);
		jlAllCreditTxt.setForeground(Color.RED);
		jlAllCreditHour.setForeground(Color.RED);
		
		jlAllCreditTxt.setBounds(850, 450, 80, 30);
		jlAllCreditHour.setBounds(940, 450, 40, 30);
		
		add(jlAllCreditTxt);
		add(jlAllCreditHour);
		
		// 확인 버튼 추가
		jbtnOK = new JButton(new ImageIcon(commonPath + "Ok_s.png"));
		jbtnOK.setBounds(450, 520, 95, 50);
		add(jbtnOK);
		
		// 테이블 추가
		String[] crsSlctColumn = {"학과", "과목", "과목코드", "강의실", "정원", "학점", "담당교수"};
		
		dtmCrsSlct = new DefaultTableModel(crsSlctColumn, 0) {
			public boolean isCellEditable(int row, int column){
			    return false; // 테이블 셀 수정 불가하도록 설정
			} // isCellEditable
		};
		
		jtbCrsSlct = new JTable(dtmCrsSlct);
		JScrollPane jsp = new JScrollPane(jtbCrsSlct);
		
		// 테이블 컬럼 가운데 정렬
		setTbHorizontal();
		
		jsp.setBounds(10, 110, 967, 330);
		add(jsp);
		
		// 이벤트 클래스 연결
		StdntCrsSlctEvent scse = new StdntCrsSlctEvent(this);
		addWindowListener(scse);
		jbtnOK.addActionListener(scse);
		
		// 배경 추가
        jlBack = new JLabel(new ImageIcon(commonPath + "Back.png"));
        jlBack.setBounds(0,0,984,620);
        add(jlBack);
		setSize(1000,650);
		setLocationRelativeTo(null);
		setVisible(true);
    } // StdntCrsSlctDesign
    
    /**
	 * 테이블의 컬럼을 가운데 정렬
	 */
	public void setTbHorizontal() {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm1 = jtbCrsSlct.getColumnModel();
		for(int i = 0; i < tcm1.getColumnCount(); i++) {
			tcm1.getColumn(i).setCellRenderer(dtcr);
		} // end for
	} // setTbHorizontal

	public StdntHomeDesign getShd() {
		return shd;
	}

	public JLabel getJlAllCreditHour() {
		return jlAllCreditHour;
	}

	public JButton getJbtnOK() {
		return jbtnOK;
	}

	public JTable getJtbCrsSlct() {
		return jtbCrsSlct;
	}

	public DefaultTableModel getDtmCrsSlct() {
		return dtmCrsSlct;
	}

} // class
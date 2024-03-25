package eduCourse_prj.student.design;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import eduCourse_prj.VO.DeptVO;
import eduCourse_prj.VO.StdntTestVO;
import eduCourse_prj.student.dao.StdntDAO;
import eduCourse_prj.student.dao.StdntTestDAO;
import eduCourse_prj.student.event.StdntTestAnswerEvent;

public class StdntTestAnswerDesign extends JDialog {

	private StdntHomeDesign shd;
    private JLabel jlBack; //배경
    private JLabel topLogin; // 우상단 로그인상태 확인창
    private JLabel jlTestAnswer, jlCrs;
    private JComboBox<String> jcbCrs;
    private JButton jbtnSlct, jbtnOk;
    private JTable jtbTestAnswerLeft, jtbTestAnswerRight;
    private DefaultTableModel dtmTestAnswerLeft, dtmTestAnswerRight;
    
    public StdntTestAnswerDesign(StdntHomeDesign shd, String title) {
    	super(shd, title, true);
		this.shd = shd;
		
		setLayout(null);
		
		String commonPath = "src/eduCourse_prj/image/common/";
		String stdntPath = "src/eduCourse_prj/image/stud/";
		
		// 우상단 로그인상태 확인창 추가
		topLogin = new JLabel(shd.getlVO().getName() + " 학생님 로그인 중");
		Font font = new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 15);
		topLogin.setFont(font);
		topLogin.setForeground(Color.WHITE);
		topLogin.setBounds(670, 30, 200, 20);
		add(topLogin);
		
		// 시험 정오표 상단 라벨 추가
		jlTestAnswer = new JLabel(new ImageIcon(stdntPath + "TestAnswer_Label.png"));
		jlTestAnswer.setBounds(10, 76, 967, 34);
		add(jlTestAnswer);
		
		// 과목명 라벨, 콤보박스, 선택 버튼 추가
		jlCrs = new JLabel("과목명");
		jcbCrs = new JComboBox<String>();
		jbtnSlct = new JButton(new ImageIcon(commonPath + "Slct2_s.png"));
		
		jcbCrs.addItem("테스트1");
		jcbCrs.addItem("테스트2");
		jcbCrs.addItem("테스트3");
		
		jlCrs.setFont(font);
		jcbCrs.setFont(font);
		
		jlCrs.setBounds(250, 155, 50, 30);
		jcbCrs.setBounds(350, 155, 250, 30);
		jbtnSlct.setBounds(650, 155, 57, 30);
		
		add(jlCrs);
		add(jcbCrs);
		add(jbtnSlct);
		
		// 확인 버튼 추가
		jbtnOk = new JButton(new ImageIcon(commonPath + "OK_sblue.png"));
		jbtnOk.setBounds(445, 480, 95, 50);
		add(jbtnOk);
		
		// 테이블 추가
		String[] testAnswerLColumn = {"문제번호", "답안", "나의 답안"};
		String[] testAnswerRColumn = {"문제번호", "답안", "나의 답안"};
		
		dtmTestAnswerLeft = new DefaultTableModel(testAnswerLColumn, 0) {
			public boolean isCellEditable(int row, int column){
			    return false; // 테이블 셀 수정 불가하도록 설정
			} // isCellEditable
		};
		
		dtmTestAnswerRight = new DefaultTableModel(testAnswerRColumn, 0) {
			public boolean isCellEditable(int row, int column){
				return false; // 테이블 셀 수정 불가하도록 설정
			} // isCellEditable
		};
		
		jtbTestAnswerLeft = new JTable(dtmTestAnswerLeft);
		jtbTestAnswerRight = new JTable(dtmTestAnswerRight);
		
		JScrollPane jspL = new JScrollPane(jtbTestAnswerLeft);
		JScrollPane jspR = new JScrollPane(jtbTestAnswerRight);
		
		// 테이블 컬럼 가운데 정렬
		setTbHorizontal();
		
		jtbTestAnswerLeft.setRowHeight(30); // 행 높이 조절
		jtbTestAnswerRight.setRowHeight(30); // 행 높이 조절
		
		jspL.setBounds(190, 225, 300, 230);
		jspR.setBounds(500, 225, 300, 230);
		
		add(jspL);
		add(jspR);
		
		// 이벤트 클래스 연결
		StdntTestAnswerEvent stae = new StdntTestAnswerEvent(this);
		addWindowListener(stae);
		jbtnSlct.addActionListener(stae);
		jbtnOk.addActionListener(stae);
		
		// 배경 추가
        jlBack = new JLabel(new ImageIcon(commonPath + "Back.png"));
        jlBack.setBounds(0,0,984,620);
        add(jlBack);
		setSize(1000,650);
		setLocationRelativeTo(null);
		setVisible(true);
    } // StdntTestAnswerDesign
    
    /**
   	 * 테이블의 컬럼을 가운데 정렬
   	 */
   	public void setTbHorizontal() {
   		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
   		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
   		TableColumnModel tcm1 = jtbTestAnswerLeft.getColumnModel();
   		for(int i = 0; i < tcm1.getColumnCount(); i++) {
   			tcm1.getColumn(i).setCellRenderer(dtcr);
   		} // end for
   		
   		DefaultTableCellRenderer dtcr2 = new DefaultTableCellRenderer();
   		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
   		TableColumnModel tcm2 = jtbTestAnswerRight.getColumnModel();
   		for(int i = 0; i < tcm2.getColumnCount(); i++) {
   			tcm1.getColumn(i).setCellRenderer(dtcr);
   		} // end for
   	} // setTbHorizontal
   	
	/**
	 * DB에서 모든 학과 정보를 가져오는 method
	 */
//	public void seltAllDept() {
//	
//	try {// 학과
//
//		// 모든 학과 정보 가져오기
//		StdntTestDAO stDAO = StdntTestDAO.getInstance();
//		stDAO.slctAllCrs();
//
//		// "전체 아이템 추가"
//		jcbDept.addItem("전체");
//
//		// 학과명만 저장하는 리스트에 학과명 저장
//		for (DeptVO dept : lDept) {
//			jcbDept.addItem(dept.getDept_name());
//
//		}
//
//	} catch (SQLException e) {
//		e.printStackTrace();
//	}
//	
//	}

	public StdntHomeDesign getShd() {
		return shd;
	}

	public JComboBox<String> getJcbCrs() {
		return jcbCrs;
	}

	public JButton getJbtnSlct() {
		return jbtnSlct;
	}

	public JButton getJbtnOk() {
		return jbtnOk;
	}

	public JTable getJtbTestAnswerLeft() {
		return jtbTestAnswerLeft;
	}

	public JTable getJtbTestAnswerRight() {
		return jtbTestAnswerRight;
	}

	public DefaultTableModel getDtmTestAnswerLeft() {
		return dtmTestAnswerLeft;
	}

	public DefaultTableModel getDtmTestAnswerRight() {
		return dtmTestAnswerRight;
	}
    
} // class
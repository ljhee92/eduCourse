package eduCourse_prj.professor.design;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import eduCourse_prj.professor.event.ProfTestMgtEvent;

public class ProfTestMgtDesign extends JDialog{
	ProfHomeDesign phd;
	private	JLabel jlBack;// 배경
	private JLabel topLogin; // 우상단 로그인상태 확인창
	private JLabel adminMgt;

	private DefaultTableModel dtmTestMgt;
	private JTable jtbTestMgt;

	private JRadioButton jrbtnEnable , jrbtnDisable;
	private JButton jbtnTestMdfy,jbtnTestReg;

	
	public ProfTestMgtDesign(ProfHomeDesign phd, String title) {
		super(phd,title,true);
		this.phd = phd;
		
		String commonPath = "C:/dev/workspace/eduCourse_prj/src/eduCourse_prj/image/common/";
		String profPath = "C:/dev/workspace/eduCourse_prj/src/eduCourse_prj/image/prof/";
		String srsPath = "C:/dev/workspace/eduCourse_prj/src/eduCourse_prj/image/crs/";
		
		jlBack = new JLabel(new ImageIcon(commonPath + "back.png"));
		jlBack.setBounds(0, 0, 984, 620);
		add(jlBack);
		
		// 강의 과목 관리 라벨 추가
		adminMgt = new JLabel(new ImageIcon(profPath + "TestMgt.png"));
		adminMgt.setBounds(10, 76, 967, 44);
		add(adminMgt);

		// 우상단 로그인상태 확인창 추가
		topLogin = new JLabel(phd.getlVO().getName() + " 관리자님 로그인 중");
		Font font = new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 15);
		topLogin.setFont(font);
		topLogin.setForeground(Color.RED);
		topLogin.setBounds(670, 30, 200, 20);
		add(topLogin);
		
		// 테이블 추가
		String[] tempColumn = { "학과", "과목" };
		dtmTestMgt = new DefaultTableModel(tempColumn, 0) {
			public boolean isCellEditable(int row, int column) {
				return false; // 테이블 셀 수정 불가하도록 설정
			} // isCellEditable
		};
		
		jtbTestMgt = new JTable(dtmTestMgt);
		JScrollPane jsp = new JScrollPane(jtbTestMgt);

		jtbTestMgt.setRowHeight(30); // 행 높이 조절
		jsp.setBounds(10, 120, 967, 350);
		add(jsp);
		

		////////////////////////////////////////////////////////////////////
		//시험 활성화,비활성화, 출제, 수정버튼 추가
		jbtnTestReg = new JButton(new ImageIcon(srsPath + "CrsReg.png"));
		jbtnTestMdfy = new JButton(new ImageIcon(commonPath +"Mdfy.png"));
		
		jrbtnEnable = new JRadioButton("활성화");
		jrbtnDisable = new JRadioButton("비활성화");
		
		
		jbtnTestReg.setBounds(550,500,111,59);
		jbtnTestMdfy.setBounds(700, 500, 111, 59);
		jrbtnEnable.setBounds(200, 500, 120,80);
		jrbtnDisable.setBounds(350, 500,120,80 );
		jrbtnEnable.setBackground(Color.WHITE); // 활성화된 라디오 버튼의 배경색을 흰색으로 설정
		jrbtnDisable.setBackground(Color.WHITE); // 활성화된 라디오 버튼의 배경색을 흰색으로 설정

		add(jbtnTestReg);
		add(jbtnTestMdfy);
		add(jrbtnEnable);
		add(jrbtnDisable);

		// 테이블에 DB 추가
//		slctLecMgt();

		// 테이블 컬럼 가운데 정렬
		setTbHorizontal();

		// 이벤트 클래스 연결

		add(jlBack);

		ProfTestMgtEvent pme = new ProfTestMgtEvent(this);
		addWindowListener(pme);
		jbtnTestReg.addActionListener(pme);
		jbtnTestMdfy.addActionListener(pme);
		
		setLayout(null);
		setSize(1000,650);		
		setLocationRelativeTo(null);
		setVisible(true);
		
		
	}
	
	/**
	 * 테이블의 컬럼을 가운데 정렬
	 */
	public void setTbHorizontal() {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = jtbTestMgt.getColumnModel();
		for (int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		} // end for
	} // setTbHorizontal

	public JRadioButton getJrbtnEnable() {
		return jrbtnEnable;
	}

	public JRadioButton getJrbtnDisable() {
		return jrbtnDisable;
	}

	public JButton getJbtnTestMdfy() {
		return jbtnTestMdfy;
	}

	public JButton getJbtnTestReg() {
		return jbtnTestReg;
	}
	
	
	
	
}

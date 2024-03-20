package eduCourse_prj.professor.design;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.List;

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

import eduCourse_prj.VO.CrsVO;
import eduCourse_prj.professor.dao.ProfDAO;
import eduCourse_prj.professor.event.ProfCrsMgtEvent;

public class ProfCrsMgtDesign extends JDialog {
	ProfHomeDesign phd;

	JLabel jlBack;// 배경
	private JLabel topLogin; // 우상단 로그인상태 확인창
	private JLabel adminMgt;

	private JTable jtbLecMgt;
	private DefaultTableModel dtmProfMgt;

	private JButton jbtnLecReg, jbtnSlct, jbtnMdfy, jbtnDel;


	public ProfCrsMgtDesign(ProfHomeDesign phd, String title) {
		super(phd,title,true);
		this.phd = phd;

		String commonPath = "src/eduCourse_prj/image/common/";
		String profPath = "src/eduCourse_prj/image/prof/";
		String crsPath = "src/eduCourse_prj/image/crs/";

		jlBack = new JLabel(new ImageIcon(commonPath + "back.png"));
		jlBack.setBounds(0, 0, 984, 620);
		add(jlBack);

		// 강의 과목 관리 라벨 추가
		adminMgt = new JLabel(new ImageIcon(profPath + "ProfCrsMgt.png"));
		adminMgt.setBounds(10, 76, 967, 44);
		add(adminMgt);

		// 우상단 로그인상태 확인창 추가
		topLogin = new JLabel(phd.getlVO().getName() + " 교수님 로그인 중");
		Font font = new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 15);
		topLogin.setFont(font);
		topLogin.setForeground(Color.WHITE);
		topLogin.setBounds(670, 30, 200, 20);
		add(topLogin);

		// 테이블 추가
		String[] tempColumn = { "학과", "과목" };
		dtmProfMgt = new DefaultTableModel(tempColumn, 0) {
			public boolean isCellEditable(int row, int column) {
				return false; // 테이블 셀 수정 불가하도록 설정
			} // isCellEditable
		};

		jtbLecMgt = new JTable(dtmProfMgt);
		JScrollPane jsp = new JScrollPane(jtbLecMgt);

		jtbLecMgt.setRowHeight(30); // 행 높이 조절
		jsp.setBounds(10, 120, 967, 350);
		add(jsp);

		// 과목등록, 조회, 삭제 버튼 추가
		jbtnLecReg = new JButton(new ImageIcon(crsPath + "crsRegBtn_new.png"));
		jbtnSlct = new JButton(new ImageIcon(commonPath + "Slct.png"));
		jbtnMdfy = new JButton(new ImageIcon(commonPath +"Mdfy.png"));
		jbtnDel = new JButton(new ImageIcon(commonPath + "Del.png"));

		jbtnLecReg.setBounds(150, 500, 142, 50);
		jbtnSlct.setBounds(400, 500, 111, 59);
		jbtnMdfy.setBounds(550,500,111,59);
		jbtnDel.setBounds(700, 500, 111, 59);

		add(jbtnLecReg);
		add(jbtnSlct);
		add(jbtnMdfy);
		add(jbtnDel);

		// 테이블에 DB 추가
		slctLecMgt();

		// 테이블 컬럼 가운데 정렬
		setTbHorizontal();

		// 이벤트 클래스 연결

		add(jlBack);

		ProfCrsMgtEvent pme = new ProfCrsMgtEvent(this);
		addWindowListener(pme);
		jbtnLecReg.addActionListener(pme);
		jbtnSlct.addActionListener(pme);
		jbtnMdfy.addActionListener(pme);
		jbtnDel.addActionListener(pme);

		setLayout(null);
		setSize(1000,650);		
		setLocationRelativeTo(null);
		setVisible(true);
	}

	//학과와 과목을 테이블에 보여주는 메서드
	public void slctLecMgt() {
		ProfDAO pDAO = ProfDAO.getInstance(); 
		try {
			String strProf_number = phd.getlVO().getId();
			int prof_number = Integer.parseInt(strProf_number);
			List<CrsVO> listCrsVO= pDAO.slctProfLec(prof_number);			
			for (CrsVO cVO1 : listCrsVO) {
				Object[] row = { cVO1.getDeptName(), cVO1.getCourName() };
				dtmProfMgt.addRow(row);
			} // end for
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch
	} // slctCrsMgt

	/**
	 * 테이블의 컬럼을 가운데 정렬
	 */
	public void setTbHorizontal() {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = jtbLecMgt.getColumnModel();
		for (int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		} // end for
	} // setTbHorizontal

	public ProfHomeDesign getPhd() {
		return phd;
	}

	public JButton getJbtnLecReg() {
		return jbtnLecReg;
	}

	public JButton getJbtnSlct() {
		return jbtnSlct;
	}

	public JButton getJbtnMdfy() {
		return jbtnMdfy;
	}

	public JButton getJbtnDel() {
		return jbtnDel;
	}
	
	

}
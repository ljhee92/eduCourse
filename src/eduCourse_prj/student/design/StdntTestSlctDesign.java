package eduCourse_prj.student.design;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import javax.swing.table.TableColumnModel;

import eduCourse_prj.VO.ScoreVO;
import eduCourse_prj.VO.StdntTestVO;
import eduCourse_prj.student.dao.StdntTestDAO;
import eduCourse_prj.student.event.StdntTestSlctEvent;

@SuppressWarnings("serial")
public class StdntTestSlctDesign extends JDialog {

	private StdntHomeDesign shd;
	private JLabel jlBack; // 배경
	private JLabel topLogin; // 우상단 로그인상태 확인창
	private JLabel jlTestSlct, jlAllCreditTxt, jlAllCreditHour;
	private JButton jbtnTest, jbtnOK;
	private JTable jtbTestSlct;
	private DefaultTableModel dtmTestSlct;
	private Object rowGrade, rowTestFlag, rowScore;

	public StdntTestSlctDesign(StdntHomeDesign shd, String title) {
		super(shd, title, true);
		this.shd = shd;

		setLayout(null);

		String commonPath = "src/eduCourse_prj/image/common/";
		String stdntPath = "src/eduCourse_prj/image/stud/";

		// 우상단 로그인상태 확인창 추가
		topLogin = new JLabel(shd.getlVO().getName() + " 학생님 로그인 중");
		Font font = new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 17);
		topLogin.setFont(font);
		topLogin.setForeground(Color.WHITE);
		topLogin.setBounds(670, 30, 200, 20);
		add(topLogin);

		// 시험응시및결과 상단 라벨 추가
		jlTestSlct = new JLabel(new ImageIcon(stdntPath + "TestSlct_Label.png"));
		jlTestSlct.setBounds(10, 76, 967, 34);
		add(jlTestSlct);

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

		// 응시, 확인 버튼 추가
		jbtnTest = new JButton(new ImageIcon(stdntPath + "Test.png"));
		jbtnOK = new JButton(new ImageIcon(commonPath + "Ok_s.png"));

		jbtnTest.setBounds(380, 520, 95, 50);
		jbtnOK.setBounds(520, 520, 95, 50);

		add(jbtnTest);
		add(jbtnOK);

		// 테이블 추가
		String[] testSlctColumn = { "학과", "과목", "과목코드", "담당교수", "응시가능여부", "점수", "성취도" };

		dtmTestSlct = new DefaultTableModel(testSlctColumn, 0) {

			public boolean isCellEditable(int row, int column) {
				return false; // 테이블 셀 수정 불가하도록 설정
			} // isCellEditable
		};

		jtbTestSlct = new JTable(dtmTestSlct);
		JScrollPane jsp = new JScrollPane(jtbTestSlct);

		// 테이블 컬럼 가운데 정렬
		setTbHorizontal();

		jsp.setBounds(10, 110, 967, 330);
		add(jsp);

		// 테이블과 하단 학점 라벨에 DB 추가
		slctTestReg();

		// 이벤트 클래스 연결
		StdntTestSlctEvent stsc = new StdntTestSlctEvent(this);
		addWindowListener(stsc);
		jbtnTest.addActionListener(stsc);
		jbtnOK.addActionListener(stsc);

		// 배경 추가
		jlBack = new JLabel(new ImageIcon(commonPath + "Back.png"));
		jlBack.setBounds(0, 0, 984, 620);
		add(jlBack);
		setSize(1000, 650);
		setLocationRelativeTo(null);
		setVisible(true);
		// 프레임크기 조절 불가
		setResizable(false);
	} // StdntTestSlctDesign

	/**
	 * 테이블의 컬럼을 가운데 정렬
	 */
	public void setTbHorizontal() {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm1 = jtbTestSlct.getColumnModel();
		for (int i = 0; i < tcm1.getColumnCount(); i++) {
			tcm1.getColumn(i).setCellRenderer(dtcr);
		} // end for
	} // setTbHorizontal

	/**
	 * 테이블에 시험 응시를 위한 수강 과목을 추가하고, 하단 학점 라벨에 학점의 합을 추가하는 method
	 */
	public void slctTestReg() {
		StdntTestDAO stDAO = StdntTestDAO.getInstance();

		int stdnt_number = Integer.parseInt(shd.getlVO().getId());

		try {
			List<StdntTestVO> listSTVO = stDAO.slctAllStdntTestList(stdnt_number);
			int totalCredit = 0;

			for (StdntTestVO stVO : listSTVO) {

				rowTestFlag = showTestFlag(stdnt_number, stVO); // 점수 유무에 따른 시험활성화 유무
				rowScore = showScore(stdnt_number, stVO); // VO의 점수 유무에 따른 점수 표기
				rowGrade = showGrade(stVO, rowScore); // VO의 점수와 테이블에 표기된 점수에 따른 성취도 표기

				Object[] row = { stVO.getDept_name(), stVO.getCourse_name(), stVO.getCourse_code(), stVO.getProf_name(),
						rowTestFlag, rowScore, rowGrade };

				dtmTestSlct.addRow(row);

				totalCredit += stVO.getCredit_hours();
			} // end for
			jlAllCreditHour.setText(String.valueOf(totalCredit));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(shd, "SQL 문제가 발생했습니다.");
			e.printStackTrace();
		} // end catch
	} // slctTestReg

	/**
	 * 시험에 응시했는지 여부를 판단하여 TestFlag를 결정하는 method
	 * 
	 * @param course_codes
	 * @return
	 * @throws SQLException
	 */
	private Object showTestFlag(int stdnt_number, StdntTestVO stVO) throws SQLException {
		Object rowTestFlag = stVO.getTest_flag();

		StdntTestDAO stDAO = StdntTestDAO.getInstance();

		ScoreVO sVO = stDAO.slctScore(stdnt_number, stVO.getCourse_code());
		if (sVO != null) {
			rowTestFlag = "N";
		} // end rowTestFlag

		return rowTestFlag;
	} // showTestFlag

	/**
	 * VO의 점수 유무에 따라 테이블에 표기되는 점수를 결정하는 method
	 * 
	 * @param stVO
	 * @return
	 * @throws SQLException
	 */
	private Object showScore(int stdnt_number, StdntTestVO stVO) throws SQLException {
		Object rowScore = "";

		StdntTestDAO stDAO = StdntTestDAO.getInstance();

		ScoreVO sVO = stDAO.slctScore(stdnt_number, stVO.getCourse_code());
		if (sVO != null) {
			rowScore = stVO.getScore();
		} // end if

		return rowScore;
	} // showScore

	/**
	 * VO의 점수에 따라 성취도를 결정하는 method
	 * 
	 * @param stVO
	 * @return
	 */
	private Object showGrade(StdntTestVO stVO, Object rowScore) {
		Object rowGrade = "";
		
		if (stVO.getScore() >= 95) {
			rowGrade = "A+";
		} else if (stVO.getScore() >= 90) {
			rowGrade = "A";
		} else if (stVO.getScore() >= 85) {
			rowGrade = "B+";
		} else if (stVO.getScore() >= 80) {
			rowGrade = "B";
		} else if (stVO.getScore() >= 75) {
			rowGrade = "C+";
		} else if (stVO.getScore() >= 70) {
			rowGrade = "C";
		} else if (stVO.getScore() >= 65) {
			rowGrade = "D+";
		} else if (stVO.getScore() >= 60) {
			rowGrade = "D";
		} else if (stVO.getTest_flag().equals("N") && stVO.getScore() == 0) {
			rowGrade = "";
		} else if (rowScore.equals("")) {
			rowGrade = "";
		} else {
			rowGrade = "F";
		} // end else
		
		return rowGrade;
	} // showGrade

	public StdntHomeDesign getShd() {
		return shd;
	}

	public JLabel getJlAllCreditHour() {
		return jlAllCreditHour;
	}

	public JButton getJbtnTest() {
		return jbtnTest;
	}

	public JButton getJbtnOK() {
		return jbtnOK;
	}

	public JTable getJtbTestSlct() {
		return jtbTestSlct;
	}

	public DefaultTableModel getDtmTestSlct() {
		return dtmTestSlct;
	}

} // class
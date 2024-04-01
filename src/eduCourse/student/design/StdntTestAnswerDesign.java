package eduCourse.student.design;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;

import java.util.List;

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

import eduCourse.VO.SubStdntAnswerVO;
import eduCourse.student.dao.StdntAnswerDAO;

import eduCourse.student.event.StdntTestAnswerEvent;

@SuppressWarnings("serial")
public class StdntTestAnswerDesign extends JDialog {

	private StdntHomeDesign shd;
	private JLabel jlBack; // 배경
	private JLabel topLogin; // 우상단 로그인상태 확인창
	private JLabel jlTestAnswer, jlCrs;
	private JComboBox<String> jcbCrs;
	private JButton jbtnSlct, jbtnOk;
	private JTable jtbTestAnswer;
	private DefaultTableModel dtmTestAnswer;
	private List<SubStdntAnswerVO> crsList = null;

	public StdntTestAnswerDesign(StdntHomeDesign shd, String title) {
		super(shd, title, true);
		this.shd = shd;

		setLayout(null);

		String commonPath = "src/eduCourse/image/common/";
		String stdntPath = "src/eduCourse/image/stud/";

		// 우상단 로그인상태 확인창 추가
		topLogin = new JLabel(shd.getlVO().getName() + " 학생님 로그인 중");
		Font font = new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 17);
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
		jbtnSlct = new JButton(new ImageIcon(commonPath + "searchButton_new.png"));

		StdntAnswerDAO saDAO = StdntAnswerDAO.getInstance();

		try {
			crsList = saDAO.slctExamCrsList(Integer.parseInt(shd.getlVO().getId()));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (SubStdntAnswerVO cVO : crsList) {
			jcbCrs.addItem(cVO.getCourse_name());
		}

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
		String[] testAnswerColumn = { "문제번호", "답안", "나의 답안" };

		dtmTestAnswer = new DefaultTableModel(testAnswerColumn, 0) {

			public boolean isCellEditable(int row, int column) {
				return false; // 테이블 셀 수정 불가하도록 설정
			} // isCellEditable
		};

		jtbTestAnswer = new JTable(dtmTestAnswer);

		JScrollPane jsp = new JScrollPane(jtbTestAnswer);

		// 테이블 컬럼 가운데 정렬
		setTbHorizontal();

		jtbTestAnswer.setRowHeight(30); // 행 높이 조절

		jsp.setBounds(190, 225, 600, 230);

		add(jsp);

		// 이벤트 클래스 연결
		StdntTestAnswerEvent stae = new StdntTestAnswerEvent(this);
		addWindowListener(stae);
		jbtnSlct.addActionListener(stae);
		jbtnOk.addActionListener(stae);

		// 배경 추가
		jlBack = new JLabel(new ImageIcon(commonPath + "Back.png"));
		jlBack.setBounds(0, 0, 984, 620);
		add(jlBack);
		setSize(1000, 650);
		setLocationRelativeTo(null);
		// 프레임크기 조절 불가
		setResizable(false);
		setVisible(true);
	} // StdntTestAnswerDesign

	/**
	 * 테이블의 컬럼을 가운데 정렬
	 */
	public void setTbHorizontal() {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm1 = jtbTestAnswer.getColumnModel();
		for (int i = 0; i < tcm1.getColumnCount(); i++) {
			tcm1.getColumn(i).setCellRenderer(dtcr);
		} // end for

	} // setTbHorizontal

	public StdntHomeDesign getShd() {
		return shd;
	}

	public List<SubStdntAnswerVO> getCrsList() {
		return crsList;
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

	public JTable getJtbTestAnswer() {
		return jtbTestAnswer;
	}

	public DefaultTableModel getDtmTestAnswer() {
		return dtmTestAnswer;
	}

} // class
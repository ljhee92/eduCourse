package eduCourse_prj.admin.design;

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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import eduCourse_prj.VO.DeptVO;

import eduCourse_prj.admin.dao.AdminDAO;
import eduCourse_prj.admin.event.AdminProfMgtEvent;


@SuppressWarnings("serial")
public class AdminProfMgtDesign extends JDialog {


	private AdminHomeDesign awd;
	AdminDAO aDAO = AdminDAO.getInstance();
	
	private JLabel jlBack; // 배경
	private JLabel topLogin; // 우상단 로그인상태 확인창
	private JLabel profMgt;
	private JLabel jlDept;
	private JLabel jlProfNum;
	private JButton jbtnProfReg, jbtnSlct, jbtnMdfy, jbtnDel, jbtnSlctTop;
	private JTable jtbProfMgt;
	private DefaultTableModel dtmProfMgt;

	private JComboBox<String> jcbDept;
	private JTextField jtfProfNum;

	private List<DeptVO> lDept;

	public AdminProfMgtDesign(AdminHomeDesign awd, String title) {
		super(awd, title, true);
		this.awd = awd;

		setLayout(null);

		String commonPath = "src/eduCourse_prj/image/common/";
		String profPath = "src/eduCourse_prj/image/prof/";

		// 우상단 로그인상태 확인창 추가
		topLogin = new JLabel(awd.getlVO().getName() + " 관리자님 로그인 중");
		Font font = new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 15);
		topLogin.setFont(font);
		topLogin.setForeground(Color.WHITE);
		topLogin.setBounds(670, 30, 200, 20);
		add(topLogin);

		// 교수관리 라벨 추가
		profMgt = new JLabel(new ImageIcon(profPath + "ProfMgt.png"));
		profMgt.setBounds(10, 76, 967, 44);
		add(profMgt);

		// 테이블 추가
		String[] tempColumn = { "학과", "교번", "이름" };
		dtmProfMgt = new DefaultTableModel(tempColumn, 0) {
			public boolean isCellEditable(int row, int column) {
				return false; // 테이블 셀 수정 불가하도록 설정
			} // isCellEditable
		};
		jtbProfMgt = new JTable(dtmProfMgt);
		JScrollPane jsp = new JScrollPane(jtbProfMgt);


		// 테이블 컬럼 가운데 정렬
		setTbHorizontal();

		jtbProfMgt.setRowHeight(30); // 행 높이 조절
		jsp.setBounds(10, 200, 967, 250);
		add(jsp);

		// 학과 라벨,콤보박스 // 교번 라벨,텍스트필드 추가
		jlDept = new JLabel("학과");
		jlDept.setFont(font);
		jlDept.setBounds(180, 150, 50, 20);
		add(jlDept);

		jcbDept = new JComboBox<String>();
		jcbDept.setFont(font);
		jcbDept.setBounds(230, 145, 200, 30);
		add(jcbDept);
		
		seltAllDept();

		jlProfNum = new JLabel("교번");
		jlProfNum.setFont(font);
		jlProfNum.setBounds(480, 150, 50, 20);
		add(jlProfNum);

		jtfProfNum = new JTextField();
		jtfProfNum.setFont(font);
		jtfProfNum.setBounds(530, 145, 200, 30);
		add(jtfProfNum);

		// 상단 조회버튼 추가
		jbtnSlctTop = new JButton(new ImageIcon(commonPath + "searchButton_new.png"));
		jbtnSlctTop.setBounds(750, 145, 55, 30);
		add(jbtnSlctTop);

		// 교수등록, 조회, 수정, 삭제 버튼 추가
		jbtnProfReg = new JButton(new ImageIcon(profPath + "ProfRegButton_new.png"));
		jbtnSlct = new JButton(new ImageIcon(commonPath + "SlctButton_new.png"));
		jbtnMdfy = new JButton(new ImageIcon(commonPath + "MdfyButton_new.png"));
		jbtnDel = new JButton(new ImageIcon(commonPath + "DelButton_new.png"));
		jbtnProfReg.setBounds(220, 490, 142, 50);
		jbtnSlct.setBounds(440, 490, 90, 50);
		jbtnMdfy.setBounds(560, 490, 90, 50);
		jbtnDel.setBounds(680, 490, 90, 50);
		add(jbtnProfReg);
		add(jbtnSlct);
		add(jbtnMdfy);
		add(jbtnDel);

		// 이벤트 클래스 연결
		AdminProfMgtEvent apme = new AdminProfMgtEvent(this);
		addWindowListener(apme);
		jbtnProfReg.addActionListener(apme);
		jbtnSlct.addActionListener(apme);
		jbtnMdfy.addActionListener(apme);
		jbtnDel.addActionListener(apme);
		jbtnSlctTop.addActionListener(apme);

		// 배경 추가
		jlBack = new JLabel(new ImageIcon(commonPath + "Back.png"));
		jlBack.setBounds(0, 0, 984, 620);
		add(jlBack);
		setSize(1000, 650);
		setLocationRelativeTo(null);
		setVisible(true);
	} // AdminProfMgtDesign

	
	
	/**
	 * DB에서 모든 학과 정보를 가져오는 method
	 */
	public void seltAllDept() {
	
	try {// 학과

		// 모든 학과 정보 가져오기
		lDept = aDAO.slctAllDept();

		// "전체 아이템 추가"
		jcbDept.addItem("전체");

		// 학과명만 저장하는 리스트에 학과명 저장
		for (DeptVO dept : lDept) {
			jcbDept.addItem(dept.getDept_name());

		}

	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	}
	
	

	/**
	 * 테이블의 컬럼을 가운데 정렬
	 */
	public void setTbHorizontal() {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = jtbProfMgt.getColumnModel();
		for (int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		} // end for
	} // setTbHorizontal

	public JButton getJbtnProfReg() {
		return jbtnProfReg;
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

	public JLabel getJlBack() {
		return jlBack;
	}

	public JLabel getTopLogin() {
		return topLogin;
	}

	public JLabel getProfMgt() {
		return profMgt;
	}

	public JTable getJtbProfMgt() {
		return jtbProfMgt;
	}

	public DefaultTableModel getDtmProfMgt() {
		return dtmProfMgt;
	}

	public AdminHomeDesign getAwd() {
		return awd;
	}
	
	public AdminDAO getaDAO() {
		return aDAO;
	}



	public JLabel getJlDept() {
		return jlDept;
	}



	public JLabel getJlProfNum() {
		return jlProfNum;
	}



	public JButton getJbtnSlctTop() {
		return jbtnSlctTop;
	}



	public JComboBox<String> getJcbDept() {
		return jcbDept;
	}



	public JTextField getJtfProfNum() {
		return jtfProfNum;
	}



	public List<DeptVO> getLDept() {
		return lDept;
	}

} // class
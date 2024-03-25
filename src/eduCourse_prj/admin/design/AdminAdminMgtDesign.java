
package eduCourse_prj.admin.design;



import java.awt.Color;

import java.awt.Font;

import java.sql.SQLException;

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

import eduCourse_prj.VO.AdminVO;
import eduCourse_prj.VO.CrsVO;
import eduCourse_prj.admin.dao.AdminDAO;
import eduCourse_prj.admin.event.AdminAdminMgtEvent;


import java.util.List;

@SuppressWarnings("serial")
public class AdminAdminMgtDesign extends JDialog {
	AdminHomeDesign awd;

	JLabel jlBack;// 배경
	private JLabel topLogin; // 우상단 로그인상태 확인창
	private JLabel adminMgt;


	private JButton jbtnMdfy;

	private JTable jtbAdminMgt;
	private DefaultTableModel dtmAdminMgt;

	public AdminAdminMgtDesign(AdminHomeDesign awd, String title) {
		super(awd, title, true);
		this.awd = awd;

		setLayout(null);

		String commonPath = "src/eduCourse_prj/image/common/";
		String adminPath = "src/eduCourse_prj/image/admin/";

		jlBack = new JLabel(new ImageIcon(commonPath + "back.png"));
		jlBack.setBounds(0, 0, 984, 620);
		add(jlBack);
		
		// 관리자관리, 등록 라벨 추가
		adminMgt = new JLabel(new ImageIcon(adminPath + "adminMgtBanner_new.png"));
		adminMgt.setBounds(10, 76, 967, 44);
		add(adminMgt);

		

		// 우상단 로그인상태 확인창 추가
		topLogin = new JLabel(awd.getlVO().getName() + " 관리자님 로그인 중");
		Font font = new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 15);
		topLogin.setFont(font);
		topLogin.setForeground(Color.WHITE);
		topLogin.setBounds(670, 30, 200, 20);
		add(topLogin);
		
		
		
		

		// 관리자 수정 버튼 추가

		jbtnMdfy = new JButton(new ImageIcon(commonPath + "Mdfy.png"));

		jbtnMdfy.setBounds(440, 500, 111, 59);

		add(jbtnMdfy);

		String[] tempColumn = { "아이디", "이름" };
		dtmAdminMgt = new DefaultTableModel(tempColumn, 0) {
			public boolean isCellEditable(int row, int column) {
				return false; // 테이블 셀 수정 불가하도록 설정
			} // isCellEditable
		};
		jtbAdminMgt = new JTable(dtmAdminMgt);
		JScrollPane jsp = new JScrollPane(jtbAdminMgt);

		jtbAdminMgt.setRowHeight(30); // 행 높이 조절
		jsp.setBounds(10, 120, 967, 350);
		add(jsp);

		JLabel jlDept = new JLabel("학과");
		jlDept.setFont(font);
		jlDept.setBounds(20, 20, 50, 20);
		add(jlDept);
		
		
		JLabel jlSrc = new JLabel("과목");
		jlSrc.setFont(font);
		jlSrc.setBounds(20, 40, 50, 20);
		add(jlSrc);
		
		JLabel jlstdNumber = new JLabel("학번");
		jlstdNumber.setFont(font);
		jlstdNumber.setBounds(20, 60, 50, 20);
		add(jlstdNumber);
		
		
		
		
		
		
		
		
		// 테이블에 DB 추가
		slctAdminMgt();

		// 테이블 컬럼 가운데 정렬
		setTbHorizontal();

		// 이벤트 클래스 연결
		AdminAdminMgtEvent aame = new AdminAdminMgtEvent(this);
		addWindowListener(aame);

		jbtnMdfy.addActionListener(aame);



		add(jlBack);

		setSize(1000, 650);

		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * 
	 * DB에서 관리자의 아이디, 이름을 불러와 테이블에 넣는 method
	 */
	public void slctAdminMgt() {
		AdminDAO aDAO = AdminDAO.getInstance();
		try {
			List<AdminVO> listAdminVO = aDAO.slctAllAdmin();

			for (AdminVO aVO : listAdminVO) {
				Object[] row = { aVO.getAdmin_id(), aVO.getAdmin_name() };
				dtmAdminMgt.addRow(row);
			} // end for

		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch
	} // slctAdminMgt

	/**
	 * 테이블의 컬럼을 가운데 정렬
	 */
	public void setTbHorizontal() {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = jtbAdminMgt.getColumnModel();
		for (int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		} // end for
	} // setTbHorizontal

	public AdminHomeDesign getAwd() {
		return awd;
	}

	public JLabel getJlBack() {
		return jlBack;
	}


	public JButton getJbtnMdfy() {
		return jbtnMdfy;
	}

	public JTable getJtbAdminMgt() {
		return jtbAdminMgt;
	}

	public DefaultTableModel getDtmAdminMgt() {
		return dtmAdminMgt;
	}

	public JLabel getTopLogin() {
		return topLogin;
	}

	public JLabel getAdminMgt() {
		return adminMgt;
	}

	
	
}
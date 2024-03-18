package eduCourse_prj.admin.design;

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

import eduCourse_prj.VO.ProfVO;
import eduCourse_prj.admin.event.AdminProfMgtEvent;
import eduCourse_prj.professor.dao.ProfDAO;


@SuppressWarnings("serial")
public class AdminProfMgtDesign extends JDialog {
	private AdminHomeDesign awd;
	
    private JLabel jlBack; //배경
    private JLabel topLogin; // 우상단 로그인상태 확인창
    private JLabel profMgt;
    private JButton jbtnProfReg, jbtnSlct, jbtnMdfy, jbtnDel;
    private JTable jtbProfMgt;
    private DefaultTableModel dtmProfMgt;
	
	public AdminProfMgtDesign(AdminHomeDesign awd, String title) {
		super(awd,title,true);
		this.awd = awd;
		
		setLayout(null);
		
		String commonPath = "C:/dev/workspace/eduCourse_prj/src/eduCourse_prj/image/common/";
		String profPath = "C:/dev/workspace/eduCourse_prj/src/eduCourse_prj/image/prof/";
		
		// 우상단 로그인상태 확인창 추가
		topLogin = new JLabel(awd.getlVO().getName() + " 관리자님 로그인 중");
		Font font = new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 15);
		topLogin.setFont(font);
		topLogin.setForeground(Color.RED);
		topLogin.setBounds(670, 30, 200, 20);
		add(topLogin);
		
		// 교수관리 라벨 추가
		profMgt = new JLabel(new ImageIcon(profPath + "ProfMgt.png"));
		profMgt.setBounds(10, 76, 967, 44);
		add(profMgt);
		
		// 테이블 추가
		String[] tempColumn = {"교번", "이름"};
		dtmProfMgt = new DefaultTableModel(tempColumn, 0) {
			public boolean isCellEditable(int row, int column){
			    return false; // 테이블 셀 수정 불가하도록 설정
			} // isCellEditable
		};
		jtbProfMgt = new JTable(dtmProfMgt);
		JScrollPane jsp = new JScrollPane(jtbProfMgt);

		// 테이블에 DB 추가
		slctProfMgt();
		
		// 테이블 컬럼 가운데 정렬
		setTbHorizontal();
		
        jtbProfMgt.setRowHeight(30); // 행 높이 조절
		jsp.setBounds(10, 120, 967, 350);
		add(jsp);
		
		// 교수등록, 조회, 수정, 삭제 버튼 추가
		jbtnProfReg = new JButton(new ImageIcon(profPath + "ProfReg.png"));
		jbtnSlct = new JButton(new ImageIcon(commonPath + "Slct.png"));
		jbtnMdfy = new JButton(new ImageIcon(commonPath + "Mdfy.png"));
		jbtnDel = new JButton(new ImageIcon(commonPath + "Del.png"));
		jbtnProfReg.setBounds(150, 500, 168, 59);
		jbtnSlct.setBounds(400, 500, 111, 59);
		jbtnMdfy.setBounds(550, 500, 111, 59);
		jbtnDel.setBounds(700, 500, 111, 59);
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
		
		// 배경 추가
        jlBack = new JLabel(new ImageIcon(commonPath + "Back.png"));
        jlBack.setBounds(0,0,984,620);
        add(jlBack);
		setSize(1000,650);
		setLocationRelativeTo(null);
		setVisible(true);
	} // AdminProfMgtDesign
	
	/**
	 * DB에서 교번, 이름을 불러와 테이블에 넣는 method
	 */
	public void slctProfMgt() {
		ProfDAO pDAO = ProfDAO.getInstance();
		try {
			List<ProfVO> listProfVO = pDAO.slctProfMgt();
			
			for(ProfVO pVO : listProfVO) {
				Object[] row = {pVO.getProf_number(), pVO.getProf_name()};
				dtmProfMgt.addRow(row);
			} // end for
			
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch
	} // slctProfMgt
	
	/**
	 * 테이블의 컬럼을 가운데 정렬
	 */
	public void setTbHorizontal() {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = jtbProfMgt.getColumnModel();
		for(int i = 0; i < tcm.getColumnCount(); i++) {
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

	public JTable getJtbProfMgt() {
		return jtbProfMgt;
	}

	public DefaultTableModel getDtmProfMgt() {
		return dtmProfMgt;
	}

	public AdminHomeDesign getAwd() {
		return awd;
	}
	
} // class
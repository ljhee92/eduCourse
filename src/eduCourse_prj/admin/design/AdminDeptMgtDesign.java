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

import eduCourse_prj.VO.DeptVO;
import eduCourse_prj.VO.ProfVO;
import eduCourse_prj.admin.dao.AdminDAO;
import eduCourse_prj.admin.event.AdminDeptMgtEvent;
import eduCourse_prj.professor.dao.ProfDAO;

@SuppressWarnings("serial")
public class AdminDeptMgtDesign extends JDialog {
	private AdminHomeDesign awd;
	private JLabel jlBack; // 배경
	private JLabel topLogin; // 우상단 로그인상태 확인창
	private JLabel deptMgt;
	private JButton jbtnDeptReg, jbtnSlct,jbtnDel;
	private JTable jtbDeptMgt;
	private DefaultTableModel dtmDeptMgt;
	
	public AdminDeptMgtDesign() {
	}
	public AdminDeptMgtDesign(AdminHomeDesign awd,String title ) {
		super(awd,title,true);
		this.awd = awd;
		
		//공통경로
		String commonPath = "src/eduCourse_prj/image/common/";
		String deptPath = "src/eduCourse_prj/image/admin/";
		
		// 우상단 로그인상태 확인창 추가
		topLogin = new JLabel(awd.getlVO().getName() + " 관리자님 로그인 중");
		Font font = new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 15);
		topLogin.setFont(font);
		topLogin.setForeground(Color.WHITE);
		topLogin.setBounds(670, 30, 200, 20);
		add(topLogin);
		
		//학과관리 라벨 추가
		deptMgt = new JLabel(new ImageIcon(deptPath+"deptMgt1.png"));
		deptMgt.setBounds(10, 76, 967, 44);
		add(deptMgt);
		
		// 테이블 추가
		String[] tempColumn = {"학과 코드", "학과"};
		dtmDeptMgt = new DefaultTableModel(tempColumn, 0) {
			public boolean isCellEditable(int row, int column){
			    return false; // 테이블 셀 수정 불가하도록 설정
			} // isCellEditable
		};
		jtbDeptMgt = new JTable(dtmDeptMgt);
		JScrollPane jsp = new JScrollPane(jtbDeptMgt);
		jsp.setBounds(10, 120, 967, 350);
		add(jsp);
		
		//테이블에 DB 추가
		slctDeptMgt();
		
		// 테이블 컬럼 가운데 정렬
		setTbHorizontal();
		
        jtbDeptMgt.setRowHeight(30); // 행 높이 조절
        
     
		// 학과등록, 조회, 삭제 버튼 추가
		jbtnDeptReg = new JButton(new ImageIcon(deptPath + "DeptRegBanner_new.png"));//DeptReg
		jbtnSlct = new JButton(new ImageIcon(commonPath + "SlctButton_new.png"));	//Slct	
		jbtnDel = new JButton(new ImageIcon(commonPath+ "DelButton_new.png")); //Del
		
		jbtnDeptReg.setBounds(300, 500, 142, 50);
		jbtnSlct.setBounds(500, 500, 90, 50);
		jbtnDel.setBounds(620, 500, 90, 50);
		
		add(jbtnDeptReg);
		add(jbtnSlct);
		add(jbtnDel);
		
		////////////////////////////////이벤트 관계 설정/////////////////////////

		AdminDeptMgtEvent adme = new AdminDeptMgtEvent(this);
		jbtnDeptReg.addActionListener(adme);
		jbtnSlct.addActionListener(adme);
		jbtnDel.addActionListener(adme);
		
		// 배경 추가
        jlBack = new JLabel(new ImageIcon(commonPath + "Back.png"));
        jlBack.setBounds(0,0,984,620);
        add(jlBack);
		setSize(1000,650);
		setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
		
		
	}//AdminDeptMgtDesgin
	
	public void slctDeptMgt() {
		AdminDAO aDAO= AdminDAO.getInstance();
		try {
			List<DeptVO> listDeptVO = aDAO.slctAllDept();
			
			for(DeptVO dVO : listDeptVO) {
				Object[] row = {dVO.getDept_code(), dVO.getDept_name()};
				dtmDeptMgt.addRow(row);
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
		TableColumnModel tcm = jtbDeptMgt.getColumnModel();
		for(int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		} // end for
	} // setTbHorizontal
	
	
	public AdminHomeDesign getAwd() {
		return awd;
	}
	public JButton getJbtnSlct() {
		return jbtnSlct;
	}
	public JButton getJbtnDel() {
		return jbtnDel;
	}
	public JButton getJbtnDeptReg() {
		return jbtnDeptReg;
	}
	
	
	public JLabel getDeptMgt() {
		return deptMgt;
	}
	public JTable getJtbDeptMgt() {
		return jtbDeptMgt;
	}
	public DefaultTableModel getDtmDeptMgt() {
		return dtmDeptMgt;
	}

}

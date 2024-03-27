package eduCourse_prj.student.design;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
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

import eduCourse_prj.VO.CrsRegVO;
import eduCourse_prj.VO.RegVO;
import eduCourse_prj.student.dao.CrsRegDAO;
import eduCourse_prj.student.event.StdntCrsRegEvent;


@SuppressWarnings("serial")
public class StdntCrsRegDesign extends JDialog {
	
	private StdntHomeDesign shd;
    private JLabel jlBack; //배경
    private JLabel topLogin; // 우상단 로그인상태 확인창
    private JLabel jlCrsReg, jlCrsCart, jlAllCreditTxt, jlAllCreditHour;
    private JButton jbtnAdd, jbtnCancel, jbtnReg;
    private JTable jtbCrsReg, jtbCrsCart;
    private DefaultTableModel dtmCrsReg, dtmCrsCart;
   
	public StdntCrsRegDesign(StdntHomeDesign shd, String title) {
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
		
		// 수강신청, 수강바구니 상단 라벨 추가
		jlCrsReg = new JLabel(new ImageIcon(stdntPath + "CrsReg_Label.png"));
		jlCrsCart = new JLabel(new ImageIcon(stdntPath + "CrsCart_Label.png"));
		
		jlCrsReg.setBounds(10, 76, 967, 34);
		jlCrsCart.setBounds(10, 350, 967, 34);
		
		add(jlCrsReg);
		add(jlCrsCart);
		
		// 총학점 하단 라벨 추가
		jlAllCreditTxt = new JLabel("총 학점 : ");
		jlAllCreditHour = new JLabel("0");
		
		jlAllCreditTxt.setFont(font);
		jlAllCreditHour.setFont(font);
		jlAllCreditTxt.setForeground(Color.RED);
		jlAllCreditHour.setForeground(Color.RED);
		
		jlAllCreditTxt.setBounds(660, 555, 80, 30);
		jlAllCreditHour.setBounds(750, 555, 40, 30);
		
		add(jlAllCreditTxt);
		add(jlAllCreditHour);
		
		// 담기, 취소, 최종신청 버튼 추가
		jbtnAdd = new JButton(new ImageIcon(stdntPath + "AddCart.png"));
		jbtnCancel = new JButton(new ImageIcon(stdntPath + "Cancel.png"));
		jbtnReg = new JButton(new ImageIcon(stdntPath + "Reg.png"));
		
		jbtnAdd.setBounds(400, 300, 78, 40);
		jbtnCancel.setBounds(520, 300, 78, 40);
		jbtnReg.setBounds(800, 545, 140, 40);
		
		add(jbtnAdd);
		add(jbtnCancel);
		add(jbtnReg);
		
		// 테이블 추가
		String[] crsRegColumn = {"학과", "과목", "과목코드", "강의실", "정원", "학점"};
		String[] crsCartColumn = {"학과", "과목", "과목코드", "강의실", "정원", "학점"};
		
		dtmCrsReg = new DefaultTableModel(crsRegColumn, 0) {
			public boolean isCellEditable(int row, int column){
			    return false; // 테이블 셀 수정 불가하도록 설정
			} // isCellEditable
		};
		
		dtmCrsCart = new DefaultTableModel(crsCartColumn, 0) {
			public boolean isCellEditable(int row, int column){
			    return false; // 테이블 셀 수정 불가하도록 설정
			} // isCellEditable
		};
		
		jtbCrsReg = new JTable(dtmCrsReg);
		jtbCrsCart = new JTable(dtmCrsCart);
		
		JScrollPane jspReg = new JScrollPane(jtbCrsReg);
		JScrollPane jspCart = new JScrollPane(jtbCrsCart);
		
		// 테이블 컬럼 가운데 정렬
		setTbHorizontal();
		
		jspReg.setBounds(10, 110, 967, 182);
		jspCart.setBounds(10, 383, 967, 155);
		add(jspReg);
		add(jspCart);
		
		// 테이블에 DB 추가
		searchAllCrsReg();
		
		// 이벤트 클래스 연결
		StdntCrsRegEvent scre = new StdntCrsRegEvent(this);
		addWindowListener(scre);
		jbtnAdd.addActionListener(scre);
		jbtnCancel.addActionListener(scre);
		jbtnReg.addActionListener(scre);
		
		// 배경 추가
        jlBack = new JLabel(new ImageIcon(commonPath + "Back.png"));
        jlBack.setBounds(0,0,984,620);
        add(jlBack);
		setSize(1000,650);
		setLocationRelativeTo(null);
		setVisible(true);
	} // AdminProfMgtDesign
	
	/**
	 * DB에서 로그인한 학생이 수강 신청 가능한 과목들을 가져와 테이블에 넣는 method
	 */
	public void searchAllCrsReg() {
		CrsRegDAO crDAO = CrsRegDAO.getInstance();

		int stdnt_number = Integer.parseInt(shd.getlVO().getId());
		
		try {
			List<RegVO> listAllRegVO = crDAO.slctAllReg(stdnt_number); // 학생의 수강신청완료 과목 리스트
			
			List<CrsRegVO> listAllCrsRegVO = crDAO.slctAllCrsReg(stdnt_number); // 학생이 속한 학과의 수강신청가능 과목 리스트

			List<CrsRegVO> listToRemove = new ArrayList<CrsRegVO>(); // 학생의 수강신청완료 과목 리스트를 지우기 위해
			String crsRegCrsCode = "", regCrsCode = "";
			
			for(CrsRegVO crsRegVO : listAllCrsRegVO) {
			    crsRegCrsCode = crsRegVO.getCourse_code(); // CrsRegVO의 course_code
			    for(RegVO regVO : listAllRegVO) {
			        regCrsCode = regVO.getCourse_code(); // RegVO의 course_code
			        if (crsRegCrsCode.equals(regCrsCode)) { // 두 객체의 course_code가 같은 경우
			            listToRemove.add(crsRegVO);
			            break;
			        } // end if
			    } // end for
			} // end for

			listAllCrsRegVO.removeAll(listToRemove);
			
			CrsRegVO crVO = null;
			for(int i = 0; i < listAllCrsRegVO.size(); i++) {
				crVO = listAllCrsRegVO.get(i);
				Object[] row = {crVO.getDept_name(), crVO.getCourse_name(), crVO.getCourse_code(), 
						crVO.getLect_room(), crVO.getCapacity(), crVO.getCredit_hours()};
				dtmCrsReg.addRow(row);
			} // end for
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch
	} // searchAllCrsReg

	/**
	 * 테이블의 컬럼을 가운데 정렬
	 */
	public void setTbHorizontal() {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm1 = jtbCrsReg.getColumnModel();
		for(int i = 0; i < tcm1.getColumnCount(); i++) {
			tcm1.getColumn(i).setCellRenderer(dtcr);
		} // end for
		
		TableColumnModel tcm2 = jtbCrsCart.getColumnModel();
		for(int i = 0; i < tcm2.getColumnCount(); i++) {
			tcm2.getColumn(i).setCellRenderer(dtcr);
		} // end for
	} // setTbHorizontal

	public JButton getJbtnAdd() {
		return jbtnAdd;
	}

	public JButton getJbtnCancel() {
		return jbtnCancel;
	}

	public JButton getJbtnReg() {
		return jbtnReg;
	}
	
	public JTable getJtbCrsReg() {
		return jtbCrsReg;
	}

	public DefaultTableModel getDtmCrsReg() {
		return dtmCrsReg;
	}
	
	public JTable getJtbCrsCart() {
		return jtbCrsCart;
	}
	
	public DefaultTableModel getDtmCrsCart() {
		return dtmCrsCart;
	}
	
	public JLabel getJlAllCreditHour() {
		return jlAllCreditHour;
	}

	public StdntHomeDesign getShd() {
		return shd;
	}
	
} // class

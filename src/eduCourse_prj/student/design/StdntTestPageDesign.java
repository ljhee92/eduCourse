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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import eduCourse_prj.VO.TestPageVO;
import eduCourse_prj.student.dao.StdntTestDAO;
import eduCourse_prj.student.event.StdntTestPageEvent;

@SuppressWarnings("serial")
public class StdntTestPageDesign extends JDialog {
	
	private StdntTestSlctDesign stsd;
    private JLabel jlBack; //배경
    private JLabel topLogin; // 우상단 로그인상태 확인창
    private JLabel jlTestSlct, jlTestPage;
    private JButton jbtnSubmit;
    private JTable jtbTestPage;
    private DefaultTableModel dtmTestPage;
    
    public StdntTestPageDesign(StdntTestSlctDesign stsd, String title) {
    	super(stsd, title, true);
		this.stsd = stsd;
		
		setLayout(null);
		
		String commonPath = "src/eduCourse_prj/image/common/";
		String stdntPath = "src/eduCourse_prj/image/stud/";
		
		// 우상단 로그인상태 확인창 추가
		topLogin = new JLabel(stsd.getShd().getlVO().getName() + " 학생님 로그인 중");
		Font font = new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 17);
		topLogin.setFont(font);
		topLogin.setForeground(Color.WHITE);
		topLogin.setBounds(670, 30, 200, 20);
		add(topLogin);
		
		// 시험응시및결과, 시험 응시 상단 라벨 추가
		jlTestSlct = new JLabel(new ImageIcon(stdntPath + "TestSlct_Label.png"));
		jlTestPage = new JLabel(new ImageIcon(stdntPath + "TestPage_Label.png"));
	
		jlTestSlct.setBounds(10, 76, 967, 34);
		jlTestPage.setBounds(10, 110, 967, 43);
		
		add(jlTestSlct);
		add(jlTestPage);
		
		// 제출 버튼 추가
		jbtnSubmit = new JButton(new ImageIcon(stdntPath + "Submit.png"));
		jbtnSubmit.setBounds(450, 520, 95, 50);
		add(jbtnSubmit);
		
		// 테이블 추가
		String[] testPageColumn = {"번호", "문제", "선택1", "선택2", "선택3", "선택4", "답"};
		
		dtmTestPage = new DefaultTableModel(testPageColumn, 0) {
			public boolean isCellEditable(int row, int column){
			    return column == 6; // "답" 열만 셀 수정 가능하도록 설정
			} // isCellEditable
		};
		
		jtbTestPage = new JTable(dtmTestPage);
		JScrollPane jsp = new JScrollPane(jtbTestPage);
		
		// 테이블 컬럼 가운데 정렬
		setTbHorizontal();
		
		jtbTestPage.setRowHeight(30); // 행 높이 조절
		jsp.setBounds(10, 150, 967, 300);
		add(jsp);
		
		// 테이블에 시험 문제 번호와 추가
		slctTestContent();
		
		// 이벤트 클래스 연결
		StdntTestPageEvent stpe = new StdntTestPageEvent(this);
		addWindowFocusListener(stpe);
		jbtnSubmit.addActionListener(stpe);
		
		// 배경 추가
        jlBack = new JLabel(new ImageIcon(commonPath + "Back.png"));
        jlBack.setBounds(0,0,984,620);
        add(jlBack);
		setSize(1000,650);
		setLocationRelativeTo(null);
		setVisible(true);
		// 프레임크기 조절 불가
		setResizable(false);
    } // StdntTestPageDesign

    /**
	 * 테이블의 컬럼을 가운데 정렬
	 */
	public void setTbHorizontal() {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm1 = jtbTestPage.getColumnModel();
		for(int i = 0; i < tcm1.getColumnCount(); i++) {
			tcm1.getColumn(i).setCellRenderer(dtcr);
		} // end for
	} // setTbHorizontal
	
	/**
	 * 테이블에 시험 문제 번호와 문제를 추가하는 method
	 */
	public void slctTestContent() {
		StdntTestDAO stDAO = StdntTestDAO.getInstance();
		
		int index = stsd.getJtbTestSlct().getSelectedRow();
		String course_name = stsd.getJtbTestSlct().getValueAt(index, 2).toString();
		
		List<TestPageVO> listTPVO = new ArrayList<TestPageVO>();
		
		try {
			listTPVO = stDAO.slctTestPage(course_name);
			
			for(TestPageVO tpVO : listTPVO) {
				Object[] row = {tpVO.getQuestion_number(), tpVO.getQuestion_split_content(), 
						tpVO.getOption1(), tpVO.getOption2(), tpVO.getOption3(), tpVO.getOption4()};
				
				dtmTestPage.addRow(row);
			} // end for
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(stsd, "SQL 예외가 발생했습니다.");
			e.printStackTrace();
		} // end catch
	} // slctTestContent
    
	public StdntTestSlctDesign getStsd() {
		return stsd;
	}

	public JButton getJbtnSubmit() {
		return jbtnSubmit;
	}

	public JTable getJtbTestPage() {
		return jtbTestPage;
	}

	public DefaultTableModel getDtmTestPage() {
		return dtmTestPage;
	}
	
} // class
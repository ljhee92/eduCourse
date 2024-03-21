package eduCourse_prj.student.design;

import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultCellEditor;
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
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import eduCourse_prj.student.event.StdntTestSlctEvent;

public class StdntTestSlctDesign extends JDialog {
	
	private StdntHomeDesign shd;
    private JLabel jlBack; //배경
    private JLabel topLogin; // 우상단 로그인상태 확인창
    private JLabel jlTestSlct, jlAllCreditTxt, jlAllCreditHour;
    private JButton jbtnTest, jbtnOK;
    private JTable jtbTestSlct;
    private DefaultTableModel dtmTestSlct;
    
    public StdntTestSlctDesign(StdntHomeDesign shd, String title) {
    	super(shd, title, true);
		this.shd = shd;
		
		setLayout(null);
		
		String commonPath = "src/eduCourse_prj/image/common/";
		String stdntPath = "src/eduCourse_prj/image/stud/";
		
		// 우상단 로그인상태 확인창 추가
		topLogin = new JLabel(shd.getlVO().getName() + " 학생님 로그인 중");
		Font font = new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 15);
		topLogin.setFont(font);
		topLogin.setForeground(Color.RED);
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
		String[] testSlctColumn = {"학과", "과목", "과목코드", "담당교수", "응시가능여부", "점수", "성취도"};
		
		dtmTestSlct = new DefaultTableModel(testSlctColumn, 0) {
			public boolean isCellEditable(int row, int column){
			    return false; // 테이블 셀 수정 불가하도록 설정
			} // isCellEditable
		};
		
		jtbTestSlct = new JTable(dtmTestSlct);
		JScrollPane jsp = new JScrollPane(jtbTestSlct);

		// 테이블 컬럼 가운데 정렬
		setTbHorizontal();
		
		jsp.setBounds(10, 110, 967, 330);
		add(jsp);
		
		// 이벤트 클래스 연결
		StdntTestSlctEvent stsc = new StdntTestSlctEvent(this);
		addWindowListener(stsc);
		jbtnTest.addActionListener(stsc);
		jbtnOK.addActionListener(stsc);
		
		// 배경 추가
        jlBack = new JLabel(new ImageIcon(commonPath + "Back.png"));
        jlBack.setBounds(0,0,984,620);
        add(jlBack);
		setSize(1000,650);
		setLocationRelativeTo(null);
		setVisible(true);
    } // StdntTestSlctDesign

    /**
   	 * 테이블의 컬럼을 가운데 정렬
   	 */
   	public void setTbHorizontal() {
   		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
   		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
   		TableColumnModel tcm1 = jtbTestSlct.getColumnModel();
   		for(int i = 0; i < tcm1.getColumnCount(); i++) {
   			tcm1.getColumn(i).setCellRenderer(dtcr);
   		} // end for
   	} // setTbHorizontal

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
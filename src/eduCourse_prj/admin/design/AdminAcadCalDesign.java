package eduCourse_prj.admin.design;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import eduCourse_prj.admin.dao.AdminAcadCalDAO;
import eduCourse_prj.admin.event.AdminAcadCalEvent;

@SuppressWarnings("serial")
public class AdminAcadCalDesign extends JDialog {
	AdminHomeDesign awd;

	private JButton searchBtn, saveBtn, deleteBtn;
	private JButton[] calBtn;
	private JComboBox<Integer> yearCb, monthCb;
	private DefaultComboBoxModel<Integer> yearCbm, monthCbm;
	private JTextField timeJtf;
	private JTextArea memoJta;
	private JLabel yearJb, monthJb, jlBack, jlBanner;
	private JPanel calJp;

	private Map<String, String> memoMap;
	private Calendar today, cal;
	private String[] days = { "일", "월", "화", "수", "목", "금", "토" };
	private int year, month, day, todays, memoday = 0;
	private int buttonIndex;
	private int cnt;

	public AdminAcadCalDesign(AdminHomeDesign awd, String title) {
		super(awd, title, true);
		this.awd = awd;

		today = Calendar.getInstance(); // 달력 가져오기
		cal = new GregorianCalendar(); // 표준 달력
		year = today.get(Calendar.YEAR);
		month = today.get(Calendar.MONTH) + 1;

		// 1998~2024년 까지 연도 콤보박스저장
		yearCbm = new DefaultComboBoxModel<Integer>();
		for (int i = 2023; i < 2026; i++) {
			yearCbm.addElement(i);
		}
		// 1월~12월 까지 콤보박스 모델 저장
		monthCbm = new DefaultComboBoxModel<Integer>();
		for (int i = 1; i < 13; i++) {
			monthCbm.addElement(i);
		}
		// 콤보박스에 모델값 저장
		yearCb = new JComboBox<Integer>(yearCbm);
		monthCb = new JComboBox<Integer>(monthCbm);

	    yearCb.setSelectedItem(today.get(Calendar.YEAR));
	    monthCb.setSelectedItem(today.get(Calendar.MONTH) + 1);
		
		yearJb = new JLabel("년");
		monthJb = new JLabel("월");

		// 콤보박스와 레이블에 대한 최소 크기 설정
		Dimension comboBoxDimension = new Dimension(70, 30); // 콤보박스 크기 설정
		yearCb.setPreferredSize(comboBoxDimension); // 콤보박스에 최소 크기 설정
		monthCb.setPreferredSize(comboBoxDimension); // 콤보박스에 최소 크기 설정
		yearJb.setPreferredSize(new Dimension(30, 30)); // 레이블 크기 설정
		monthJb.setPreferredSize(new Dimension(30, 30)); // 레이블 크기 설정
		
		//폰트 설정
		Font font = new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 15);
		yearJb.setFont(font);
		monthJb.setFont(font);
		yearCb.setFont(font);
		monthCb.setFont(font);
		

		// 달력
		calJp = new JPanel(new GridLayout(7, 7));
		calBtn = new JButton[49];
		for (int i = 0; i < 49; i++) {
			int clickBtnIndex = i;
			calBtn[i] = new JButton();
			Font calFont = new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 20);
			calBtn[i].setFont(calFont);
			Insets insets = new Insets(1, 1, 1, 1); // top, left, bottom, right
	        calBtn[i].setMargin(insets);
			calJp.add(calBtn[i]);
			// 달력 버튼 하나당 ActionLinstner 추가
			calBtn[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					year = (int) yearCb.getSelectedItem();
					month = (int) monthCb.getSelectedItem();
					selectDay(clickBtnIndex - (6 + cnt));
					String dayMonthYear = year+""+month+""+day;
					AdminAcadCalDAO aacDAO = AdminAcadCalDAO.getInstance();
					try {
						aacDAO.selectOneCal(dayMonthYear);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		calSet();

		calJp.setBounds(70, 220, 350, 280);
		
		// 메모
		JPanel memoJp = new JPanel(new BorderLayout());
		memoJta = new JTextArea();
		memoJta.setPreferredSize(new Dimension(300, 300));
		Border border = BorderFactory.createLineBorder(Color.gray, 2);
		memoJta.setBorder(border); // JTextArea에 Border를 추가

		String adminPath = "src/eduCourse_prj/image/admin/";
		String commonPath = "src/eduCourse_prj/image/common/";
		JPanel memoBtnJp = new JPanel();
		saveBtn = new JButton(new ImageIcon(commonPath + "저장.png"));
		deleteBtn = new JButton(new ImageIcon(commonPath + "삭제.png"));
		saveBtn.setPreferredSize(new Dimension(75, 40));
		deleteBtn.setPreferredSize(new Dimension(75, 40));

		JLabel memoJl = new JLabel("메모");
		memoJl.setFont(font);
		memoJp.add(memoJl, BorderLayout.NORTH);
		memoJp.add(memoJta, BorderLayout.CENTER);
		memoBtnJp.add(saveBtn);
		memoBtnJp.add(deleteBtn);
		memoJp.add(memoBtnJp, BorderLayout.SOUTH);

		memoJp.setBounds(530, 200, 350, 300);

		// 메모 Map 초기화
		memoMap = new HashMap<String, String>();

		// 년도,월 선택
		JPanel yearSelectJP = new JPanel();
		JPanel monthSelectJP = new JPanel();

		//패널 배경 설정
		memoBtnJp.setBackground(Color.white); //버튼 패널
		memoJp.setBackground(Color.white); //메모 패널
		yearSelectJP.setBackground(Color.white); //년 패널
		monthSelectJP.setBackground(Color.white); //달 패널
		
		// 버튼 설정
		searchBtn = new JButton(new ImageIcon(commonPath + "ConfirmButtonSmall_new.png"));
		searchBtn.setBounds(335, 150, 75, 40);

		// 배너 삽입
		jlBanner = new JLabel(new ImageIcon(adminPath + "SchedMgtBanner_new.png"));
		jlBanner.setBounds(10, 76, 967, 45);

		// 배경 삽입
		jlBack = new JLabel(new ImageIcon(commonPath + "back.png"));
		jlBack.setBounds(0, 0, 984, 620);
		
		// 배치
		yearSelectJP.add(yearJb);
		yearSelectJP.add(yearCb);
		monthSelectJP.add(monthJb);
		monthSelectJP.add(monthCb);
		
		add(calJp);
		add(memoJp);
		add(yearSelectJP);
		add(monthSelectJP);
		
		add(searchBtn);
    
		add(jlBanner);
		add(jlBack);

		yearSelectJP.setBounds(60, 150, 120, 200);
		monthSelectJP.setBounds(190, 150, 120, 200);
		
		AdminAcadCalEvent aace = new AdminAcadCalEvent(this);
		searchBtn.addActionListener(aace);
		saveBtn.addActionListener(aace);
		deleteBtn.addActionListener(aace);

		
		setLayout(null);

		setSize(1000, 650);
		setLocationRelativeTo(null);
		setVisible(true);
	}// end constructor


	public void calSet() {
		cal.set(Calendar.YEAR, (int) yearCb.getSelectedItem());
		cal.set(Calendar.MONTH, (int) monthCb.getSelectedItem() - 1);
		cal.set(Calendar.DATE, 1);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

		cnt = 0;
		// 토요일 일요일 구분
		calBtn[0].setForeground(new Color(255, 0, 0)); // 일요일 "일"
		calBtn[6].setForeground(new Color(0, 0, 255)); // 토요일 "토"
		for (int i = cal.getFirstDayOfWeek(); i < dayOfWeek; i++) {
			cnt++;
		}

		// 일요일~토요일까지
		for (int i = 0; i < 7; i++) {
			calBtn[i].setText(days[i]);
			calBtn[i].setForeground(Color.red);
			calBtn[i].setEnabled(false);
			calBtn[i].setBackground(Color.white);
		}
		// 일요일부터 그달의 첫 시작요일까지 빈칸 세팅
		// 달력 버튼 초기화
		for (int i = 7; i < calBtn.length; i++) {
			calBtn[i].setEnabled(false);
			calBtn[i].setText("");
		}

		for (int i = 1; i <= cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
			cal.set(Calendar.DATE, i);
			int currentDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
			buttonIndex = i + 6 + cnt;
			if (currentDayOfWeek == Calendar.SUNDAY) {
				calBtn[buttonIndex].setForeground(new Color(255, 0, 0));
			} else if (currentDayOfWeek == Calendar.SATURDAY) {
				calBtn[buttonIndex].setForeground(new Color(0, 0, 255));
			} else {
				calBtn[buttonIndex].setForeground(new Color(0, 0, 0));
			}
			calBtn[buttonIndex].setText(String.valueOf(i));

		} // end for
		for (int i = 7; i < 49; i++) {
			if (!calBtn[i].getText().isEmpty()) {
				calBtn[i].setEnabled(true);
			}
		} // end for

	}// end calSet

	// selectDay
	public void selectDay(int day) {
		this.day = day;
	}

	// getter
	public Map<String, String> getMemoMap() {
		return memoMap;
	}

	public JPanel getCalJp() {
		return calJp;
	}

	public JButton[] getCalBtn() {
		return calBtn;
	}

	public JComboBox<Integer> getYearCb() {
		return yearCb;
	}

	public JComboBox<Integer> getMonthCb() {
		return monthCb;
	}

	public DefaultComboBoxModel<Integer> getYearCbm() {
		return yearCbm;
	}

	public DefaultComboBoxModel<Integer> getMonthCbm() {
		return monthCbm;
	}

	public JTextArea getMemoJta() {
		return memoJta;
	}

	public JTextField getTimeJtf() {
		return timeJtf;
	}

	public JLabel getYearJb() {
		return yearJb;
	}

	public JLabel getMonthJb() {
		return monthJb;
	}

	public Calendar getToday() {
		return today;
	}

	public Calendar getCal() {
		return cal;
	}

	public String[] getDays() {
		return days;
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	public int getTodays() {
		return todays;
	}

	public int getMemoday() {
		return memoday;
	}

	public JButton getSearchBtn() {
		return searchBtn;
	}

	public JButton getSaveBtn() {
		return saveBtn;
	}

	public JButton getDeleteBtn() {
		return deleteBtn;
	}

//	public static void main(String[] args) {
//		new AdminAcadCalDesign(null, null);
//	}

}
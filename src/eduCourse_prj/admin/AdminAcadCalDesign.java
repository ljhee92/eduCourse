package eduCourse_prj.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AdminAcadCalDesign extends JDialog {
	AdminHomeDesign awd;
		
		private JButton searchBtn,saveBtn,deleteBtn;
		private JButton[] calBtn;
		private JComboBox<Integer> yearCb,monthCb;
		private DefaultComboBoxModel<Integer> yearCbm, monthCbm;
		private JTextField memoJtf,timeJtf;
		private JLabel yearJb, monthJb;
		private JPanel calJp;
		
		private Map<String,String> memoMap;
		private Calendar today,cal;
		private String[] days = { "일", "월", "화", "수", "목", "금", "토" };
		private int year, month, day, todays, memoday = 0;
		private int buttonIndex;
		private int cnt;
		
		public AdminAcadCalDesign(AdminHomeDesign awd,String title ) {
			super(awd,title,true);
			this.awd = awd;
			
			today = Calendar.getInstance(); //달력 가져오기
			cal = new GregorianCalendar(); // 표준 달력
			year = today.get(Calendar.YEAR);
			month = today.get(Calendar.MONTH)+1;
			
			
			//1998~2024년 까지 연도 콤보박스저장
			yearCbm = new DefaultComboBoxModel<Integer>();
			for(int i=1998 ; i < 2025; i++) {
				yearCbm.addElement(i);
			}
			//1월~12월 까지 콤보박스 모델 저장		
			monthCbm = new DefaultComboBoxModel<Integer>();
			for(int i = 1 ; i < 13 ; i++) {
				monthCbm.addElement(i);
			}	
			//콤보박스에 모델값 저장
			yearCb = new JComboBox<Integer>(yearCbm);
			monthCb = new JComboBox<Integer>(monthCbm);
			
			yearJb = new JLabel("년");
			monthJb = new JLabel("월");
			
		     // 콤보박스와 레이블에 대한 최소 크기 설정
	        Dimension comboBoxDimension = new Dimension(80, 40); // 콤보박스 크기 설정
	        yearCb.setPreferredSize(comboBoxDimension); // 콤보박스에 최소 크기 설정
	        monthCb.setPreferredSize(comboBoxDimension); // 콤보박스에 최소 크기 설정
	        yearJb.setPreferredSize(new Dimension(30, 40)); // 레이블 크기 설정
	        monthJb.setPreferredSize(new Dimension(30, 40)); // 레이블 크기 설정
	        searchBtn = new JButton("조회");
	        
	        //달력
	        calJp = new JPanel(new GridLayout(7,7));
	        calBtn = new JButton[49];
	        for(int i=0; i<49; i++) {
	        	int clickBtnIndex = i;
	        	calBtn[i] = new JButton();
	        	calJp.add(calBtn[i]);
	        	//달력 버튼 하나당 ActionLinstner 추가
	        	calBtn[i].addActionListener(new ActionListener() {				
					@Override
					public void actionPerformed(ActionEvent e) {
						selectDay(clickBtnIndex-(6+cnt));
					}
				});
	        }
	        
	        calJp.setBounds(30,150,400,400);
	        //메모
	        JPanel memoJp = new JPanel(new BorderLayout());
	        memoJtf = new JTextField();
	        memoJtf.setPreferredSize(new Dimension(300, 300));
	        
	        JPanel memoBtnJp = new JPanel();
	        saveBtn = new JButton("저장");
	        deleteBtn = new JButton("삭제");
	        
	        memoJp.add(new JLabel("메모"),BorderLayout.NORTH);
	        memoJp.add(memoJtf, BorderLayout.CENTER);
	        memoBtnJp.add(saveBtn);
	        memoBtnJp.add(deleteBtn);
	        memoJp.add(memoBtnJp,BorderLayout.SOUTH);
	        
	        memoJp.setBounds(450,150,250,250);
	        
	        //메모 Map 초기화
	        memoMap = new HashMap<String, String>();
	        
	        //년도,월 선택
			JPanel yearSelectJP = new JPanel();
			JPanel monthSelectJP = new JPanel();
			
			
			//배치
			yearSelectJP.add(yearJb);
			yearSelectJP.add(yearCb);
			monthSelectJP.add(monthJb);
			monthSelectJP.add(monthCb);
			
			yearSelectJP.setBounds(160,20,150,200);
			monthSelectJP.setBounds(295,20,150,200);
			searchBtn.setBounds(460, 25 , 60, 40);
			
			AdminAcadCalEvent aace = new AdminAcadCalEvent(this);
			searchBtn.addActionListener(aace);
			saveBtn.addActionListener(aace);
			deleteBtn.addActionListener(aace);
			
			add(calJp);
			add(memoJp);
			add(yearSelectJP);
			add(monthSelectJP);
			add(searchBtn);
			
			setLayout(null);
			     
			setSize(1000,650);
			setLocationRelativeTo(null);
			setVisible(true);
		}//end constructor
		
		public void showCalSet() {
			cal.set(Calendar.YEAR, year);
		}

		public void calSet() {
			cal.set(Calendar.YEAR, (int)yearCb.getSelectedItem());
			cal.set(Calendar.MONTH, (int)monthCb.getSelectedItem()-1);
			cal.set(Calendar.DATE,1);
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
			
			cnt = 0;
			//토요일 일요일 구분
			calBtn[0].setForeground(new Color(255,0,0)); //일요일 "일"
			calBtn[6].setForeground(new Color(0,0,255)); //토요일 "토"
			for(int i=cal.getFirstDayOfWeek() ; i < dayOfWeek; i++) {
				cnt++;
			}
			
			//일요일~토요일까지
			for(int i=0 ; i<7 ; i++) {
				calBtn[i].setText(days[i]);
				calBtn[i].setForeground(Color.red);		
				calBtn[i].setEnabled(false);
				calBtn[i].setBackground(Color.white); 
			}
			//일요일부터 그달의 첫 시작요일까지 빈칸 세팅
			
			for(int i=0 ; i<cnt ; i++) {
				calBtn[i+7].setText("");
			}
			for(int i=1 ; i<=cal.getActualMaximum(Calendar.DAY_OF_MONTH) ; i++) {
				cal.set(Calendar.DATE, i);
		        int currentDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		        buttonIndex = i + 6 + cnt;
		        if (currentDayOfWeek == Calendar.SUNDAY) {
		            calBtn[buttonIndex].setForeground(new Color(255,0,0));
		        } else if (currentDayOfWeek == Calendar.SATURDAY) {
		            calBtn[buttonIndex].setForeground(new Color(0,0,255));
		        } else {
		            calBtn[buttonIndex].setForeground(new Color(0,0,0));
		        }
		        calBtn[buttonIndex].setText(String.valueOf(i));
		      
			}//end for		
			for(int i=0; i<49 ; i++) {
				if(calBtn[i].getText() == "") {
					calBtn[i].setEnabled(false);
				}
			}
		}//end calSet
		
		//selectDay
		public void selectDay(int day) {
			this.day = day;
		}
		
		//getter
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
		public JTextField getMemoJtf() {
			return memoJtf;
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
		public static void main(String[] args) {
			new AdminAcadCalDesign(null, null);
		}

}

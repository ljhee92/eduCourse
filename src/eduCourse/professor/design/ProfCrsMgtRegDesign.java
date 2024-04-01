package eduCourse.professor.design;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import eduCourse.VO.CrsVO;
import eduCourse.VO.LectureRoomVO;
import eduCourse.professor.dao.CrsMgtRegDAO;
import eduCourse.professor.dao.ProfDAO;
import eduCourse.professor.event.ProfCrsMgtRegEvent;

@SuppressWarnings("serial")
public class ProfCrsMgtRegDesign extends JDialog {

	private ProfCrsMgtDesign pcmd;
	private JLabel jlBack;// 배경
	private JLabel topLogin; // 우상단 로그인상태 확인창
	private JLabel jlProfCrsMgt, jlProfCrsMgtReg; // 상단 라벨
	private JLabel jlCrsName, jlCrsCode, jlDeptName, jlDeptCode, jlProfName,
					jlLectRoom, jlCredit, jlCapa, jlNecessary;

	private JComboBox<String> jcbCrsName, jcbLectRoom;
	private JTextField jtfCrsCode, jtfDeptName, jtfDeptCode, jtfProfName, jtfCredit, jtfCapa;
	private JButton jbtnReg, jbtnCancel;

	public ProfCrsMgtRegDesign(ProfCrsMgtDesign pcmd, String title) {
		super(pcmd, title, true);
		this.pcmd = pcmd;

		setLayout(null);

		String commonPath = "src/eduCourse/image/common/";
		String profPath = "src/eduCourse/image/prof/";

		// 우상단 로그인상태 확인창 추가
		topLogin = new JLabel(pcmd.getPhd().getlVO().getName() + " 교수님 로그인 중");
		Font font = new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 17);
		Font fonts = new Font("나눔스퀘어라운드 ExtraBold", Font.BOLD, 13);
		topLogin.setFont(font);
		topLogin.setForeground(Color.WHITE);
		topLogin.setBounds(670, 30, 300, 20);
		add(topLogin);

		// 강의 과목 관리, 등록 상단 라벨 추가
		jlProfCrsMgt = new JLabel(new ImageIcon(profPath + "ProfCrsMgt_Label.png"));
		jlProfCrsMgtReg = new JLabel(new ImageIcon(commonPath + "RegBanner_new.png"));
		
		jlProfCrsMgt.setBounds(10, 76, 967, 44);
		jlProfCrsMgtReg.setBounds(10, 118, 967, 45);
		
		add(jlProfCrsMgt);
		add(jlProfCrsMgtReg);
		
		// 과목, 과목 코드, 학과, 학과 코드, 담당 교수, 강의실, 학점, 정원, 필수입력사항 라벨 추가
		jlCrsName = new JLabel("과목");
		jlCrsCode = new JLabel("과목 코드");
		jlDeptName = new JLabel("학과");
		jlDeptCode = new JLabel("학과 코드");
		jlProfName = new JLabel("담당 교수");
		jlLectRoom = new JLabel("강의실");
		jlCredit = new JLabel("학점");
		jlCapa = new JLabel("정원");
		jlNecessary = new JLabel("정원은 필수입력사항입니다.");

		jlCrsName.setBounds(120, 220, 100, 20);
		jlCrsCode.setBounds(120, 280, 100, 20);
		jlDeptName.setBounds(120, 340, 100, 20);
		jlDeptCode.setBounds(120, 400, 100, 20);
		jlProfName.setBounds(520, 220, 100, 20);
		jlLectRoom.setBounds(520, 280, 100, 20);
		jlCredit.setBounds(520, 340, 100, 20);
		jlCapa.setBounds(520, 400, 100, 20);
		jlNecessary.setBounds(690, 430, 300, 20);

		jlCrsName.setFont(font);
		jlCrsCode.setFont(font);
		jlDeptName.setFont(font);
		jlDeptCode.setFont(font);
		jlProfName.setFont(font);
		jlLectRoom.setFont(font);
		jlCredit.setFont(font);
		jlCapa.setFont(font);
		jlNecessary.setFont(fonts);
		jlNecessary.setForeground(Color.RED);

		add(jlCrsName);
		add(jlCrsCode);
		add(jlDeptName);
		add(jlDeptCode);
		add(jlProfName);
		add(jlLectRoom);
		add(jlCredit);
		add(jlCapa);
		add(jlNecessary);

		// 과목, 과목 코드, 학과, 학과 코드, 담당 교수, 강의실, 학점, 정원 콤보박스, JTF 추가
		ProfDAO pDAO = ProfDAO.getInstance();
		CrsMgtRegDAO cmrDAO = CrsMgtRegDAO.getInstance();
		try {
			// 학과이름 콤보박스 추가
			List<CrsVO> listCVO = pDAO.slctNotLectCrs(Integer.parseInt(pcmd.getPhd().getlVO().getId()));
			jcbCrsName = new JComboBox<String>();

			for (CrsVO cVO : listCVO) {
				jcbCrsName.addItem(cVO.getCourName());
			} // end for

			// 강의실 콤보박스 추가
			List<LectureRoomVO> listLectRoomVO = cmrDAO.selectAllLectRoom();
			jcbLectRoom = new JComboBox<String>();

			for (LectureRoomVO lrVO : listLectRoomVO) {
				jcbLectRoom.addItem(lrVO.getLect_room_num());
			} // end for

			jtfCrsCode = new JTextField();
			jtfDeptName = new JTextField(listCVO.get(0).getDeptName());
			jtfDeptCode = new JTextField(String.valueOf(listCVO.get(0).getDeptCode()));
			jtfProfName = new JTextField(pcmd.getPhd().getlVO().getName());
			jtfCredit = new JTextField();
			jtfCapa = new JTextField();
		} catch (IndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(pcmd, "등록 가능한 과목이 없습니다.");
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(pcmd, "SQL문제가 발생했습니다.");
			e.printStackTrace();
		} // end catch

		jcbCrsName.setBounds(220, 215, 220, 30);
		jtfCrsCode.setBounds(220, 275, 220, 30);
		jtfDeptName.setBounds(220, 335, 220, 30);
		jtfDeptCode.setBounds(220, 395, 220, 30);
		jtfProfName.setBounds(620, 215, 220, 30);
		jcbLectRoom.setBounds(620, 275, 220, 30);
		jtfCredit.setBounds(620, 335, 220, 30);
		jtfCapa.setBounds(620, 395, 220, 30);
		
		jcbCrsName.setFont(font);
		jtfCrsCode.setFont(font);
		jtfDeptName.setFont(font);
		jtfDeptCode.setFont(font);
		jtfProfName.setFont(font);
		jcbLectRoom.setFont(font);
		jtfCredit.setFont(font);
		jtfCapa.setFont(font);

		jtfCrsCode.setEditable(false);
		jtfDeptName.setEditable(false);
		jtfDeptCode.setEditable(false);
		jtfProfName.setEditable(false);
		jtfCredit.setEditable(false);

		add(jcbCrsName);
		add(jtfCrsCode);
		add(jtfDeptName);
		add(jtfDeptCode);
		add(jtfProfName);
		add(jcbLectRoom);
		add(jtfCredit);
		add(jtfCapa);

		// 등록, 취소 버튼 추가
		jbtnReg = new JButton(new ImageIcon(commonPath + "RegButton_new.png"));
		jbtnCancel = new JButton(new ImageIcon(commonPath + "CancelButton_new.png"));

		jbtnReg.setBounds(370, 510, 90, 50);
		jbtnCancel.setBounds(520, 510, 90, 50);

		add(jbtnReg);
		add(jbtnCancel);

		// 이벤트 클래스 연결
		ProfCrsMgtRegEvent pcmre = new ProfCrsMgtRegEvent(this);
		addWindowListener(pcmre);
		jcbCrsName.addActionListener(pcmre);
		jbtnReg.addActionListener(pcmre);
		jbtnCancel.addActionListener(pcmre);

		// 배경 추가
		jlBack = new JLabel(new ImageIcon(commonPath + "Back.png"));
		jlBack.setBounds(0, 0, 984, 620);
		add(jlBack);
		setSize(1000, 650);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	} // ProfCrsMgtRegDesign

	public JComboBox<String> getJcbCrsName() {
		return jcbCrsName;
	}

	public JTextField getJtfCrsCode() {
		return jtfCrsCode;
	}

	public JComboBox<String> getJcbLectRoom() {
		return jcbLectRoom;
	}

	public JTextField getJtfCapa() {
		return jtfCapa;
	}

	public JTextField getJtfCredit() {
		return jtfCredit;
	}

	public JButton getJbtnReg() {
		return jbtnReg;
	}

	public JButton getJbtnCancel() {
		return jbtnCancel;
	}

	public ProfCrsMgtDesign getPcmd() {
		return pcmd;
	}

} // class
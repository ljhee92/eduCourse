package eduCourse_prj.professor.design;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import eduCourse_prj.VO.CrsRegVO;

import eduCourse_prj.VO.LectureRoomVO;
import eduCourse_prj.professor.dao.CrsMgtRegDAO;

import eduCourse_prj.professor.event.ProfCrsMgtMdfyEvent;

@SuppressWarnings("serial")
public class ProfCrsMgtMdfyDesign extends JDialog {

	private ProfCrsMgtDesign pcmd;
	private JLabel jlBack;// 배경
	private JLabel topLogin; // 우상단 로그인상태 확인창

	private JLabel jlProfCrsMgt, jlProfCrsMgtMdfy; // 상단 라벨
	private JLabel jlCrsName, jlCrsCode, jlDeptName, jlDeptCode, jlProfName,
					jlLectRoom, jlCredit, jlCapa, jlNecessary;

	private JComboBox<String> jcbLectRoom;
	private JTextField jtfCrsName, jtfCrsCode, jtfDeptName, jtfDeptCode, jtfProfName, jtfCredit, jtfCapa;
	private JButton jbtnMdfy, jbtnCancel;

	public ProfCrsMgtMdfyDesign(ProfCrsMgtDesign pcmd, String title) {
		super(pcmd, title, true);
		this.pcmd = pcmd;

		setLayout(null);

		String commonPath = "src/eduCourse_prj/image/common/";
		String profPath = "src/eduCourse_prj/image/prof/";

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
		jlProfCrsMgtMdfy = new JLabel(new ImageIcon(commonPath + "MdfyBanner_new.png"));
		
		jlProfCrsMgt.setBounds(10, 76, 967, 44);
		jlProfCrsMgtMdfy.setBounds(10, 118, 967, 45);
		
		add(jlProfCrsMgt);
		add(jlProfCrsMgtMdfy);

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
		CrsMgtRegDAO cmrDAO = CrsMgtRegDAO.getInstance();
		int index = pcmd.getJtbLecMgt().getSelectedRow();
		String course_name = pcmd.getDtmProfMgt().getValueAt(index, 1).toString();
		try {
			CrsRegVO crVO = cmrDAO.slctProfOneLect(course_name);

			// 모든 강의실 콤보박스에 추가
			List<LectureRoomVO> listLectRoomVO = cmrDAO.selectAllLectRoom();
			jcbLectRoom = new JComboBox<String>();

			for (LectureRoomVO lrVO : listLectRoomVO) {
				jcbLectRoom.addItem(lrVO.getLect_room_num());
			} // end for

			jtfCrsName = new JTextField(crVO.getCourse_name());
			jtfCrsCode = new JTextField(crVO.getCourse_code());
			jtfDeptName = new JTextField(crVO.getDept_name());
			jtfDeptCode = new JTextField(String.valueOf(crVO.getDept_code()));
			jtfProfName = new JTextField(pcmd.getPhd().getlVO().getName());
			jtfCredit = new JTextField(String.valueOf(crVO.getCredit_hours()));
			jtfCapa = new JTextField(String.valueOf(crVO.getCapacity()));

			// 선택된 과목의 강의실을 콤보박스에서 선택된 값으로 변경
			LectureRoomVO lectureRoomVO = cmrDAO.selectOneLectRoom(crVO.getCourse_code());
			jcbLectRoom.setSelectedItem(lectureRoomVO.getLect_room_num());

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

		jtfCrsName.setBounds(220, 215, 220, 30);
		jtfCrsCode.setBounds(220, 275, 220, 30);
		jtfDeptName.setBounds(220, 335, 220, 30);
		jtfDeptCode.setBounds(220, 395, 220, 30);
		jtfProfName.setBounds(620, 215, 220, 30);
		jcbLectRoom.setBounds(620, 275, 220, 30);
		jtfCredit.setBounds(620, 335, 220, 30);
		jtfCapa.setBounds(620, 395, 220, 30);

		jtfCrsName.setEditable(false);
		jtfCrsCode.setEditable(false);
		jtfDeptName.setEditable(false);
		jtfDeptCode.setEditable(false);
		jtfProfName.setEditable(false);
		jtfCredit.setEditable(false);

		add(jtfCrsName);
		add(jtfCrsCode);
		add(jtfDeptName);
		add(jtfDeptCode);
		add(jtfProfName);
		add(jcbLectRoom);
		add(jtfCredit);
		add(jtfCapa);

		// 수정, 취소 버튼 추가
		jbtnMdfy = new JButton(new ImageIcon(commonPath + "MdfyButton_new.png"));
		jbtnCancel = new JButton(new ImageIcon(commonPath + "CancelButton_new.png"));

		jbtnMdfy.setBounds(370, 510, 90, 50);
		jbtnCancel.setBounds(520, 510, 90, 50);

		add(jbtnMdfy);
		add(jbtnCancel);

		// 이벤트 클래스 연결
		ProfCrsMgtMdfyEvent pcmme = new ProfCrsMgtMdfyEvent(this);
		addWindowListener(pcmme);
		jbtnMdfy.addActionListener(pcmme);
		jbtnCancel.addActionListener(pcmme);

		// 배경 추가
		jlBack = new JLabel(new ImageIcon(commonPath + "Back.png"));
		jlBack.setBounds(0, 0, 984, 620);
		add(jlBack);
		setSize(1000, 650);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	} // ProfCrsMgtMdfyDesign

	public JComboBox<String> getJcbLectRoom() {
		return jcbLectRoom;
	}

	public JTextField getJtfCrsName() {
		return jtfCrsName;
	}

	public JTextField getJtfCrsCode() {
		return jtfCrsCode;
	}

	public JTextField getJtfCredit() {
		return jtfCredit;
	}

	public JTextField getJtfCapa() {
		return jtfCapa;
	}

	public JButton getJbtnMdfy() {
		return jbtnMdfy;
	}

	public JButton getJbtnCancel() {
		return jbtnCancel;
	}

	public ProfCrsMgtDesign getPcmd() {
		return pcmd;
	}

} // class
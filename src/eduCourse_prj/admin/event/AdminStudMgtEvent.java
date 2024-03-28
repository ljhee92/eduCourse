package eduCourse_prj.admin.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import eduCourse_prj.VO.CrsVO;
import eduCourse_prj.VO.SlctStdVO;
import eduCourse_prj.VO.StdntVO;
import eduCourse_prj.admin.dao.AdminDAO;
import eduCourse_prj.admin.design.AdminStudMgtDesign;
import eduCourse_prj.student.dao.StdntDAO;

public class AdminStudMgtEvent extends WindowAdapter implements ActionListener {

	private AdminStudMgtDesign asmd;
	private AdminDAO aDAO = AdminDAO.getInstance();

	public AdminStudMgtEvent(AdminStudMgtDesign asmd) {

		this.asmd = asmd;

	}

	@Override
	public void windowClosing(WindowEvent we) {
		asmd.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == asmd.getJbtnSlctTop()) {

			asmd.getDtmStdMgt().setRowCount(0);

			int dept_code = 0; // 학과 코드
			String crs_code = ""; // 과목 코드
			int std_num = 0; // 학번
			
			// 학과가 "전체"일 경우
			if (asmd.getJcbDept().getSelectedItem().equals("전체")) {

				// 과목이 "전체"일 경우
				if (asmd.getJcbCrs().getSelectedItem().equals("전체")) {

				} else {// 과목이 "전체"가 아닌 모든 경우
					crs_code = asmd.getLCrs().get(asmd.getJcbCrs().getSelectedIndex() - 1).getCourCode();

				}

			} else {// 학과가 "전체"가 아닌 모든 경우
				dept_code = asmd.getLDept().get((asmd.getJcbDept().getSelectedIndex() - 1)).getDept_code();

				// 과목이 "전체"일 경우
				if (asmd.getJcbCrs().getSelectedItem().equals("전체")) {

				} else {// 과목이 "전체"가 아닌 모든 경우 //문제 발견 ->

					crs_code = (asmd.getLCrs().get(asmd.getJcbCrs().getSelectedIndex() - 1)).getCourCode();

				}

			}

			// 학번 입력 유무 체크
			if (!(asmd.getJtfStdNum().getText().isEmpty())) {
				try {

					std_num = Integer.parseInt(asmd.getJtfStdNum().getText());
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(asmd, "숫자만 입력가능 9자리");
					return;
				}
			}

			try {
				List<SlctStdVO> listSlctStdVO = aDAO.slctStd(dept_code, crs_code, std_num);

				for (SlctStdVO ssVO : listSlctStdVO) {

					Object[] row = { ssVO.getDept_name(), ssVO.getCrs_name(), ssVO.getStd_num(), ssVO.getStd_name() };
					asmd.getDtmStdMgt().addRow(row);
				} // end for
				
				if(asmd.getDtmStdMgt().getRowCount() == 0) {
					JOptionPane.showMessageDialog(asmd, "검색된 정보가 없습니다.");
					return;
				} // end if

			} catch (SQLException e) {
				e.printStackTrace();
			} // end catch

		} // end if

		if (ae.getSource() == asmd.getJbtnSlct()) {

			// 학생 정보 상세 조회 클릭
			if (ae.getSource() == asmd.getJbtnSlct()) {
				int index = asmd.getJtbStdMgt().getSelectedRow();
				if (index == -1) {
					JOptionPane.showMessageDialog(asmd, "조회할 학생을 선택해주세요.");
					return;
				} // end if
				try {
					int stud_number = Integer.parseInt(asmd.getDtmStdMgt().getValueAt(index, 2).toString());
					String dept_name = asmd.getDtmStdMgt().getValueAt(index, 0).toString();
					StdntDAO sDAO = StdntDAO.getInstance();
					StdntVO sVO = sDAO.slctOneStdnt(stud_number);

					StringBuilder output = new StringBuilder();
					output.append("학과: ").append(dept_name).append("\n");
					output.append("학번: ").append(stud_number).append("\n");
					output.append("이름: ").append(sVO.getStdnt_name()).append("\n");
					output.append("이메일: ").append(sVO.getStdnt_email() != null ? sVO.getStdnt_email() : "")
							.append("\n");
					output.append("주소: ").append(sVO.getStdnt_addr());

					JTextArea jta = new JTextArea(output.toString(), 8, 50);
					JScrollPane jsp = new JScrollPane(jta);
					jta.setEditable(false);
					JOptionPane.showMessageDialog(asmd, jsp, "학생 상세 조회", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(asmd, "SQL 문제가 발생했습니다.");
					e1.printStackTrace();
				} // end catch
			} // end if

		}

		if (ae.getSource() == asmd.getJcbDept()) {
			int dept_code = 0;
			asmd.getJcbCrs().removeAllItems();
			asmd.getJcbCrs().addItem("전체");
			asmd.getLCrs().clear();

			// 학과 콤보박스(jcbDept)를 클릭시 선택된 아이템의 인덱스와 동일한 인덱스를 lDept(DeptVO가 저장되어있음)에서 선택하여
			// DeptVo객체에서 dept_code를 얻어냄.

			if (asmd.getJcbDept().getSelectedItem().equals("전체")) {

				// 모든 과목 정보 가져오기

				List<CrsVO> listCrsVO = new ArrayList<CrsVO>();

				try {
					listCrsVO = aDAO.slctAllCrs();

					// 과목명만 저장하는 리스트에 과목명 저장
					for (CrsVO cVO : listCrsVO) {
						asmd.getJcbCrs().addItem(cVO.getCourName());
						asmd.getLCrs().add(cVO);

					}
				} catch (SQLException e) {

					e.printStackTrace();
				}

			} else

				dept_code = asmd.getLDept().get(asmd.getJcbDept().getSelectedIndex() - 1).getDept_code();

			List<CrsVO> listCrsVO;
			try {

				listCrsVO = aDAO.slctDeptCrs(dept_code);

				for (CrsVO cVO : listCrsVO) {

					asmd.getJcbCrs().addItem(cVO.getCourName());
					asmd.getLCrs().add(cVO);

				}

			} catch (SQLException e) {
				JOptionPane.showConfirmDialog(asmd, "에러발생");
				e.printStackTrace();
			}

		}

	}

}

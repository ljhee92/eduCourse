package eduCourse.professor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eduCourse.VO.CrsRegVO;
import eduCourse.VO.CrsVO;
import eduCourse.VO.LectureRoomVO;
import eduCourse.VO.LectureVO;
import eduCourse.resources.DbConnection;

public class CrsMgtRegDAO {
	private static CrsMgtRegDAO cmrDAO;

	private CrsMgtRegDAO() {
	}

	public static CrsMgtRegDAO getInstance() {
		if (cmrDAO == null) {
			cmrDAO = new CrsMgtRegDAO();
		} // end if
		return cmrDAO;
	} // getInstance

	/**
	 * 교수모드 > 강의 과목 관리 > 등록 시 로그인한 교수의 강의 과목 리스트를 조회하는 method
	 * @param prof_number
	 * @return
	 * @throws SQLException
	 */
	public List<CrsVO> slctProfLect(int prof_number) throws SQLException {
		DbConnection dbConnection = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CrsVO cVO = null;
		List<CrsVO> courses = null;
		try {
			con = dbConnection.getConnection();

			StringBuilder sb = new StringBuilder();
			sb.append("select d.dept_name, d.dept_code, c.course_name, l.course_code ");
			sb.append("from lecture l ");
			sb.append("join professor p on p.prof_number = l.prof_number ");
			sb.append("join course c on c.course_code = l.course_code ");
			sb.append("join dept d on d.dept_code = c.dept_code ");
			sb.append("where p.prof_number = ? and lect_delete_flag = 'N' and d.dept_delete_flag = 'N'");

			String slctCrsQuery = sb.toString();
			pstmt = con.prepareStatement(slctCrsQuery);
			pstmt.setInt(1, prof_number);
			rs = pstmt.executeQuery();

			String deptName = "", courseName = "", courseCode = "";
			int deptCode = 0;

			courses = new ArrayList<CrsVO>();
			while (rs.next()) {
				deptName = rs.getString("dept_name");
				deptCode = rs.getInt("dept_code");
				courseName = rs.getString("course_name");
				courseCode = rs.getString("course_code");
				cVO = new CrsVO(courseCode, courseName, deptName, deptCode);
				courses.add(cVO);
			} // end while
		} finally {
			dbConnection.dbClose(rs, pstmt, con);
		} // end finally
		return courses;
	}// slctProfLect

	/**
	 * 교수모드 > 강의 과목 관리 > 등록 시 로그인한 교수의 강의 중 한 과목을 조회하는 method
	 * @param prof_number
	 * @return
	 * @throws SQLException
	 */
	public CrsRegVO slctProfOneLect(String course_name) throws SQLException {
		DbConnection dbConnection = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CrsRegVO crVO = null;
		try {
			con = dbConnection.getConnection();

			StringBuilder sb = new StringBuilder();
			sb.append(
					"select c.course_name, l.course_code, p.prof_name, l.lect_room, c.credit_hours, l.capacity, d.dept_name, d.dept_code ");
			sb.append("from lecture l ");
			sb.append("join professor p on p.prof_number = l.prof_number ");
			sb.append("join course c on c.course_code = l.course_code ");
			sb.append("join dept d on d.dept_code = c.dept_code ");
			sb.append("where c.course_name = ? and course_delete_flag = 'N'");

			String slctCrsQuery = sb.toString();
			pstmt = con.prepareStatement(slctCrsQuery);
			pstmt.setString(1, course_name);
			rs = pstmt.executeQuery();

			String dept_name = "", course_code = "", lect_room = "";
			int dept_code = 0, capacity = 0, credit_hours = 0;

			while (rs.next()) {
				dept_code = rs.getInt("dept_code");
				dept_name = rs.getString("dept_name");
				course_code = rs.getString("course_code");
				lect_room = rs.getString("lect_room");
				capacity = rs.getInt("capacity");
				credit_hours = rs.getInt("credit_hours");
				crVO = new CrsRegVO(dept_code, dept_name, course_name, course_code, lect_room, capacity, credit_hours);
			} // end while
		} finally {
			dbConnection.dbClose(rs, pstmt, con);
		} // end finally
		return crVO;
	}// slctProfOneLect

	/**
	 * 교수모드 > 강의과목관리 > 등록, 수정 시 선택한 과목명의 과목코드를 가져오는 method
	 * @param CrsName
	 * @return
	 * @throws SQLException
	 */
	public CrsVO slctOneCrsCode(String CrsName) throws SQLException {
		CrsVO cVO = null;
		DbConnection dbConnection = DbConnection.getInstance();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = dbConnection.getConnection();

			String slctOneCrsCode = "select course_code, credit_hours from course where course_name = ?";
			pstmt = con.prepareStatement(slctOneCrsCode);
			pstmt.setString(1, CrsName);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cVO = new CrsVO(rs.getString("course_code"), CrsName, rs.getInt("credit_hours"));
			} // end if
		} finally {
			dbConnection.dbClose(rs, pstmt, con);
		} // end finally

		return cVO;
	} // slctOneCrsCode

	/**
	 * 교수관리 > 강의과목관리 > 등록 시 Lecture 테이블로 저장하는 method
	 * @param ltVO
	 * @throws SQLException
	 */
	public void insertLect(LectureVO ltVO) throws SQLException {
		DbConnection dbConnection = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = dbConnection.getConnection();

			StringBuilder insertLect = new StringBuilder();
			insertLect.append("insert into lecture(prof_number, course_code, capacity, lect_room, test_flag) ")
					.append("values(?, ?, ?, ?, 'N')");

			pstmt = con.prepareStatement(insertLect.toString());

			pstmt.setInt(1, ltVO.getProf_number());
			pstmt.setString(2, ltVO.getCourse_code());
			pstmt.setInt(3, ltVO.getCapacity());
			pstmt.setString(4, ltVO.getLect_room());

			pstmt.executeUpdate();
		} finally {
			dbConnection.dbClose(null, pstmt, con);
		} // end finally
	} // insertLect

	/**
	 * 교수모드 > 강의과목관리에서 선택한 과목을 삭제하는 일
	 * @param prof_number
	 * @param course_code
	 * @throws SQLException
	 */
	public void deleteLect(int prof_number, String course_code) throws SQLException {
		DbConnection dbConnection = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = dbConnection.getConnection();

			String deleteLect = "update lecture set lect_delete_flag = 'Y' where prof_number = ? and course_code = ?";
			pstmt = con.prepareStatement(deleteLect);

			pstmt.setInt(1, prof_number);
			pstmt.setString(2, course_code);

			pstmt.executeUpdate();
		} finally {
			dbConnection.dbClose(null, pstmt, con);
		} // end finally
	} // deleteLect

	/**
	 * 교수모드 > 강의과목관리에서 선택한 과목의 강의실과 정원을 수정하는 일
	 * @param crVO
	 * @throws SQLException
	 */
	public void updateLect(LectureVO ltVO) throws SQLException {
		DbConnection dbConnection = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = dbConnection.getConnection();

			String deleteLect = "update lecture set capacity = ?, lect_room = ? where prof_number = ? and course_code = ?";
			pstmt = con.prepareStatement(deleteLect);

			pstmt.setInt(1, ltVO.getCapacity());
			pstmt.setString(2, ltVO.getLect_room());
			pstmt.setInt(3, ltVO.getProf_number());
			pstmt.setString(4, ltVO.getCourse_code());

			pstmt.executeUpdate();
		} finally {
			dbConnection.dbClose(null, pstmt, con);
		} // end finally
	} // updateLect

	/**
	 * 교수모드 > 강의 과목 등록에 DB의 강의실을 가져오기 위한 method
	 * @return
	 * @throws SQLException
	 */
	public List<LectureRoomVO> selectAllLectRoom() throws SQLException {
		List<LectureRoomVO> listLectRoom = new ArrayList<LectureRoomVO>();
		LectureRoomVO lrVO = null;

		DbConnection dbConnection = DbConnection.getInstance();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = dbConnection.getConnection();

			String selectAllLectRoom = "select lect_room_num, dept_code from lecture_room";

			pstmt = con.prepareStatement(selectAllLectRoom);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lrVO = new LectureRoomVO(rs.getString("lect_room_num"), rs.getInt("dept_code"));
				listLectRoom.add(lrVO);
			} // end if
		} finally {
			dbConnection.dbClose(rs, pstmt, con);
		} // end finally

		return listLectRoom;
	} // selectAllLectRoom

	/**
	 * 교수모드 > 강의 과목 수정에서 선택한 과목코드의 강의실을 가져오기 위한 method
	 * @param courseCode
	 * @return
	 */
	public LectureRoomVO selectOneLectRoom(String courseCode) throws SQLException {
		LectureRoomVO lectureRoomVO = null;

		DbConnection dbConnection = DbConnection.getInstance();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = dbConnection.getConnection();

			String selectAllLectRoom = "select lect_room from lecture where course_code = ?";

			pstmt = con.prepareStatement(selectAllLectRoom);
			pstmt.setString(1, courseCode);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lectureRoomVO = new LectureRoomVO(rs.getString("lect_room"));
			} // end if
		} finally {
			dbConnection.dbClose(rs, pstmt, con);
		} // end finally

		return lectureRoomVO;
	} // selectOneLectRoom

	/**
	 * 해당 과목을 수강중인 학생의 수를 찾아 학생의 수가 수정될 정원수보다 적은지 확인하는 method
	 * @param course_code 학과코드
	 * @param capacity    수정될 정원수
	 * @return 수정 가능여부 (수정될 정원수>=수강중인 학생수) -> true
	 * @throws SQLException
	 */
	public boolean checkEditable(String course_code, int capacity) throws SQLException {
		boolean result = false;

		DbConnection dbConnection = DbConnection.getInstance();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = dbConnection.getConnection();

			String checkEditable = "	select CAPACITED	" + "	from LECTURE	" + "	where COURSE_CODE = ?	";

			pstmt = con.prepareStatement(checkEditable);
			pstmt.setString(1, course_code);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (capacity >= rs.getInt("CAPACITED")) {
					result = true;
				} // end if
			} // end if
		} finally {
			dbConnection.dbClose(rs, pstmt, con);
		} // end finally

		return result;
	} // checkEditable
} // class
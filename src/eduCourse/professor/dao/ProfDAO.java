package eduCourse.professor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eduCourse.VO.AdminProfVO;
import eduCourse.VO.CrsVO;

import eduCourse.VO.LoginVO;
import eduCourse.VO.ProfLectStudVO;
import eduCourse.VO.ProfVO;

import eduCourse.VO.TestListVO;
import eduCourse.resources.DbConnection;

public class ProfDAO {
	private static ProfDAO pDAO;

	private ProfDAO() {
	}

	public static ProfDAO getInstance() {
		if (pDAO == null) {
			pDAO = new ProfDAO();
		} // end if
		return pDAO;
	} // getInstance

	/**
	 * 교수 로그인 정보를 가져오기 위한 DAO
	 * @param lVO
	 * @return
	 * @throws SQLException
	 */
	public LoginVO professorLogin(LoginVO lVO) throws SQLException {
		LoginVO lresultVO = null;
		DbConnection dbConnection = DbConnection.getInstance();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = dbConnection.getConnection();

			String selectQuery = "SELECT PROF_NUMBER, PROF_PASSWORD ,PROF_NAME FROM professor WHERE  PROF_NUMBER = ? AND PROF_PASSWORD = ?";
			pstmt = con.prepareStatement(selectQuery);

			pstmt.setString(1, lVO.getId());
			pstmt.setString(2, lVO.getPassword());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				lresultVO = new LoginVO(rs.getString("PROF_NUMBER"), rs.getString("PROF_PASSWORD"),
						rs.getString("PROF_NAME"));
			} // end if
		} finally {
			dbConnection.dbClose(rs, pstmt, con);
		} // end finally
		return lresultVO;
	} // professorLogin

	/**
	 * 관리자모드 과목관리의 특정학과의 교수의 교번, 이름을 가져오기 위한 DAO
	 * @return 특정학과의 교수 리스트
	 * @throws SQLException
	 */
	public List<ProfVO> slctDeptProf(int dept_code) throws SQLException {
		List<ProfVO> listProfVO = new ArrayList<ProfVO>();
		ProfVO pVO = null;
		DbConnection dbConnection = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = dbConnection.getConnection();

			String selectDeptProf = "select prof_number, prof_name from professor where prof_delete_flag = 'N' and DEPT_CODE = ? order by prof_number";
			pstmt = con.prepareStatement(selectDeptProf);
			pstmt.setInt(1, dept_code);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				pVO = new ProfVO(rs.getInt("prof_number"), rs.getString("prof_name"));
				listProfVO.add(pVO);
			} // end while
		} finally {
			dbConnection.dbClose(rs, pstmt, con);
		} // end finally

		return listProfVO;
	} // slctDeptProf

	/**
	 * 관리자모드 교수관리의 교번, 이름, 학과 번호를 가져오기 위한 DAO
	 * @return
	 * @throws SQLException
	 */
	public List<ProfVO> slctProfMgt() throws SQLException {
		List<ProfVO> listProfVO = new ArrayList<ProfVO>();
		ProfVO pVO = null;
		DbConnection dbConnection = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = dbConnection.getConnection();

			String selectProfMgt = "	select p.prof_number, p.prof_name , d.dept_name	" + "	from professor p	"
					+ "	JOIN dept d ON p.dept_code = d.dept_code	"
					+ "	where prof_delete_flag = 'N' order by dept_name	";
			pstmt = con.prepareStatement(selectProfMgt);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				pVO = new ProfVO(rs.getInt("prof_number"), rs.getString("prof_name"), rs.getString("dept_name"));
				listProfVO.add(pVO);
			} // end while
		} finally {
			dbConnection.dbClose(rs, pstmt, con);
		} // end finally

		return listProfVO;
	} // slctProfMgt

	/**
	 * 관리자 모드 > 교수 관리 > 교수 상세 조회, 교수 모드 > 교수 메인을 위한 method
	 * @param prof_number
	 * @return
	 * @throws SQLException
	 */
	public AdminProfVO slctProfMgtSlct(int prof_number) throws SQLException {
		AdminProfVO apVO = null;
		DbConnection dbConnection = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = dbConnection.getConnection();

			StringBuilder slcQuery = new StringBuilder();
			slcQuery.append("SELECT p.PROF_NUMBER, p.PROF_NAME, p.PROF_EMAIL, d.dept_name, c.COURSE_NAME ");
			slcQuery.append("FROM PROFESSOR p ");
			slcQuery.append("JOIN lecture s ON p.PROF_NUMBER = s.PROF_NUMBER ");
			slcQuery.append("JOIN COURSE c ON s.COURSE_code = c.COURSE_code ");
			slcQuery.append("JOIN dept d ON d.dept_code = p.dept_code ");
			slcQuery.append("WHERE p.PROF_NUMBER = ?");
			String slcProfQuery = slcQuery.toString();

			pstmt = con.prepareStatement(slcProfQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			pstmt.setInt(1, prof_number);
			rs = pstmt.executeQuery();

			List<String> courses = new ArrayList<>();
			/////////////////// 과목이 존재한다면//////////////////////////
			if (rs.next()) {
				rs.beforeFirst();
				while (rs.next()) {
					courses.add(rs.getString("course_name"));
					apVO = new AdminProfVO(rs.getInt("prof_number"), rs.getString("prof_name"),
							rs.getString("prof_email"), rs.getString("dept_name"), courses);
				} // end while
			} // end if
			else {
				///////////////// 과목이 없다면//////////////////////////
				rs.beforeFirst();
				pstmt.close(); // 닫기
				rs.close(); // 닫기

				// 새로운 쿼리 생성
				slcQuery.delete(0, slcQuery.length());
				slcQuery.append("SELECT p.PROF_NUMBER, p.PROF_NAME, p.PROF_EMAIL, d.DEPT_NAME ");
				slcQuery.append("FROM PROFESSOR p ");
				slcQuery.append("JOIN DEPT d ON p.DEPT_CODE = d.DEPT_CODE ");
				slcQuery.append("WHERE p.PROF_NUMBER = ?");
				slcProfQuery = slcQuery.toString();
				pstmt = con.prepareStatement(slcProfQuery);
				pstmt.setInt(1, prof_number);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					apVO = new AdminProfVO(rs.getInt("prof_number"), rs.getString("prof_name"),
							rs.getString("prof_email"), rs.getString("dept_name"), courses);
				} // end if
			} // end else
		} finally {
			dbConnection.dbClose(rs, pstmt, con);
		} // end finally

		return apVO;
	} // slctProfMgtSlct

	/**
	 * 관리자모드 > 교수 등록 구현부
	 * @param pVO
	 * @throws SQLException
	 */
	public void insertProf(ProfVO pVO) throws SQLException {
		DbConnection dbConnection = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;

		try {
			con = dbConnection.getConnection();

			// 교수 번호 확인 후, max 교번+1로 추가
			int prof_num = 000000001;
			String checkProfNum = "select max(prof_number) from professor";
			pstmt = con.prepareStatement(checkProfNum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int lastProfNum = rs.getInt(1);
				prof_num = lastProfNum + 1;
			} // end if

			String addProf = "insert into professor(PROF_NUMBER, PROF_PASSWORD, PROF_NAME, PROF_EMAIL, DEPT_CODE)"
					+ "	values(?, ?, ?, ?, (select dept_code from dept where dept_name = ?))";
			pstmt2 = con.prepareStatement(addProf);

			pstmt2.setInt(1, prof_num);
			pstmt2.setString(2, pVO.getProf_password());
			pstmt2.setString(3, pVO.getProf_name());
			pstmt2.setString(4, pVO.getProf_email());
			pstmt2.setString(5, pVO.getDept_name());

			pstmt2.executeUpdate();
		} finally {
			dbConnection.dbClose(rs, pstmt, con);
			dbConnection.dbClose(rs, pstmt2, con);
		} // end finally
	} // addProf

	/**
	 * 관리자모드 > 교수 관리에서 교수 삭제를 위한 method
	 * @param prof_number
	 * @throws SQLException
	 */
	public void deleteProf(int prof_number) throws SQLException {
		DbConnection dbConnection = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = dbConnection.getConnection();

			String deleteProf = "update professor set prof_delete_flag = 'Y' where prof_number = ?";
			pstmt = con.prepareStatement(deleteProf);

			pstmt.setInt(1, prof_number);

			pstmt.executeUpdate();
		} finally {
			dbConnection.dbClose(null, pstmt, con);
		} // end finally
	} // deleteProf

	/**
	 * 관리자모드 > 교수 관리에서 교수 정보 수정을 위한 method
	 * @param pVO
	 * @throws SQLException
	 */
	public void updateProf(ProfVO pVO) throws SQLException {
		DbConnection dbConnection = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = dbConnection.getConnection();

			String modifyProf = "update professor set prof_name = ?, prof_password = ?, prof_email = ?, "
					+ "dept_code = (select dept_code from dept where dept_name = ?) where prof_number = ?";
			pstmt = con.prepareStatement(modifyProf);

			pstmt.setString(1, pVO.getProf_name());
			pstmt.setString(2, pVO.getProf_password());
			pstmt.setString(3, pVO.getProf_email());
			pstmt.setString(4, pVO.getDept_name());
			pstmt.setInt(5, pVO.getProf_number());

			pstmt.executeUpdate();
		} finally {
			dbConnection.dbClose(null, pstmt, con);
		} // end finally
	} // modifyProf

	/**
	 * 교수 > 교수가 속한 학과의 과목을 조회하기 위한 메서드
	 * @param prof_number
	 * @throws SQLException
	 */
	public List<CrsVO> slctProfCrs(int prof_number) throws SQLException {
		DbConnection dbConnection = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CrsVO cVO = null;
		List<CrsVO> courses = null;
		
		try {
			con = dbConnection.getConnection();

			StringBuilder sb = new StringBuilder();
			sb.append("SELECT d.dept_name, d.dept_code, c.course_name, c.course_code ");
			sb.append("FROM professor p ");
			sb.append("JOIN dept d ON p.dept_code = d.dept_code ");
			sb.append("JOIN course c ON d.dept_code = c.dept_code ");
			sb.append("WHERE p.prof_number = ? and course_delete_flag = 'N'");
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
	}// slctProfCrs

	/**
	 * 교수 > 교수가 속한 학과의 과목중 lecture테이블에 존재하지않는 과목을 조회하기 위한 메서드
	 * @param prof_number
	 * @throws SQLException
	 */
	public List<CrsVO> slctNotLectCrs(int prof_number) throws SQLException {
		DbConnection dbConnection = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CrsVO cVO = null;
		List<CrsVO> courses = null;
		
		try {
			con = dbConnection.getConnection();

			String slctCrsQuery = ("	select dept_name,dept_code,course_name,course_code	")
					+ ("	from(SELECT d.dept_name, d.dept_code, c.course_name, c.course_code	")
					+ ("	FROM professor p	") + ("	JOIN dept d ON p.dept_code = d.dept_code	")
					+ ("	JOIN course c ON d.dept_code = c.dept_code	")
					+ ("	WHERE p.prof_number = ? and c.course_delete_flag = 'N' and d.dept_delete_flag = 'N')	")
					+ ("	where course_code not in(select course_code from Lecture)	");

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
	}// slctProfCrs

	/**
	 * 관리자모드에서 학과,교번으로 교수를 검색하는 DAO
	 * @param dept_code //학번코드
	 * @param prof_num  //학번
	 * @return 선택된 학과,교번,교수명을 가진 ProfVO를 가지고있는 리스트
	 * @throws SQLException
	 */
	public List<ProfVO> slctProf(int dept_code, int prof_num) throws SQLException {
		List<ProfVO> listProfVO = new ArrayList<ProfVO>();
		ProfVO pVO = null;
		DbConnection dbConnection = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = dbConnection.getConnection();

			// 1 전체 비어있음
			if (dept_code == 0 && prof_num == 0) {

				String selectProf = "SELECT d.dept_name, p.PROF_NUMBER, p.PROF_NAME	"
						+ "						FROM PROFESSOR p	"
						+ "						JOIN dept d ON p.dept_code = d.dept_code	"
						+ "						where p.PROF_DELETE_FLAG = 'N'	"
						+ "						order by dept_name	";

				pstmt = con.prepareStatement(selectProf);
			} // end if

			// 2 전체 비어X
			else if (dept_code == 0 && prof_num != 0) {

				String selectProf = "SELECT d.dept_name, p.PROF_NUMBER, p.PROF_NAME	"
						+ "						FROM PROFESSOR p	"
						+ "						JOIN dept d ON p.dept_code = d.dept_code	"
						+ "						WHERE p.prof_number = ?	"
						+ "						AND p.PROF_DELETE_FLAG = 'N'	"
						+ "						order by dept_name	";

				pstmt = con.prepareStatement(selectProf);
				pstmt.setInt(1, prof_num);
			} // end if

			// 3 일부 비어있음
			else if (dept_code != 0 && prof_num == 0) {

				String selectProf = "SELECT d.dept_name, p.PROF_NUMBER, p.PROF_NAME	"
						+ "						FROM PROFESSOR p	"
						+ "						JOIN dept d ON p.dept_code = d.dept_code	"
						+ "						WHERE d.dept_code = ?	"
						+ "						AND p.PROF_DELETE_FLAG = 'N'	"
						+ "						order by dept_name	";

				pstmt = con.prepareStatement(selectProf);
				pstmt.setInt(1, dept_code);
			} // end if

			// 4 일부 비어X

			else if (dept_code != 0 && prof_num != 0) {

				String selectProf = "SELECT d.dept_name, p.PROF_NUMBER, p.PROF_NAME	"
						+ "						FROM PROFESSOR p	"
						+ "						JOIN dept d ON p.dept_code = d.dept_code	"
						+ "						WHERE d.dept_code = ?	"
						+ "						AND p.prof_number = ?	"
						+ "						AND p.PROF_DELETE_FLAG = 'N'	"
						+ "						order by dept_name	";

				pstmt = con.prepareStatement(selectProf);
				pstmt.setInt(1, dept_code);
				pstmt.setInt(2, prof_num);
			} // end if

			rs = pstmt.executeQuery();

			while (rs.next()) {
				pVO = new ProfVO(rs.getInt("prof_number"), rs.getString("prof_name"), rs.getString("dept_name"));

				listProfVO.add(pVO);
			} // end while
		} finally {
			dbConnection.dbClose(rs, pstmt, con);
		} // end finally

		return listProfVO;
	} // slctDeptProf

	/**
	 * 교수모드 > 교수 메인 정보호출을 위한 method
	 * @param prof_number
	 * @return
	 * @throws SQLException
	 */
	public ProfVO slctOneProf(int prof_number) throws SQLException {
		ProfVO pVO = null;
		DbConnection dbConnection = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = dbConnection.getConnection();

			String selectProf = "select p.PROF_NUMBER, p.PROF_PASSWORD, p.PROF_NAME, p.PROF_EMAIL, d.dept_name "
					+ "	from PROFESSOR p " + "	join dept d on p.dept_code = d.dept_code "
					+ "	WHERE p.prof_number = ?";

			pstmt = con.prepareStatement(selectProf);
			pstmt.setInt(1, prof_number);
			rs = pstmt.executeQuery();
			if (rs.next()) {

				pVO = new ProfVO(rs.getInt("PROF_NUMBER"), rs.getString("PROF_PASSWORD"), rs.getString("PROF_NAME"),
						rs.getString("PROF_EMAIL"), rs.getString("DEPT_NAME"));
			} // end while
		} finally {
			dbConnection.dbClose(rs, pstmt, con);
		} // end finally

		return pVO;
	} // slctOneProf

	/**
	 * 교수모드 > 교수 정보 수정을 위한 method
	 * @param pVO
	 * @throws SQLException
	 */
	public void modifyProf(ProfVO pVO) throws SQLException {
		DbConnection dbConnection = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = dbConnection.getConnection();

			String modifyProf = "update PROFESSOR set PROF_PASSWORD = ?, PROF_EMAIL = ? where PROF_NUMBER = ?";

			pstmt = con.prepareStatement(modifyProf);
			pstmt.setString(1, pVO.getProf_password());
			pstmt.setString(2, pVO.getProf_email());
			pstmt.setInt(3, pVO.getProf_number());

			pstmt.executeUpdate();
		} finally {
			dbConnection.dbClose(null, pstmt, con);
		} // end finally
	} // modifyStdnt

	/**
	 * 교수 > 해당 교수의 강의 과목을 조회하기 위한 메서드
	 * @param prof_number
	 * @throws SQLException
	 */
	public List<CrsVO> slctProfLectList(int prof_number) throws SQLException {
		DbConnection dbConnection = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CrsVO cVO = null;
		List<CrsVO> courses = null;
		
		try {
			con = dbConnection.getConnection();

			StringBuilder sb = new StringBuilder();
			sb.append("	SELECT c.COURSE_CODE, c.COURSE_NAME ");
			sb.append("	FROM LECTURE l	");
			sb.append("	JOIN COURSE c on l.COURSE_CODE = c.COURSE_CODE ");
			sb.append("	JOIN dept d on d.dept_code = c.dept_code ");
			sb.append("	where  c.COURSE_DELETE_FLAG ='N' and d.dept_delete_flag = 'N'	");
			sb.append("	AND l.PROF_NUMBER = ?");
			String slctLecQuery = sb.toString();
			pstmt = con.prepareStatement(slctLecQuery);
			pstmt.setInt(1, prof_number);
			rs = pstmt.executeQuery();
			String deptCode = "";
			String courseName = "";
			courses = new ArrayList<CrsVO>();
			while (rs.next()) {
				deptCode = rs.getString("COURSE_CODE");
				courseName = rs.getString("course_name");
				cVO = new CrsVO(deptCode, courseName);
				courses.add(cVO);
			} // end while
		} finally {
			dbConnection.dbClose(null, pstmt, con);
		} // end finally
		return courses;
	}// slctProfLect

	/**
	 * 교수 > 과목코드 ,학번선택시 그에따른 확과, 과목 , 학번, 이름 을 가진 리스트 반환
	 * @param prof_number
	 * @throws SQLException
	 */
	public List<ProfLectStudVO> slctProfStud(int prof_number, String crs_code, int std_number) throws SQLException {
		DbConnection dbConnection = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProfLectStudVO plsVO = null;
		List<ProfLectStudVO> lSlctProfStud = null;
		
		try {
			con = dbConnection.getConnection();

			// 1 전체 비어있음
			if (crs_code.isEmpty() && std_number == 0) {

				String selectProf = "SElECT  d.DEPT_NAME , c.COURSE_NAME   , s.STD_NUMBER ,s.STD_NAME	"
						+ "	FROM REGISTER r	" + "	JOIN COURSE c on r.COURSE_CODE = c.COURSE_CODE	"
						+ "	JOIN STUDENT s on r.STD_NUMBER  = s.STD_NUMBER	"
						+ "	JOIN PROFESSOR p on r.PROF_NUMBER = p.PROF_NUMBER	"
						+ "	JOIN DEPT d on c.DEPT_CODE = d.DEPT_CODE	" + "	WHERE  r.PROF_NUMBER = ?	"
						+ "	AND  s.STD_DELETE_FLAG = 'N' " + "	AND  d.DEPT_DELETE_FLAG ='N' "
						+ "	AND  c.COURSE_DELETE_FLAG ='N' ";

				pstmt = con.prepareStatement(selectProf);
				pstmt.setInt(1, prof_number);
			} // end if

			// 2 전체 비어X
			else if (crs_code.isEmpty() && std_number != 0) {

				String selectProf = "SElECT  d.DEPT_NAME , c.COURSE_NAME   , s.STD_NUMBER ,s.STD_NAME	"
						+ "	FROM REGISTER r	" + "	JOIN COURSE c on r.COURSE_CODE = c.COURSE_CODE	"
						+ "	JOIN STUDENT s on r.STD_NUMBER  = s.STD_NUMBER	"
						+ "	JOIN PROFESSOR p on r.PROF_NUMBER = p.PROF_NUMBER	"
						+ "	JOIN DEPT d on c.DEPT_CODE = d.DEPT_CODE	" + "	WHERE  r.PROF_NUMBER = ?	"
						+ "	AND s.STD_NUMBER = ?" + "	AND s.STD_DELETE_FLAG = 'N' "
						+ "	AND d.DEPT_DELETE_FLAG ='N' " + "	AND c.COURSE_DELETE_FLAG ='N' ";

				pstmt = con.prepareStatement(selectProf);
				pstmt.setInt(1, prof_number);
				pstmt.setInt(2, std_number);
			} // end if

			// 3 일부 비어있음
			else if (!crs_code.isEmpty() && std_number == 0) {

				String selectProf = "SElECT  d.DEPT_NAME , c.COURSE_NAME  , s.STD_NUMBER ,s.STD_NAME	"
						+ "	FROM REGISTER r	" + "	JOIN COURSE c on r.COURSE_CODE = c.COURSE_CODE	"
						+ "	JOIN STUDENT s on r.STD_NUMBER  = s.STD_NUMBER	"
						+ "	JOIN PROFESSOR p on r.PROF_NUMBER = p.PROF_NUMBER	"
						+ "	JOIN DEPT d on c.DEPT_CODE = d.DEPT_CODE	" + "	WHERE  r.PROF_NUMBER = ?		"
						+ "	AND r.COURSE_CODE = ?	 " + "	AND s.STD_DELETE_FLAG = 'N' "
						+ "	AND d.DEPT_DELETE_FLAG ='N' " + "	AND c.COURSE_DELETE_FLAG ='N' ";

				pstmt = con.prepareStatement(selectProf);
				pstmt.setInt(1, prof_number);
				pstmt.setString(2, crs_code);
			} // end if

			// 4 일부 비어X

			else if (!crs_code.isEmpty() && std_number != 0) {

				String selectProf = "SElECT  d.DEPT_NAME , c.COURSE_NAME  , s.STD_NUMBER ,s.STD_NAME	"
						+ "	FROM REGISTER r	" + "	JOIN COURSE c on r.COURSE_CODE = c.COURSE_CODE	"
						+ "	JOIN STUDENT s on r.STD_NUMBER  = s.STD_NUMBER	"
						+ "	JOIN PROFESSOR p on r.PROF_NUMBER = p.PROF_NUMBER	"
						+ "	JOIN DEPT d on c.DEPT_CODE = d.DEPT_CODE	" + "	WHERE  r.PROF_NUMBER = ?		"
						+ "	AND r.COURSE_CODE = ?	 " + "	AND s.STD_NUMBER = ?	"
						+ "	AND s.STD_DELETE_FLAG = 'N' " + "	AND d.DEPT_DELETE_FLAG ='N' "
						+ "	AND c.COURSE_DELETE_FLAG ='N' ";

				pstmt = con.prepareStatement(selectProf);
				pstmt.setInt(1, prof_number);
				pstmt.setString(2, crs_code);
				pstmt.setInt(3, std_number);
			} // end if

			rs = pstmt.executeQuery();

			lSlctProfStud = new ArrayList<ProfLectStudVO>();
			while (rs.next()) {

				plsVO = new ProfLectStudVO(rs.getString("DEPT_NAME"), rs.getString("COURSE_NAME"),
						rs.getInt("STD_NUMBER"), rs.getString("STD_NAME"));

				lSlctProfStud.add(plsVO);
			} // end while
		} finally {
			dbConnection.dbClose(null, pstmt, con);
		} // end finally
		return lSlctProfStud;
	}// slctProfLect

	/**
	 * 로그인한 교수의 강의 과목을 불러오는 메서드
	 * @param prof_number
	 * @return
	 * @throws SQLException
	 */
	public List<TestListVO> slctAllTest(int prof_number) throws SQLException {
		DbConnection dbConnection = DbConnection.getInstance();
		TestListVO tlVO = null;
		List<TestListVO> testList = new ArrayList<TestListVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = dbConnection.getConnection();

			StringBuilder slctQuery = new StringBuilder();
			slctQuery.append("SELECT c.course_name, l.test_flag,c.course_code ");
			slctQuery.append("FROM lecture l ");
			slctQuery.append("JOIN course c ON c.course_code = l.course_code ");
			slctQuery.append("JOIN dept d ON d.dept_code = c.dept_code ");
			slctQuery.append(
					"WHERE l.prof_number = ? and l.lect_delete_flag = 'N' and c.course_delete_flag = 'N' and d.dept_delete_flag = 'N'");

			String slctTestQuery = slctQuery.toString();
			pstmt = con.prepareStatement(slctTestQuery);
			pstmt.setInt(1, prof_number);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				tlVO = new TestListVO(rs.getString("course_name"), rs.getString("test_flag"),
						rs.getString("course_code"));
				testList.add(tlVO);
			} // end while
		} finally {
			dbConnection.dbClose(null, pstmt, con);
		} // end finally
		return testList;
	}// slctAllTest

	/**
	 * 시험 활성화 여부를 업데이트하는 메서드
	 * @throws SQLException
	 */
	public void updateTestFlag(String course_name, String clickBtn) throws SQLException {
		DbConnection dbConnection = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = dbConnection.getConnection();

			StringBuilder updateQuery = new StringBuilder();
			updateQuery.append("UPDATE lecture l ");
			updateQuery.append("SET l.test_flag = ? ");
			updateQuery.append(
					"WHERE EXISTS (SELECT 1 FROM course c WHERE l.course_code = c.course_code AND c.course_name = ?)");
			String updateTestQuery = updateQuery.toString();
			pstmt = con.prepareStatement(updateTestQuery);
			pstmt.setString(1, clickBtn);
			pstmt.setString(2, course_name);
			pstmt.executeUpdate();
		} finally {
			dbConnection.dbClose(null, pstmt, con);
		} // end finally
	}// updateTestFlag

	/**
	 * 시험출제여부를 판단하는 메서드
	 * @throws SQLException
	 */
	public String selectExaming(String course_code) throws SQLException {
		DbConnection dbConnection = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String examStatus = "";
		
		try {
			con = dbConnection.getConnection();

			String slctQuery = "SELECT COUNT(*) AS count FROM test_question WHERE course_code = ?";
			pstmt = con.prepareStatement(slctQuery);
			pstmt.setString(1, course_code);
			rs = pstmt.executeQuery();
			int examCount = 0;
			while (rs.next()) {
				examCount = rs.getInt("count");
			} // end while
			if (examCount > 0 && examCount < 10) {
				examStatus = "출제중";
			} // end if
			if (examCount == 0) {
				examStatus = "출제전";
			} // end if
			if (examCount == 10) {
				examStatus = "출제완료";
			} // end if
		} finally {
			dbConnection.dbClose(rs, pstmt, con);
		} // end finally
		return examStatus;
	}// selectExaming

	/**
	 * 이메일 중복확인기능
	 * @throws SQLException
	 */
	public int selectAllByEmail(String email, int prof_number) throws SQLException {
		DbConnection dbConnection = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int isValidEmail = 0;

		try {
			con = dbConnection.getConnection();

			String slctQuery = "SELECT prof_number, prof_email FROM professor WHERE prof_email = ?";
			pstmt = con.prepareStatement(slctQuery);
			pstmt.setString(1, email);

			rs = pstmt.executeQuery();
			int emailNumber = 0;
			while (rs.next()) {
				emailNumber = rs.getInt("prof_number");

				if (emailNumber == prof_number) {
					isValidEmail = 1;
					return isValidEmail;
				} // end if
			} // end while
			
			if (emailNumber == 0)
				isValidEmail = 1; // 바뀐게 없을 경우
			if (emailNumber != 0)
				isValidEmail = -1;// 중복
		} finally {
			dbConnection.dbClose(rs, pstmt, con);
		} // end finally
		return isValidEmail;
	} // selectAllByEmail

	public int selectAllByEmail(String email) throws SQLException {
		DbConnection dbConnection = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = dbConnection.getConnection();

			String slctQuery = "SELECT prof_number, prof_email FROM professor WHERE prof_email = ?";
			pstmt = con.prepareStatement(slctQuery);
			pstmt.setString(1, email);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				return -1;
			} // end if

		} finally {
			dbConnection.dbClose(rs, pstmt, con);
		} // end finally
		return 1;
	} // selectAllByEmail

	/**
	 * @param 과목코드
	 * @return 해당 과목을 수강중인 학생이 존재하는지 확인하는 method
	 * @throws SQLException
	 */
	public boolean checkDeleteable(String courCode) throws SQLException {
		DbConnection dbConnection = DbConnection.getInstance();
		boolean result = false;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = dbConnection.getConnection();

			String slctQuery = "select count(*)	count" + "	from REGISTER r	"
					+ "	JOIN LECTURE l on l.COURSE_CODE = r.COURSE_CODE	" + "	WHERE r.COURSE_CODE = ?	";
			pstmt = con.prepareStatement(slctQuery);
			pstmt.setString(1, courCode);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				// 해당 과목코드를 수강하는 학생이 0명일 경우에만 삭제가능 flag = true
				System.out.println(rs.getInt("count"));
				if (rs.getInt("count") == 0) {
					result = true;
				} // end if
			} // end if
		} finally {
			dbConnection.dbClose(rs, pstmt, con);
		} // end finally
		return result;
	} // checkDeleteable
	
	/**
	 * 교수 등록을 위해 DB의 가장 마지막 교번을 가져오는 method
	 * @return
	 * @throws SQLException
	 */
	public int selectMaxProfNumber() throws SQLException {
		int maxProfNumber = 0;
		DbConnection dbConnection = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = dbConnection.getConnection();

			String selectMaxProfNumber = "select max(prof_number) as maxProfNumber from professor";
			pstmt = con.prepareStatement(selectMaxProfNumber);

			rs = pstmt.executeQuery();

			if(rs.next()) {
				maxProfNumber = rs.getInt("maxProfNumber");
			} // end if

		} finally {
			dbConnection.dbClose(rs, pstmt, con);
		} // end finally
		return maxProfNumber;
	} // selectMaxProfNumber
} // class
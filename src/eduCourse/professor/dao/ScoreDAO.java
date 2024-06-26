package eduCourse.professor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eduCourse.VO.ScoreVO;
import eduCourse.resources.DbConnection;

public class ScoreDAO {
	private static ScoreDAO sDAO;

	public ScoreDAO() {
	}

	public static ScoreDAO getInstance() {
		if (sDAO == null) {
			sDAO = new ScoreDAO();
		} // end if
		return sDAO;
	} // getInstance

	/**
	 * 교수 로그인 정보를 가져오기 위한 DAO
	 * @param lVO
	 * @return
	 * @throws SQLException
	 */
	public List<ScoreVO> slctAllScore(int prof_id) throws SQLException {
		DbConnection dbConnection = DbConnection.getInstance();
		ScoreVO sVO = null;
		List<ScoreVO> list = new ArrayList<ScoreVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = dbConnection.getConnection();

			String selectQuery = "SELECT s.STD_NUMBER, d.DEPT_NAME, s.STD_NAME, sc.SCORE, c.course_name "
					+ "FROM STUDENT s " + "JOIN DEPT d ON s.DEPT_CODE = d.DEPT_CODE "
					+ "JOIN REGISTER r ON s.STD_NUMBER = r.STD_NUMBER "
					+ "JOIN SCORE sc ON r.REGISTER_NUMBER = sc.REGISTER_NUMBER "
					+ "JOIN course c ON r.course_code = c.course_code " + "Where r.prof_number = ?";
			pstmt = con.prepareStatement(selectQuery);
			pstmt.setInt(1, prof_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				sVO = new ScoreVO(rs.getInt("STD_NUMBER"), rs.getString("STD_NAME"), rs.getString("DEPT_NAME"),
						rs.getInt("SCORE"), rs.getString("course_name"));

				list.add(sVO);
			} // end while
		} finally {
			dbConnection.dbClose(null, pstmt, con);
		} // end finally

		return list;
	}// slctAllScore

	public List<ScoreVO> slctOneScore(int stdnt_num, int prof_id) throws SQLException {// 학번으로 검색
		DbConnection dbConnection = DbConnection.getInstance();
		ScoreVO sVO = null;
		List<ScoreVO> list = new ArrayList<ScoreVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = dbConnection.getConnection();

			String selectQuery = "SELECT s.std_number, d.dept_name, s.std_name, sc.score, c.course_name "
					+ "FROM student s " + "JOIN dept d ON s.dept_code = d.dept_code "
					+ "JOIN register r ON s.std_number = r.std_number "
					+ "JOIN score sc ON r.register_number = sc.register_number "
					+ "JOIN course c ON r.course_code = c.course_code "
					+ "WHERE s.std_number = ? and r.PROF_NUMBER = ?";

			pstmt = con.prepareStatement(selectQuery);
			pstmt.setInt(1, stdnt_num);
			pstmt.setInt(2, prof_id);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				sVO = new ScoreVO(rs.getInt("STD_NUMBER"), rs.getString("STD_NAME"), rs.getString("DEPT_NAME"),
						rs.getInt("SCORE"), rs.getString("course_name"));

				list.add(sVO);
			} // end while
		} finally {
			dbConnection.dbClose(null, pstmt, con);
		} // end finally

		return list;
	}// slctOneScore 학번

	public List<ScoreVO> slctOneScore(String crs_code, int prof_id) throws SQLException {// 과목코드로 찾기
		DbConnection dbConnection = DbConnection.getInstance();
		ScoreVO sVO = null;
		List<ScoreVO> list = new ArrayList<ScoreVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = dbConnection.getConnection();

			String selectQuery = "SELECT s.std_number, d.dept_name, s.std_name, sc.score, c.course_name "
					+ "FROM student s " + "JOIN dept d ON s.dept_code = d.dept_code "
					+ "JOIN register r ON s.std_number = r.std_number "
					+ "JOIN score sc ON r.register_number = sc.register_number "
					+ "JOIN course c ON r.course_code = c.course_code "
					+ "WHERE c.course_code = ? and r.PROF_NUMBER = ?";

			pstmt = con.prepareStatement(selectQuery);
			pstmt.setString(1, crs_code);
			pstmt.setInt(2, prof_id);
			// 5. 쿼리 실행 및 결과 처리
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sVO = new ScoreVO(rs.getInt("STD_NUMBER"), rs.getString("STD_NAME"), rs.getString("DEPT_NAME"),
						rs.getInt("SCORE"), rs.getString("course_name"));

				list.add(sVO);
			} // end while
		} finally {
			dbConnection.dbClose(null, pstmt, con);
		} // end finally

		return list;
	}// slctOneScore

	public List<ScoreVO> slctOneScore(int stdnt_num, String crs_code, int prof_id) throws SQLException {// 학번, 과목코드로 검색
		DbConnection dbConnection = DbConnection.getInstance();
		ScoreVO sVO = null;
		List<ScoreVO> list = new ArrayList<ScoreVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = dbConnection.getConnection();

			String selectQuery = "	SELECT s.std_number, d.dept_name, s.std_name, sc.score, c.course_name	 "
					+ "		FROM student s 	" + "		JOIN dept d ON s.dept_code = d.dept_code 	"
					+ "		JOIN register r ON s.std_number = r.std_number	 "
					+ "		JOIN score sc ON r.register_number = sc.register_number 	"
					+ "		JOIN course c ON r.course_code = c.course_code	 "
					+ "		WHERE c.course_code = ? and s.std_number = ? and r.PROF_NUMBER = ?	";

			pstmt = con.prepareStatement(selectQuery);
			pstmt.setString(1, crs_code);
			pstmt.setInt(2, stdnt_num);
			pstmt.setInt(3, prof_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				sVO = new ScoreVO(rs.getInt("STD_NUMBER"), rs.getString("STD_NAME"), rs.getString("DEPT_NAME"),
						rs.getInt("SCORE"), rs.getString("course_name"));

				list.add(sVO);
			} // end while
		} finally {
			dbConnection.dbClose(null, pstmt, con);
		} // end finally

		return list;
	}// slctOneScore
} // class
package eduCourse_prj.professor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eduCourse_prj.DbConnection;
import eduCourse_prj.VO.ScoreVO;
import eduCourse_prj.VO.DeptVO;
import eduCourse_prj.VO.LoginVO;

public class ScoreDAO {
	private static ScoreDAO sDAO;
	public ScoreDAO() {
	}
	
	public static ScoreDAO getInstance() {
		if (sDAO == null) {
			sDAO = new ScoreDAO();
		} // end if
		return sDAO;
	}
	
	/**
	 * 교수 로그인 정보를 가져오기 위한 DAO
	 * @param lVO
	 * @return
	 * @throws SQLException
	 */
	public List<ScoreVO> slctAllScore() throws SQLException {

		DbConnection dbCon = DbConnection.getInstance();
		ScoreVO sVO = null;
		List<ScoreVO> list = new ArrayList<ScoreVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			String id = "scott";
			String pass = "tiger";
			con = dbCon.getConnection(id, pass);

			String selectQuery = "SELECT s.STD_NUMBER, d.DEPT_NAME, s.STD_NAME, sc.SCORE "
					+ "FROM STUDENT s "
					+ "JOIN DEPT d ON s.DEPT_CODE = d.DEPT_CODE "
					+ "JOIN REGISTER r ON s.STD_NUMBER = r.STD_NUMBER "
					+ "JOIN SCORE sc ON r.REGISTER_NUMBER = sc.REGISTER_NUMBER";
			pstmt = con.prepareStatement(selectQuery);

			// 5. 쿼리 실행 및 결과 처리
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sVO = new ScoreVO(rs.getInt("STD_NUMBER"), rs.getString("STD_NAME"), rs.getString("DEPT_NAME"),  rs.getInt("SCORE"));


				list.add(sVO);

			}

		} finally {

			dbCon.dbClose(null, pstmt, con);
		} // end finally

		return list;

	}// slctAllScore
	
	public List<ScoreVO> slctOneScore(int stdnt_num) throws SQLException {//학번으로 검색

		DbConnection dbCon = DbConnection.getInstance();
		ScoreVO sVO = null;
		List<ScoreVO> list = new ArrayList<ScoreVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			String id = "scott";
			String pass = "tiger";
			con = dbCon.getConnection(id, pass);

			String selectQuery = "SELECT s.std_number, d.dept_name, s.std_name, sc.score "
					+ "FROM student s "
					+ "JOIN dept d ON s.dept_code = d.dept_code "
					+ "JOIN register r ON s.std_number = r.std_number "
					+ "JOIN score sc ON r.register_number = sc.register_number "
					+ "WHERE s.std_number = ?";

			pstmt = con.prepareStatement(selectQuery);
			pstmt.setInt(1, stdnt_num);

			// 5. 쿼리 실행 및 결과 처리
			rs = pstmt.executeQuery();
			while (rs.next()) {
				sVO = new ScoreVO(rs.getInt("STD_NUMBER"), rs.getString("STD_NAME"), rs.getString("DEPT_NAME"),  rs.getInt("SCORE"));


				list.add(sVO);

			}

		} finally {

			dbCon.dbClose(null, pstmt, con);
		} // end finally

		return list;

	}// slctOneScore 학번
	
	public List<ScoreVO> slctOneScore(String crs_code) throws SQLException {//과목코드로 찾기

		DbConnection dbCon = DbConnection.getInstance();
		ScoreVO sVO = null;
		List<ScoreVO> list = new ArrayList<ScoreVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			String id = "scott";
			String pass = "tiger";
			con = dbCon.getConnection(id, pass);

			String selectQuery = "SELECT s.std_number, d.dept_name, s.std_name, sc.score "
					+ "FROM student s "
					+ "JOIN dept d ON s.dept_code = d.dept_code "
					+ "JOIN register r ON s.std_number = r.std_number "
					+ "JOIN score sc ON r.register_number = sc.register_number "
					+ "JOIN course c ON r.course_code = c.course_code "
					+ "WHERE c.course_code = ?";

			pstmt = con.prepareStatement(selectQuery);
			pstmt.setString(1, crs_code);

			// 5. 쿼리 실행 및 결과 처리
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sVO = new ScoreVO(rs.getInt("STD_NUMBER"), rs.getString("STD_NAME"), rs.getString("DEPT_NAME"),  rs.getInt("SCORE"));


				list.add(sVO);

			}

		} finally {

			dbCon.dbClose(null, pstmt, con);
		} // end finally

		return list;

	}// slctOneScore
	
	public List<ScoreVO> slctOneScore(int stdnt_num, String crs_code) throws SQLException {//학번, 과목코드로 검색

		System.out.println("sydnt_num: " +stdnt_num);
		System.out.println("crs_code: " +crs_code);
		DbConnection dbCon = DbConnection.getInstance();
		ScoreVO sVO = null;
		List<ScoreVO> list = new ArrayList<ScoreVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			String id = "scott";
			String pass = "tiger";
			con = dbCon.getConnection(id, pass);

			String selectQuery = "	SELECT s.std_number, d.dept_name, s.std_name, sc.score	 "
					+ "		FROM student s 	"
					+ "		JOIN dept d ON s.dept_code = d.dept_code 	"
					+ "		JOIN register r ON s.std_number = r.std_number	 "
					+ "		JOIN score sc ON r.register_number = sc.register_number 	"
					+ "		JOIN course c ON r.course_code = c.course_code	 "
					+ "		WHERE c.course_code = ? and s.std_number = ?	";

			pstmt = con.prepareStatement(selectQuery);
			pstmt.setString(1, crs_code);
			pstmt.setInt(2, stdnt_num);

			// 5. 쿼리 실행 및 결과 처리
			rs = pstmt.executeQuery();
			
			
			while (rs.next()) {

				sVO = new ScoreVO(rs.getInt("STD_NUMBER"), rs.getString("STD_NAME"), rs.getString("DEPT_NAME"),  rs.getInt("SCORE"));



				list.add(sVO);

			}

		} finally {

			dbCon.dbClose(null, pstmt, con);
		} // end finally

		return list;

	}// slctOneScore
	
	
}

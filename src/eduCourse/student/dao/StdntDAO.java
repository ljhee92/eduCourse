package eduCourse.student.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import eduCourse.VO.LoginVO;
import eduCourse.VO.StdntVO;
import eduCourse.resources.DbConnection;

public class StdntDAO {
	private static StdntDAO sDAO;

	private StdntDAO() {
	}

	public static StdntDAO getInstance() {
		if (sDAO == null) {
			sDAO = new StdntDAO();
		} // end if
		return sDAO;
	} // getInstance

	/**
	 * 학생 로그인 정보를 가져오기 위한 DAO
	 * @param lVO
	 * @return
	 * @throws SQLException
	 */
	public LoginVO studentLogin(LoginVO lVO) throws SQLException {
		LoginVO lresultVO = null;
		DbConnection dbConnection = DbConnection.getInstance();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = dbConnection.getConnection();

			String selectQuery = "SELECT STD_NUMBER, STD_PASSWORD, STD_NAME FROM student WHERE STD_NUMBER = ? AND STD_PASSWORD = ?";
			pstmt = con.prepareStatement(selectQuery);

			pstmt.setString(1, lVO.getId());
			pstmt.setString(2, lVO.getPassword());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				lresultVO = new LoginVO(rs.getString("STD_NUMBER"), rs.getString("STD_PASSWORD"),
						rs.getString("STD_NAME"));
			} // end if
		} finally {
			dbConnection.dbClose(rs, pstmt, con);
		} // end finally

		return lresultVO;
	} // studentLogin

	/**
	 * 학생모드 > 학생 메인을 위한 method
	 * @param stdnt_number
	 * @return
	 * @throws SQLException
	 */
	public StdntVO slctOneStdnt(int stdnt_number) throws SQLException {
		StdntVO sVO = null;
		DbConnection dbConnection = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = dbConnection.getConnection();

			String selectProf = "select s.std_number, s.std_password, s.std_name, s.std_email, s.std_addr, d.dept_name "
					+ "from student s " + "join dept d on d.dept_code = s.dept_code " + "where s.std_number = ?";

			pstmt = con.prepareStatement(selectProf);
			pstmt.setInt(1, stdnt_number);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sVO = new StdntVO(stdnt_number, rs.getString("std_password"), rs.getString("std_name"),
						rs.getString("std_email"), rs.getString("std_addr"), null, null, rs.getString("dept_name"));
			} // end while
		} finally {
			dbConnection.dbClose(rs, pstmt, con);
		} // end finally

		return sVO;
	} // slctOneStdnt

	/**
	 * 학생모드 > 학생 정보 수정을 위한 method
	 * @param sVO
	 * @throws SQLException
	 */
	public void updateStdnt(StdntVO sVO) throws SQLException {
		DbConnection dbConnection = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			String id = "scott";
			String pass = "tiger";

			con = dbConnection.getConnection();

			String modifyStdnt = "update student set std_password = ?, std_email = ?, std_addr = ? where std_number = ?";

			pstmt = con.prepareStatement(modifyStdnt);
			pstmt.setString(1, sVO.getStdnt_password());
			pstmt.setString(2, sVO.getStdnt_email());
			pstmt.setString(3, sVO.getStdnt_addr());
			pstmt.setInt(4, sVO.getStdnt_number());

			pstmt.executeUpdate();
		} finally {
			dbConnection.dbClose(null, pstmt, con);
		} // end finally
	} // modifyStdnt

	public int selectAllByEmail(String email, int std_number) throws SQLException {
		DbConnection dbConnection = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int isValidEmail = 0;

		try {
			con = dbConnection.getConnection();

			String slctQuery = "SELECT std_email, std_number FROM student WHERE std_email = ?";
			pstmt = con.prepareStatement(slctQuery);
			pstmt.setString(1, email);

			rs = pstmt.executeQuery();
			int emailNumber = 0;
			while (rs.next()) {
				emailNumber = rs.getInt("std_number");
				if (emailNumber == std_number) {
					isValidEmail = 1;
					return isValidEmail;
				} // end if
			} // end while
			if (emailNumber == 0)
				isValidEmail = 1; // 바뀐게 없을 경우
			if (emailNumber != 0)
				isValidEmail = -1; // 중복
		} finally {
			dbConnection.dbClose(rs, pstmt, con);
		} // end finally
		return isValidEmail;
	} // selectAllByEmail
} // class
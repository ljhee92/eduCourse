package eduCourse_prj.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import eduCourse_prj.DbConnection;
import eduCourse_prj.VO.AdminAcadCalVO;

public class AdminAcadCalDAO {
	private static AdminAcadCalDAO aacDAO;

	private AdminAcadCalDAO() {

	}// AdminAcadCalDAO

	public static AdminAcadCalDAO getInstance() {
		if (aacDAO == null) {
			aacDAO = new AdminAcadCalDAO();
		}
		return aacDAO;
	}// getInstance

	/**
	 * 해당하는 날짜의 메모를 불러오는 메서드
	 * 
	 * @param yearMonthDay
	 * @return
	 * @throws SQLException
	 */
	public String selectOneCal(String yearMonthDay) throws SQLException {
		DbConnection dbCon = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String memo = "";
		try {

			String id = "scott";
			String pass = "tiger";

			con = dbCon.getConnection(id, pass);

			String selectMemoCal = "select schedule_memo from schedule where schedule_input_date=? ";
			pstmt = con.prepareStatement(selectMemoCal);

			pstmt.setString(1, yearMonthDay);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				memo = rs.getString("schedule_memo");
			}

		} finally {

			dbCon.dbClose(rs, pstmt, con);

		} // end finally
		return memo;
	}

	/**
	 * 메모를 저장하는 메서드
	 * 
	 * @param memo
	 * @param yearMonthDay
	 * @throws SQLException
	 */
	public void saveCal(String memo, String yearMonthDay) throws SQLException {
		DbConnection dbCon = DbConnection.getInstance();
		AdminAcadCalVO aacVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			aacVO = new AdminAcadCalVO(memo, yearMonthDay);
			String id = "scott";
			String pass = "tiger";

			con = dbCon.getConnection(id, pass);

			String saveCal = "insert into schedule(schedule_memo, schedule_input_date) values(?,?) ";
			pstmt = con.prepareStatement(saveCal);

			pstmt.setString(1, aacVO.getMemo());
			pstmt.setString(2, aacVO.getYearMonthDay());
			pstmt.executeUpdate();

		} finally {
			dbCon.dbClose(null, pstmt, con);
		} // end finally
	}

	/**
	 * 메모장을 업데이트하는 메서드
	 * 
	 * @param yearMonthDay
	 * @throws SQLException
	 */
	public void updateCal(String memo, String yearMonthDay) throws SQLException {
		DbConnection dbCon = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			String id = "scott";
			String pass = "tiger";

			con = dbCon.getConnection(id, pass);

			String saveCal = "	update schedule " + "	set	schedule_memo = ? " + "	where schedule_input_date = ? ";
			pstmt = con.prepareStatement(saveCal);
			pstmt.setString(1, memo);
			pstmt.setString(2, yearMonthDay);
			pstmt.executeUpdate();

		} finally {
			dbCon.dbClose(null, pstmt, con);
		} // end finally
	}

	/**
	 * 메모를 삭제하는 메서드
	 * 
	 * @param yearMonthDay
	 * @throws SQLException
	 */
	public void deleteCal(String yearMonthDay) throws SQLException {
		DbConnection dbCon = DbConnection.getInstance();
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			String id = "scott";
			String pass = "tiger";

			con = dbCon.getConnection(id, pass);

			String deleteSql = "delete from schedule where schedule_input_date = ?";
			pstmt = con.prepareStatement(deleteSql);
			pstmt.setString(1, yearMonthDay);
			pstmt.executeUpdate();
		} finally {
			dbCon.dbClose(null, pstmt, con);
		}
	}

}

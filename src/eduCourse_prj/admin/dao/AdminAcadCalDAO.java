package eduCourse_prj.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import eduCourse_prj.DbConnection;
import eduCourse_prj.VO.AdminAcadCalVO;

public class AdminAcadCalDAO {
	private static AdminAcadCalDAO aacDAO;
	
	private AdminAcadCalDAO() {
		
	}//AdminAcadCalDAO
	
	public static AdminAcadCalDAO getInstance() {
		if(aacDAO == null) {
			aacDAO =  new AdminAcadCalDAO();
		}
		return aacDAO;
	}//getInstance
	
	public String selectOneCal(String dayMonthYear) throws SQLException{
		DbConnection dbCon = DbConnection.getInstance();
		AdminAcadCalVO aacVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			aacVO = new AdminAcadCalVO();
			String id = "scott";
			String pass = "tiger";
			
			con = dbCon.getConnection(id, pass);
			
			String selectMemoCal = "select schedule_memo from schedule where schedule_input_date=(?)"
					+ " ";
			pstmt = con.prepareStatement(selectMemoCal);
			
			pstmt.setString(1, aacVO.getYearMonthDay());
			pstmt.executeUpdate();
			
			

		} finally {

			dbCon.dbClose(null, pstmt, con);

		} // end finally
		return dayMonthYear; ///////////////수정생각해야됨
	}
	
	public void saveCal(String memo,String dayMonthYear) throws SQLException {
		DbConnection dbCon = DbConnection.getInstance();
		AdminAcadCalVO aacVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			aacVO = new AdminAcadCalVO(memo, dayMonthYear);
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
	
	public void deleteCal(String dayMonthYear) {
		
	}
	
	
}

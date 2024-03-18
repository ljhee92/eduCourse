package eduCourse_prj.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import eduCourse_prj.DbConnection;
import eduCourse_prj.VO.LoginVO;
import eduCourse_prj.VO.StdntVO;



public class StdntDAO {
	private static StdntDAO slDAO;

	private StdntDAO() {

	}

	public static StdntDAO getInstance() {
		if (slDAO == null) {
			slDAO = new StdntDAO();

		} // end if

		return slDAO;
	}
	
	
	/**
	 * 학생 로그인 정보를 가져오기 위한 DAO
	 * @param lVO
	 * @return
	 * @throws SQLException
	 */
	public LoginVO studentLogin(LoginVO lVO) throws SQLException {
	    LoginVO lresultVO = null;
	    DbConnection dbcon = DbConnection.getInstance();
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        // 1. 데이터베이스 접속 정보
	        String id = "scott";
	        String pass = "tiger";

	        // 2. 데이터베이스 연결
	        con = dbcon.getConnection(id, pass);

	        // 3. SQL 쿼리 준비
	        String selectQuery = "SELECT STD_NUMBER, STD_PASSWORD, STD_NAME FROM student WHERE STD_NUMBER = ? AND STD_PASSWORD = ?";
	        pstmt = con.prepareStatement(selectQuery);

	        // 4. SQL 쿼리에 파라미터 설정

	        pstmt.setString(1, lVO.getId());
	        pstmt.setString(2, lVO.getPassword());

	        // 5. 쿼리 실행 및 결과 처리
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	        	
	            lresultVO = new LoginVO(rs.getString("STD_NUMBER"), rs.getString("STD_PASSWORD"),rs.getString("STD_NAME"));
	        }
	    } finally {
	        // 6. 리소스 해제
	        dbcon.dbClose(rs, pstmt, con);
	    }

	  
	    return lresultVO;
	}
	
	/**
	 * 학생모드 > 학생 메인을 위한 method
	 * @param stdnt_number
	 * @return
	 * @throws SQLException
	 */
	public StdntVO slctOneStdnt(int stdnt_number) throws SQLException {
		StdntVO sVO = null;
		DbConnection dbCon = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String id = "scott";
			String pass = "tiger";
			
			con = dbCon.getConnection(id, pass);
			
			String selectStdnt = "select s.std_number, s.std_name, s.std_email, s.std_addr, d.dept_name "
					+ "from student s "
					+ "join dept d on d.dept_code = s.dept_code "
					+ "where s.std_number = ?";
			
			pstmt = con.prepareStatement(selectStdnt);
			pstmt.setInt(1, stdnt_number);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				sVO = new StdntVO(stdnt_number, null, rs.getString("std_name"), rs.getString("std_email"), rs.getString("std_addr"), null, null, rs.getString("dept_name"));
			} // end while
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		} // end finally
		return sVO;
	} // slctOneStdnt
	
	/**
	 * 학생모드 > 학생 정보 수정을 위한 method
	 * @param sVO
	 * @throws SQLException
	 */
	public void modifyStdnt(StdntVO sVO) throws SQLException {
		DbConnection dbCon = DbConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			String id = "scott";
			String pass = "tiger";
			
			con = dbCon.getConnection(id, pass);
			
			String modifyStdnt = "update student ";
			pstmt = con.prepareStatement(modifyStdnt);
			
			
		} finally {
			dbCon.dbClose(null, pstmt, con);
		} // end finally
	} // modifyStdnt
	
} // class
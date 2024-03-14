package eduCourse_prj.professor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import eduCourse_prj.DbConnection;
import eduCourse_prj.VO.LoginVO;



public class ProfessorLoginDAO {
	private static ProfessorLoginDAO plDAO;

	private ProfessorLoginDAO() {

	}

	public static ProfessorLoginDAO getInstance() {
		if (plDAO == null) {
			plDAO = new ProfessorLoginDAO();

		} // end if

		return plDAO;
	}
	
	
	public LoginVO professorLogin(LoginVO lVO) throws SQLException {
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
	        String selectQuery = "SELECT PROF_NUMBER, PROF_PASSWORD ,PROF_NAME FROM professor WHERE  PROF_NUMBER = ? AND PROF_PASSWORD = ?";
	        pstmt = con.prepareStatement(selectQuery);

	        // 4. SQL 쿼리에 파라미터 설정

	        pstmt.setString(1, lVO.getId());
	        pstmt.setString(2, lVO.getPassword());

	        // 5. 쿼리 실행 및 결과 처리
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	        	
	            lresultVO = new LoginVO(rs.getString("PROF_NUMBER"), rs.getString("PROF_PASSWORD"),rs.getString("PROF_NAME"));
	        }
	    } finally {
	        // 6. 리소스 해제
	        dbcon.dbClose(rs, pstmt, con);
	    }

	  
	    return lresultVO;
	}
}
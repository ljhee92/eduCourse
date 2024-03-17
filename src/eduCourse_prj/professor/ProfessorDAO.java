package eduCourse_prj.professor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eduCourse_prj.DbConnection;
import eduCourse_prj.VO.LoginVO;
import eduCourse_prj.VO.ProfVO;
import oracle.jdbc.proxy.annotation.Pre;

public class ProfessorDAO {
	private static ProfessorDAO pDAO;

	private ProfessorDAO() {
	}

	public static ProfessorDAO getInstance() {
		if (pDAO == null) {
			pDAO = new ProfessorDAO();
		} // end if
		return pDAO;
	}
	
	/**
	 * 교수 로그인 정보를 가져오기 위한 DAO
	 * @param lVO
	 * @return
	 * @throws SQLException
	 */
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
	
	/**
	 * 관리자모드 교수관리의 교번, 이름을 가져오기 위한 DAO
	 * @return
	 * @throws SQLException
	 */
	public List<ProfVO> slctProfMgt() throws SQLException {
		List<ProfVO> listProfVO = new ArrayList<ProfVO>();
		ProfVO pVO = null;
		DbConnection dbCon = DbConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String id = "scott";
			String pass = "tiger";
			
			con = dbCon.getConnection(id, pass);
			
			String selectProfMgt = "select prof_number, prof_name from professor";
			pstmt = con.prepareStatement(selectProfMgt);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				pVO = new ProfVO(rs.getInt("prof_number"), rs.getString("prof_name"));
				listProfVO.add(pVO);
			} // end while
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		} // end finally
		
		return listProfVO;
	} // slctProfMgt
	
	public List<ProfVO> slctProfMgsSlct(int prof_number) throws SQLException{
		List<ProfVO> listProfVO = new ArrayList<ProfVO>();
		ProfVO pVO = null;
		DbConnection dbCon = DbConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String id = "scott";
			String pass = "tiger";
			
			con = dbCon.getConnection(id, pass);
			
			String selectProf = "select distinct p.prof_number, p.prof_name, p.prof_email, d.dept_name "
								+ "from professor p "
								+ "join lecture l on l.prof_number = p.prof_number "
								+ "join dept d on d.dept_code = p.dept_code "
								+ "where p.prof_number = ?";
			pstmt = con.prepareStatement(selectProf);
			pstmt.setInt(1, prof_number);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				pVO = new ProfVO(prof_number, rs.getString("prof_name"), rs.getString("prof_email"), rs.getString("dept_name"));
				listProfVO.add(pVO);
			} // end while
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		} // end finally
		
		return listProfVO;
	} // slctProfMgtSlct
} // class
package eduCourse_prj.professor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import eduCourse_prj.DbConnection;
import eduCourse_prj.VO.LoginVO;
import eduCourse_prj.VO.ProfVO;

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
			
			String selectProfMgt = "select prof_number, prof_name from professor order by prof_number";
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
	
	/**
	 * 관리자 모드 > 교수 관리 > 교수 상세 조회를 위한 method
	 * @param prof_number
	 * @return
	 * @throws SQLException
	 */
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
	
	/**
	 * 관리자모드 > 교수 등록 구현부 
	 * @param pVO
	 * @throws SQLException
	 */
	public void addProf(ProfVO pVO) throws SQLException {
		DbConnection dbCon = DbConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		
		try {
			String id = "scott";
			String pass = "tiger";
			con = dbCon.getConnection(id, pass);
			
			// 교수 번호 확인 후, max 교번+1로 추가
			int prof_num = 000000001;
			String checkProfNum = "select max(prof_number) from professor";
			pstmt = con.prepareStatement(checkProfNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int lastProfNum = rs.getInt(1);
				prof_num = lastProfNum +1;
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
			JOptionPane.showMessageDialog(null, "교수가 성공적으로 등록되었습니다.");
		} finally {
			dbCon.dbClose(rs, pstmt, con);
			dbCon.dbClose(rs, pstmt2, con);
		} // end finally
		
	} // addProf
} // class
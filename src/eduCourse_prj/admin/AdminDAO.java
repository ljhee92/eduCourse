package eduCourse_prj.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import eduCourse_prj.DbConnection;
import eduCourse_prj.VO.DeptVO;
import eduCourse_prj.VO.LoginVO;




public class AdminDAO {
	private static AdminDAO alDAO;

	private AdminDAO() {

	}

	public static AdminDAO getInstance() {
		if (alDAO == null) {
			alDAO = new AdminDAO();

		} // end if

		return alDAO;
	}
	
	
	/**
	 * 로그인 기능구현부
	 * @param lVO
	 * @return
	 * @throws SQLException
	 */
	public LoginVO adminLogin(LoginVO lVO) throws SQLException {
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
	        String selectQuery = "SELECT ADMIN_ID, ADMIN_PASSWORD, ADMIN_NAME FROM admin WHERE ADMIN_ID = ? AND ADMIN_PASSWORD = ?";
	        pstmt = con.prepareStatement(selectQuery);

	        // 4. SQL 쿼리에 파라미터 설정

	        pstmt.setString(1, lVO.getId());
	        pstmt.setString(2, lVO.getPassword());

	        // 5. 쿼리 실행 및 결과 처리
	        rs = pstmt.executeQuery();


	        if (rs.next()) {
	            lresultVO = new LoginVO(rs.getString("ADMIN_ID"), rs.getString("ADMIN_PASSWORD"),rs.getString("ADMIN_NAME"));
	        }
	    } finally {
	        // 6. 리소스 해제
	        dbcon.dbClose(rs, pstmt, con);
	    }


	    return lresultVO;
	}//AdminLogin
	
	/**
	 * 학과 등록 구현부
	 * @param dVO
	 * @throws SQLException
	 */
	@SuppressWarnings("resource")
	public void addDepartment(DeptVO dVO) throws SQLException{
		


		
		
		
		
	
		DbConnection dbCon = DbConnection.getInstance();
		

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			String id = "scott";
			String pass = "tiger";
			con = dbCon.getConnection(id, pass);
			
			
			//학과추가 순서
			//1. 우선 학과번호가 확인
			int dept_code = 0000;
			String checkDept="select 	max(dept_code) 		from	 dept";
			pstmt=con.prepareStatement(checkDept);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				//2 존재한다면 DEPT_CODE의 가장 마지막번호 + 1으로 설정
				int lastDeptCode = rs.getInt(1);
				dept_code = lastDeptCode+1;
			}
			//2.1 존재하지 않는다면 DEPT_CODE = 0001으로 설정

			String addDept = "insert into dept(DEPT_CODE,DEPT_NAME,DEPT_CAPACITY)values(?,?,?)";
			pstmt = con.prepareStatement(addDept);
			

			pstmt.setInt(1, dept_code);
			pstmt.setString(2, dVO.getDept_name());
			pstmt.setInt(3, dVO.getDept_capacity());


			pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "학과가 성공적으로 등록되엇습니다.");
			
		}finally {

			dbCon.dbClose(null, pstmt, con);
		}//end finally
	
	
	}//addDepartment
	
	
	
	
}
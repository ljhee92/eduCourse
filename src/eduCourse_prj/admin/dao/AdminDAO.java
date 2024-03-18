package eduCourse_prj.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import eduCourse_prj.DbConnection;
import eduCourse_prj.VO.AdminVO;
import eduCourse_prj.VO.DeptVO;
import eduCourse_prj.VO.LoginVO;
import eduCourse_prj.VO.ProfVO;

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

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 로그인 기능구현부
	 * 
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
				lresultVO = new LoginVO(rs.getString("ADMIN_ID"), rs.getString("ADMIN_PASSWORD"),
						rs.getString("ADMIN_NAME"));
			}
		} finally {
			// 6. 리소스 해제
			dbcon.dbClose(rs, pstmt, con);
		}

		return lresultVO;
	}// AdminLogin
////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 학과 등록 구현부
	 * 
	 * @param dVO
	 * @throws SQLException
	 */
	@SuppressWarnings("resource")
	public void addDepartment(DeptVO dVO) throws SQLException {

		DbConnection dbCon = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			String id = "scott";
			String pass = "tiger";
			con = dbCon.getConnection(id, pass);

			// 학과추가 순서
			// 1. 우선 학과번호가 확인
			int dept_code = 0000;
			String checkDept = "select 	max(dept_code) 		from	 dept";
			pstmt = con.prepareStatement(checkDept);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				// 2 존재한다면 DEPT_CODE의 가장 마지막번호 + 1으로 설정
				int lastDeptCode = rs.getInt(1);
				dept_code = lastDeptCode + 1;
			}
			// 2.1 존재하지 않는다면 DEPT_CODE = 0001으로 설정

			String addDept = "insert into dept(DEPT_CODE,DEPT_NAME,DEPT_CAPACITY)values(?,?,?)";
			pstmt = con.prepareStatement(addDept);

			pstmt.setInt(1, dept_code);
			pstmt.setString(2, dVO.getDept_name());
			pstmt.setInt(3, dVO.getDept_capacity());

			pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "학과가 성공적으로 등록되엇습니다.");

		} finally {

			dbCon.dbClose(null, pstmt, con);
		} // end finally

	}// addDepartment

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public List<DeptVO> slctAllDept() throws SQLException {

		DbConnection dbCon = DbConnection.getInstance();
		DeptVO dVO = null;
		List<DeptVO> list = new ArrayList<DeptVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			String id = "scott";
			String pass = "tiger";
			con = dbCon.getConnection(id, pass);

			String selectQuery = "SELECT DEPT_CODE, DEPT_NAME,	DEPT_CAPACITY, DEPT_INPUT_DATE,	DEPT_DELETE_FLAG	"
					+ "	FROM DEPT ";
			pstmt = con.prepareStatement(selectQuery);

			// 5. 쿼리 실행 및 결과 처리
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dVO = new DeptVO(rs.getInt("DEPT_CODE"), rs.getString("DEPT_NAME"), rs.getInt("DEPT_CAPACITY"),
						rs.getString("DEPT_INPUT_DATE"), rs.getString("DEPT_DELETE_FLAG"));

				// System.out.println(dVO);

				list.add(dVO);

			}

		} finally {

			dbCon.dbClose(null, pstmt, con);
		} // end finally

		return list;

	}// slctAllDept

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 관리자모드 모든 관리자들의 정보를 가져오기 위한 DAO
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<AdminVO> slctAllAdmin() throws SQLException {
		List<AdminVO> listAdminVO = new ArrayList<AdminVO>();
		AdminVO aVO = null;
		DbConnection dbCon = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String id = "scott";
			String pass = "tiger";

			con = dbCon.getConnection(id, pass);

			String selectAdminMgt = "SELECT ADMIN_ID,	ADMIN_PASSWORD , ADMIN_NAME, ADMIN_CHMOD from ADMIN  order by ADMIN_CHMOD";
			pstmt = con.prepareStatement(selectAdminMgt);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				aVO = new AdminVO(rs.getString("ADMIN_ID"), rs.getString("ADMIN_PASSWORD"), rs.getString("ADMIN_NAME"),
						rs.getInt("ADMIN_CHMOD"));
				listAdminVO.add(aVO);

			} // end while
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		} // end finally

		return listAdminVO;
	} // slctAdminfMgt

	/**
	 * 관리자모드 > 관리자 관리에서 관리자 정보 수정을 위한 method
	 * 
	 * @param aVO
	 * @throws SQLException
	 */
	public void modifyAdmin(AdminVO aVO) throws SQLException {
		DbConnection dbCon = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			String id = "scott";
			String pass = "tiger";

			con = dbCon.getConnection(id, pass);

			String modifyProf = "update ADMIN set ADMIN_NAME = ? , ADMIN_PASSWORD = ? where ADMIN_ID = ?";
			pstmt = con.prepareStatement(modifyProf);

			pstmt.setString(1, aVO.getAdmin_name());
			pstmt.setString(2, aVO.getAdmin_password());
			pstmt.setString(3, aVO.getAdmin_id());

			pstmt.executeUpdate();
		} finally {
			dbCon.dbClose(null, pstmt, con);
		} // end finally
	} // modifyProf

	public void addCrs(DeptVO dVO) throws SQLException {

		DbConnection dbCon = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			String id = "scott";
			String pass = "tiger";
			con = dbCon.getConnection(id, pass);

			///////////////////////////////////////////////
			///////////////// 학과등록 구현부//////////////////
			//////////////////////////////////////////////

		} finally {

			dbCon.dbClose(null, pstmt, con);
		} // end finally

	}// addCrs

}
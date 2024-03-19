package eduCourse_prj.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import eduCourse_prj.DbConnection;
import eduCourse_prj.VO.AdminVO;
import eduCourse_prj.VO.CrsVO;
import eduCourse_prj.VO.DeptDTO;
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
		ResultSet rs = null;

		try {

			String id = "scott";
			String pass = "tiger";
			con = dbCon.getConnection(id, pass);

			// 학과추가 순서

			// 0. 동일한 학과명 있는지 확인
			String checkDeptName = "select 	DEPT_NAME 		from	 dept where DEPT_NAME = ?";
			pstmt = con.prepareStatement(checkDeptName);
			pstmt.setString(1, dVO.getDept_name());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "입력하신 학과명과 동일한 학과명을 가진 학과가 이미 존재합니다..", "오류",
						JOptionPane.ERROR_MESSAGE);
				return;

			}
			// 1. 학과번호 확인

			int dept_code = 0000;
			String checkDept = "select 	max(dept_code) 		from	 dept";
			pstmt = con.prepareStatement(checkDept);
			rs = pstmt.executeQuery();
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

	/**
	 * 과목 등록을 위한 DAO 동일한 과목명이 존재하거나 동일한 과목코드가 존재하면 리턴
	 * 
	 * @param dVO
	 * @throws SQLException
	 */
	@SuppressWarnings("resource")
	public void addcourse(CrsVO cVO) throws SQLException {

		DbConnection dbCon = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			String id = "scott";
			String pass = "tiger";
			con = dbCon.getConnection(id, pass);

			String checkCorseCode = "SELECT COURSE_CODE FROM COURSE WHERE COURSE_CODE = ? ";
			pstmt = con.prepareStatement(checkCorseCode);
			pstmt.setString(1, cVO.getCourCode());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "입력하신 과목코드와 동일한 과목코드를가진 과목이 이미 존재합니다.", "오류",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			String checkCourseName = "SELECT COURSE_NAME FROM COURSE WHERE COURSE_NAME = ?";
			pstmt = con.prepareStatement(checkCourseName);
			pstmt.setString(1, cVO.getCourName());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "입력하신 과목명과 동일한 과목명을 가진 과목이 이미 존재합니다.", "오류",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			String addcourse = "insert into COURSE(COURSE_CODE,COURSE_NAME,CREDIT_HOURS,DEPT_CODE) values(?,?,?,?)";
			pstmt = con.prepareStatement(addcourse);

			pstmt.setString(1, cVO.getCourCode());
			pstmt.setString(2, cVO.getCourName());
			pstmt.setInt(3, cVO.getCreditHour());

			pstmt.setInt(4, cVO.getDeptCode());

			pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "과목 등록이 완료되었습니다.");

		} finally {

			dbCon.dbClose(null, pstmt, con);
		} // end finally

	}// addDepartment

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 모든 부서의 정보를 가져오기 위한 DAO
	 * 
	 * @return
	 * @throws SQLException
	 */
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
	 * @return 학과관리 조회를 위한 메서드
	 * @throws SQLException
	 */
	public DeptDTO selectOneDept(int deptCode) throws SQLException{
		DeptDTO dDTO = null;
		DbConnection dbCon = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String id = "scott";
			String pass = "tiger";

			con = dbCon.getConnection(id, pass);

			String selectQuery =   "SELECT d.DEPT_CODE, d.DEPT_NAME, p.PROF_NAME, d.DEPT_CAPACITY "
                    + "FROM DEPT d "
                    + "INNER JOIN PROFESSOR p ON d.DEPT_CODE = p.DEPT_CODE "
                    + "WHERE d.DEPT_CODE = ?";		
			//CURSOR를 양방향으로 전환가능하게 만듬
			pstmt = con.prepareStatement(selectQuery,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			pstmt.setInt(1, deptCode);
			rs = pstmt.executeQuery();
			
	        List<String> professors = new ArrayList<>(); // 교수 목록을 저장하기 위한 리스트 생성
	        
	        ////////////////교수가 존재할떄/////////////////
	        if (rs.next()) {
	        	//커서를 처음으로 초기화
	        	rs.beforeFirst();
	        	while(rs.next()) {
	        		professors.add(rs.getString("PROF_NAME"));
	            dDTO = new DeptDTO(
	                    rs.getInt("DEPT_CODE"),
	                    rs.getString("DEPT_NAME"),
	                    professors,     // DeptDTO에 리스트 전달
	                    rs.getInt("DEPT_CAPACITY"));
	        	}
			} else {
		        ////////////////교수가 존재하지 않을때/////////////////
				pstmt.close(); //닫기
				rs.close(); //닫기
				
				//새로운 쿼리 작성
				selectQuery = "SELECT DEPT_CODE, DEPT_NAME, DEPT_CAPACITY "
	                + "FROM DEPT d "    
	                + "WHERE d.DEPT_CODE = ?";		

	            pstmt = con.prepareStatement(selectQuery);
	            pstmt.setInt(1, deptCode);
	            rs = pstmt.executeQuery();

	            if (rs.next()) {
	                dDTO = new DeptDTO(
	                    rs.getInt("DEPT_CODE"),
	                    rs.getString("DEPT_NAME"),
	                    professors,     // DeptDTO에 리스트 전달
	                    rs.getInt("DEPT_CAPACITY")
	                );
	            }
	        }
	    } finally {
	        dbCon.dbClose(rs, pstmt, con);
	    } // end finally
	    return dDTO;
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 학과 삭제를 위한 메서드
	 * @param deptCode 삭제할 학과의 코드
	 * @throws SQLException
	 */
	public boolean deleteDept(int deptCode) throws SQLException {
	    DbConnection dbCon = DbConnection.getInstance();
	    
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    boolean isSuccess = false; // 삭제 성공 여부를 저장할 변수

	    try {
	        String id = "scott";
	        String pass = "tiger";
	        
	        con = dbCon.getConnection(id, pass);
	        
	        String deleteDept = "DELETE FROM DEPT WHERE DEPT_CODE = ?";
	        pstmt = con.prepareStatement(deleteDept);
	        
	        pstmt.setInt(1, deptCode);
	        
	        int rowsAffected = pstmt.executeUpdate(); // 실행된 행의 수를 반환 
	        if (rowsAffected > 0) { // 행이 한 개 이상 영향을 받았을 때
	            isSuccess = true;
	        }      
	        pstmt.executeUpdate();
	    } finally {
	        dbCon.dbClose(null, pstmt, con);
	    } // end finally
		return isSuccess;
	} // deleteDept
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
	 * 관리자모드 모든 과목의 정보를 가져오기 위한 DAO
	 * 
	 * @return 과목 정보를 담고 있는 CrsVO 객체의 리스트
	 * @throws SQLException
	 */
	public List<CrsVO> slctAllCrs() throws SQLException {

		List<CrsVO> courseList = new ArrayList<>();

		Connection con = null;
		DbConnection dbCon = DbConnection.getInstance();
		PreparedStatement pstmt = null;

		try {
			String id = "scott";
			String pass = "tiger";

			con = dbCon.getConnection(id, pass);

			
			String slctAllCrs = "SELECT c.COURSE_CODE, c.COURSE_NAME, c.CREDIT_HOURS, c.COURSE_INPUT_DATE, c.COURSE_DELETE_FLAG, d.DEPT_CODE, d.DEPT_NAME	"
					+ "FROM COURSE c, DEPT d " + "WHERE c.DEPT_CODE = d.DEPT_CODE AND COURSE_DELETE_FLAG = 'N'" + "ORDER BY COURSE_CODE";
			pstmt = con.prepareStatement(slctAllCrs);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				CrsVO course = new CrsVO(rs.getString("COURSE_CODE"), rs.getString("COURSE_NAME"),
						rs.getInt("CREDIT_HOURS"), rs.getString("COURSE_INPUT_DATE"),
						rs.getString("COURSE_DELETE_FLAG"), rs.getInt("DEPT_CODE"), rs.getString("DEPT_NAME"));
				courseList.add(course);
			}
		} finally {
			dbCon.dbClose(null, pstmt, null);
			;
		}

		// 과목 정보가 담긴 리스트를 반환합니다.
		return courseList;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
	
	/**
	 * 관리자 모드 > 학과 관리 > 학과 상세 조회, 교수 모드 > 학과 메인을 위한 method
	 * @param crs_number
	 * @return
	 * @throws SQLException
	 */
	public CrsVO slctOneCrs(String crs_name) throws SQLException{
		CrsVO cVO = null;
		DbConnection dbCon = DbConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String id = "scott";
			String pass = "tiger";
			
			con = dbCon.getConnection(id, pass);
			
			String selectCrs = "SELECT c.COURSE_CODE, c.COURSE_NAME, c.CREDIT_HOURS , d.DEPT_CODE ,d.DEPT_NAME	"
					+ "	FROM  COURSE c , DEPT d	"
					+ "	where  c. COURSE_NAME = ?  and c.DEPT_CODE = d.DEPT_CODE ";
			pstmt = con.prepareStatement(selectCrs);
			pstmt.setString(1, crs_name);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				cVO = new CrsVO(rs.getString("COURSE_CODE"), rs.getString("COURSE_NAME"), rs.getInt("CREDIT_HOURS"),rs.getInt("DEPT_CODE"),rs.getString("DEPT_NAME"));
			} // end while
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		} // end finally
		
		return cVO;

	} // slctOneCrs
	//////////////////////////////////////////////////////////////////
	/**
	 * 관리자모드 > 과목 관리에서 과목 삭제를 위한 method
	 * @param crs_name
	 * @throws SQLException
	 */
	public void deleteCrs(String crs_name) throws SQLException {
		DbConnection dbCon = DbConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			String id = "scott";
			String pass = "tiger";
			
			con = dbCon.getConnection(id, pass);
			
			String deleteCrs = "update COURSE set COURSE_DELETE_FLAG = 'Y' where COURSE_NAME = ?";
			pstmt = con.prepareStatement(deleteCrs);
			
			pstmt.setString(1, crs_name);
			
			pstmt.executeUpdate();
		} finally {
			dbCon.dbClose(null, pstmt, con);
		} // end finally
	} // deleteProf
	
	
	
	
	
	
	
	
}
	


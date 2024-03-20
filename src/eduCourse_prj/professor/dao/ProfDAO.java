package eduCourse_prj.professor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eduCourse_prj.DbConnection;
import eduCourse_prj.VO.AdminProfVO;
import eduCourse_prj.VO.LoginVO;
import eduCourse_prj.VO.ProfVO;
import eduCourse_prj.VO.SlctStdVO;
import eduCourse_prj.VO.StdntVO;

public class ProfDAO {
	private static ProfDAO pDAO;

	private ProfDAO() {
	}

	public static ProfDAO getInstance() {
		if (pDAO == null) {
			pDAO = new ProfDAO();
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
	 * 관리자모드 과목관리의 특정학과의 교수의 교번, 이름을 가져오기 위한 DAO
	 * @return 특정학과의 교수 리스트
	 * @throws SQLException
	 */
	public List<ProfVO> slctDeptProf(int dept_code) throws SQLException {
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
			
			String selectDeptProf = "select prof_number, prof_name from professor where prof_delete_flag = 'N' and DEPT_CODE = ? order by prof_number";
			pstmt = con.prepareStatement(selectDeptProf);
			pstmt.setInt(1, dept_code);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				pVO = new ProfVO(rs.getInt("prof_number"), rs.getString("prof_name"));
				listProfVO.add(pVO);
			} // end while
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		} // end finally
		
		return listProfVO;
	} // slctDeptProf
	
	
	
	

	
	/**
	 * 관리자모드 교수관리의 교번, 이름, 학과 번호를 가져오기 위한 DAO
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
			
			String selectProfMgt = "	select p.prof_number, p.prof_name , d.dept_name	"
					+ "	from professor p	"
					+ "	JOIN dept d ON p.dept_code = d.dept_code	"
					+ "	where prof_delete_flag = 'N' order by dept_name	";
			pstmt = con.prepareStatement(selectProfMgt);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				pVO = new ProfVO(rs.getInt("prof_number"), rs.getString("prof_name"), rs.getString("dept_name"));
				listProfVO.add(pVO);
			} // end while
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		} // end finally
		
		return listProfVO;
	} // slctProfMgt
	
	/**
	 * 관리자 모드 > 교수 관리 > 교수 상세 조회, 교수 모드 > 교수 메인을 위한 method
	 * @param prof_number
	 * @return
	 * @throws SQLException
	 *  */
	public AdminProfVO slctProfMgtSlct(int prof_number) throws SQLException{
	
		AdminProfVO apVO = null;
		DbConnection dbCon = DbConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String id = "scott";
			String pass = "tiger";
			
			con = dbCon.getConnection(id, pass);
			
			String selectProf = "SELECT p.PROF_NUMBER, p.PROF_NAME, p.PROF_EMAIL, d.DEPT_NAME, c.COURSE_NAME "
                    + "FROM PROFESSOR p "
                    + "JOIN DEPT d ON p.DEPT_CODE = d.DEPT_CODE "
                    + "JOIN COURSE c ON d.DEPT_CODE = c.DEPT_CODE "
                    + "WHERE p.PROF_NUMBER = ?";
			pstmt = con.prepareStatement(selectProf,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			pstmt.setInt(1, prof_number);
			rs = pstmt.executeQuery();

			List<String> courses = new ArrayList<>();
			///////////////////과목이 존재한다면//////////////////////////
			if(rs.next()) {
				rs.beforeFirst();
				while(rs.next()) {
					courses.add(rs.getString("course_name"));
					apVO = new AdminProfVO(
							prof_number,
							rs.getString("prof_name"),
							rs.getString("prof_email"),
							rs.getString("dept_name"),
							courses);
				} // end while				
			}//end if
			else {
				/////////////////과목이 없다면//////////////////////////
				pstmt.close(); //닫기
				rs.close(); // 닫기
				
				//새로운 쿼리 생성
				selectProf = "SELECT p.PROF_NUMBER, p.PROF_NAME, p.PROF_EMAIL, d.DEPT_NAME "
	                       + "FROM PROFESSOR p "
	                       + "JOIN DEPT d ON p.DEPT_CODE = d.DEPT_CODE "
						   + "WHERE p.PROF_NUMBER = ?";
				pstmt = con.prepareStatement(selectProf);
				pstmt.setInt(1, prof_number);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
				apVO = new AdminProfVO(
						prof_number,
						rs.getString("prof_name"),
						rs.getString("prof_email"),
						rs.getString("dept_name"),
						courses);
				}
			}//end else
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		} // end finally
		
		return apVO;
//		return listProfVO;
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
		} finally {
			dbCon.dbClose(rs, pstmt, con);
			dbCon.dbClose(rs, pstmt2, con);
		} // end finally
	} // addProf
	
	/**
	 * 관리자모드 > 교수 관리에서 교수 삭제를 위한 method
	 * @param prof_number
	 * @throws SQLException
	 */
	public void deleteProf(int prof_number) throws SQLException {
		DbConnection dbCon = DbConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			String id = "scott";
			String pass = "tiger";
			
			con = dbCon.getConnection(id, pass);
			
			String deleteProf = "update professor set prof_delete_flag = 'Y' where prof_number = ?";
			pstmt = con.prepareStatement(deleteProf);
			
			pstmt.setInt(1, prof_number);
			
			pstmt.executeUpdate();
		} finally {
			dbCon.dbClose(null, pstmt, con);
		} // end finally
	} // deleteProf
	
	/**
	 * 관리자모드 > 교수 관리에서 교수 정보 수정을 위한 method
	 * @param pVO
	 * @throws SQLException
	 */
	public void adminModifyProf(ProfVO pVO) throws SQLException {
		DbConnection dbCon = DbConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			String id = "scott";
			String pass = "tiger";
			
			con = dbCon.getConnection(id, pass);
			 
			String modifyProf = "update professor set prof_name = ?, prof_password = ?, prof_email = ?, "
					+ "dept_code = (select dept_code from dept where dept_name = ?) where prof_number = ?";
			pstmt = con.prepareStatement(modifyProf);
			
			pstmt.setString(1, pVO.getProf_name());
			pstmt.setString(2, pVO.getProf_password());
			pstmt.setString(3, pVO.getProf_email());
			pstmt.setString(4, pVO.getDept_name());
			pstmt.setInt(5, pVO.getProf_number());
			
			pstmt.executeUpdate();
		} finally {
			dbCon.dbClose(null, pstmt, con);
		} // end finally
	} // modifyProf
	
	
	/**
	 * 관리자모드에서  학과,교번으로 교수를 검색하는 DAO
	 * @param dept_code //학번코드
	 * @param prof_num //학번
	 * @return 선택된 학과,교번,교수명을 가진 ProfVO를 가지고있는 리스트
	 * @throws SQLException
	 */
	public List<ProfVO> slctProf(int dept_code ,int prof_num) throws SQLException {
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
			
			
			//1 전체 비어있음
			if(dept_code==0  && prof_num==0) {
				
				String selectProf = "SELECT d.dept_name, p.PROF_NUMBER, p.PROF_NAME	"
						+ "						FROM PROFESSOR p	"
						+ "						JOIN dept d ON p.dept_code = d.dept_code	"
						+ "						where p.PROF_DELETE_FLAG = 'N'	"
						+ "						order by dept_name	";
				
				
				
				
				pstmt = con.prepareStatement(selectProf);
				
			}
			
			//2 전체 비어X
			else if(dept_code==0 && prof_num!=0) {
				
				String selectProf = "SELECT d.dept_name, p.PROF_NUMBER, p.PROF_NAME	"
						+ "						FROM PROFESSOR p	"
						+ "						JOIN dept d ON p.dept_code = d.dept_code	"
						+ "						WHERE p.prof_number = ?	"	
						+ "						AND p.PROF_DELETE_FLAG = 'N'	"
						+ "						order by dept_name	";

				

				pstmt = con.prepareStatement(selectProf);
				pstmt.setInt(1,prof_num);
			}
			
			//3 일부 비어있음
			else if(dept_code!=0  && prof_num==0) {
				
				String selectProf =  "SELECT d.dept_name, p.PROF_NUMBER, p.PROF_NAME	"
						+ "						FROM PROFESSOR p	"
						+ "						JOIN dept d ON p.dept_code = d.dept_code	"
						+ "						WHERE d.dept_code = ?	"
						+ "						AND p.PROF_DELETE_FLAG = 'N'	"
						+ "						order by dept_name	";

				

				pstmt = con.prepareStatement(selectProf);
				pstmt.setInt(1,dept_code);
			}
			
			
			//4 일부 비어X
			
			else if(dept_code!=0 && prof_num!=0) {
				
				String selectProf = "SELECT d.dept_name, p.PROF_NUMBER, p.PROF_NAME	"
						+ "						FROM PROFESSOR p	"
						+ "						JOIN dept d ON p.dept_code = d.dept_code	"
						+ "						WHERE d.dept_code = ?	"				
						+ "						AND p.prof_number = ?	"	
						+ "						AND p.PROF_DELETE_FLAG = 'N'	"
						+ "						order by dept_name	";
				

				pstmt = con.prepareStatement(selectProf);
				pstmt.setInt(1,dept_code);
				pstmt.setInt(2,prof_num);
			}

			

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				pVO = new ProfVO(rs.getInt("prof_number"), rs.getString("prof_name"),rs.getString("dept_name"));

				listProfVO.add(pVO);
				
				
			} // end while
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		} // end finally
		
		return listProfVO;
	} // slctDeptProf
	
	/**
	 * 교수모드 > 교수 메인 정보호출을 위한 method
	 * @param prof_number
	 * @return
	 * @throws SQLException
	 */
	public ProfVO slctOneProf(int prof_number) throws SQLException{
		ProfVO pVO = null;
		DbConnection dbCon = DbConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String id = "scott";
			String pass = "tiger";
			
			con = dbCon.getConnection(id, pass);
			
			String selectProf = "select p.PROF_NUMBER, p.PROF_PASSWORD, p.PROF_NAME, p.PROF_EMAIL, d.dept_name "
								+ "	from PROFESSOR p "
								+ "	join dept d on p.dept_code = d.dept_code "
								+ "	WHERE p.prof_number = ?";
			
			pstmt = con.prepareStatement(selectProf);
			pstmt.setInt(1, prof_number);
			rs = pstmt.executeQuery();
			if(rs.next()) {

				pVO = new ProfVO(rs.getInt("PROF_NUMBER"),
						rs.getString("PROF_PASSWORD"),
						rs.getString("PROF_NAME"),
						rs.getString("PROF_EMAIL"),
						rs.getString("DEPT_NAME"));
			
			
			} // end while
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		} // end finally
		
		return pVO;
	} // slctOneProf
	
	/**
	 * 교수모드 > 교수 정보 수정을 위한 method
	 * @param pVO
	 * @throws SQLException
	 */
	public void modifyProf(ProfVO pVO) throws SQLException {
		DbConnection dbCon = DbConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			String id = "scott";
			String pass = "tiger";
			
			con = dbCon.getConnection(id, pass);
			
			String modifyProf = "update PROFESSOR set PROF_PASSWORD = ?, PROF_EMAIL = ? where PROF_NUMBER = ?";
			
			pstmt = con.prepareStatement(modifyProf);
			pstmt.setString(1, pVO.getProf_password());
			pstmt.setString(2, pVO.getProf_email());
			pstmt.setInt(3, pVO.getProf_number());
			
			pstmt.executeUpdate();
		} finally {
			dbCon.dbClose(null, pstmt, con);
		} // end finally
	} // modifyStdnt

	
	
	
	
} // class
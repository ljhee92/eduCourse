package eduCourse_prj.professor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import java.util.List;

import javax.swing.JOptionPane;

import eduCourse_prj.DbConnection;
import eduCourse_prj.VO.AdminVO;
import eduCourse_prj.VO.CrsVO;
import eduCourse_prj.VO.DeptVO;
import eduCourse_prj.VO.TestPageVO;
import eduCourse_prj.VO.TestQustVO;


public class TestDAO {
	private static TestDAO tDAO;

	private TestDAO() {

	}

	public static TestDAO getInstance() {
		if (tDAO == null) {
			tDAO = new TestDAO();

		} // end if

		return tDAO;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	@SuppressWarnings("resource")
	public void insertTest(TestQustVO tqVO) throws SQLException {

		DbConnection dbCon = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			String id = "scott";
			String pass = "tiger";
			con = dbCon.getConnection(id, pass);



			// 1. 문제 id 삽입

//			
			//----------------------------------
			String checkTestCode = "select 	QUESTION_NUMBER 		from	 TEST_QUESTION	where COURSE_CODE = ?";
			pstmt = con.prepareStatement(checkTestCode);
			pstmt.setString(1,tqVO.getCrs_code());
			rs = pstmt.executeQuery();
			
			

            //----------------------------------
			String addTest = "insert into TEST_QUESTION(QUESTION_NUMBER, QUESTION_CONTENT, ANSWER, PROF_NUMBER, COURSE_CODE) values(?,?,?,?,?)";
			
			pstmt = con.prepareStatement(addTest);

			pstmt.setInt(1, tqVO.getQust_number());
			pstmt.setString(2, tqVO.getQust_content());
			pstmt.setInt(3, tqVO.getAnswer());
			pstmt.setInt(4, tqVO.getProf_number());
			pstmt.setString(5, tqVO.getCrs_code());

			while(rs.next()) {
				if (rs.getInt("QUESTION_NUMBER")==tqVO.getQust_number()) {
					JOptionPane.showMessageDialog(null, "이미 출제된 번호입니다 번호 " + tqVO.getQust_number()+"번");
					return;
				}
			}
			
			pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "시험이 성공적으로 등록되엇습니다.");

		} finally {

			dbCon.dbClose(null, pstmt, con);
		} // end finally

	}// addtest
	
	public List<Integer> selectValidTestNumber(String courseName) throws SQLException {
		DbConnection dbCon = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Integer> qustNum = new ArrayList<Integer>();
		try {

			String id = "scott";
			String pass = "tiger";
			con = dbCon.getConnection(id, pass);
			


			// 1. 문제 id 삽입

//			
			//----------------------------------
			String checkTestCode = "SELECT t.question_number"
					+ "	FROM test_question t"
					+ "	JOIN course c ON t.course_code = c.course_code"
					+ "	WHERE c.course_name = ?";
			pstmt = con.prepareStatement(checkTestCode);
			pstmt.setString(1,courseName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				qustNum.add(rs.getInt("QUESTION_NUMBER"));
			}
			
		} finally {

			dbCon.dbClose(null, pstmt, con);
		} // end finally
		return qustNum;
	}// addtest
	
	
	@SuppressWarnings("resource")
	public void selectAllTest(TestQustVO tqVO) throws SQLException {

		DbConnection dbCon = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			String id = "scott";
			String pass = "tiger";
			con = dbCon.getConnection(id, pass);



			// 1. 문제 id 삽입

//			
			//----------------------------------
			String checkTestCode = "select 	QUESTION_NUMBER 		from	 TEST_QUESTION	where COURSE_CODE = ?";
			pstmt = con.prepareStatement(checkTestCode);
			pstmt.setString(1,tqVO.getCrs_code());
			rs = pstmt.executeQuery();
			
			

            //----------------------------------
			String addTest = "select QUESTION_NUMBER from TEST_QUESTION "
					+ "where prof_number = ? and course_code = ?";
			
			pstmt = con.prepareStatement(addTest);

			pstmt.setInt(1, tqVO.getProf_number());
			pstmt.setString(2, tqVO.getCrs_code());


			while(rs.next()) {
				if (rs.getInt("QUESTION_NUMBER")==tqVO.getQust_number()) {
					JOptionPane.showMessageDialog(null, "이미 출제된 번호입니다 번호 " + tqVO.getQust_number()+"번");
					return;
				}
			}
			
			pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "시험이 성공적으로 등록되엇습니다.");

		} finally {

			dbCon.dbClose(null, pstmt, con);
		} // end finally

	}// addtest
	

	public void updateOnetest(TestQustVO tqVO) throws SQLException {
		DbConnection dbCon = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			String id = "scott";
			String pass = "tiger";

			con = dbCon.getConnection(id, pass);

			String modifyProf = "update TEST_QUESTION set QUESTION_CONTENT, ANSWER where ADMIN_ID = ?";
			pstmt = con.prepareStatement(modifyProf);

//			pstmt.setString(1, tqVO.get());
//			pstmt.setString(2, aVO.getAdmin_password());
//			pstmt.setString(3, aVO.getAdmin_id());

			pstmt.executeUpdate();
		} finally {
			dbCon.dbClose(null, pstmt, con);
		} // end finally
	} // modifyProf
	
//	public TestQustVO selectOneDept(int testQuestionId) throws SQLException {
//        TestQustVO tqVO = null;
//        DbConnection dbCon = DbConnection.getInstance();
//
//        Connection con = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//
//        try {
//            String id = "scott";
//            String pass = "tiger";
//
//            con = dbCon.getConnection(id, pass);
//
//            String selectQuery = "SELECT Question_Content, Answer FROM Test_Question WHERE Test_Question_Id = ?";
//
//            pstmt = con.prepareStatement(selectQuery);
//            pstmt.setInt(1, testQuestionId);
//            rs = pstmt.executeQuery();
//            
//            if (rs.next()) {
//                String questionContent = rs.getString("Question_Content");
//                int answer = rs.getInt("Answer");
//                // 여기서 TestQustVO 생성자에 맞게 파라미터를 전달하여 객체를 생성합니다.
//                tqVO = new TestQustVO(testQuestionId, questionContent, answer, 0, null); // 교수 번호와 과목 코드는 null 또는 0으로 초기화합니다.
//            }
//        } finally {
//            dbCon.dbClose(rs, pstmt, con);
//        }
//
//        return tqVO;
//    }

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
	
	/**
	 * test_question 테이블에서 시험 문제 번호와 내용을 가져오는 method
	 * @return
	 */
	public TestPageVO slctOneTestQuestion(String course_name, int question_number) throws SQLException {
		TestPageVO tpVO = null;
		DbConnection dbCon = DbConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String id = "scott";
			String pass = "tiger";
			
			con = dbCon.getConnection(id, pass);
			
			StringBuilder slctOneTestQuestion = new StringBuilder();
			slctOneTestQuestion.append("select c.course_name, t.question_number, t.question_content, t.answer ")
							   .append("from test_question t ")
							   .append("join course c on c.course_code = t.course_code ")
							   .append("where c.course_name = ? and t.question_number = ?");
			
			pstmt = con.prepareStatement(slctOneTestQuestion.toString());
			pstmt.setString(1, course_name);
			pstmt.setInt(2, question_number);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				tpVO = new TestPageVO(rs.getInt("question_number"), rs.getString("question_content"), rs.getString("answer"));
			} // end while
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		} // end finally

		return tpVO;
	} // slctOneTestQuestion
	
	
	
}
	


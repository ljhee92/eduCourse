package eduCourse_prj.student.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eduCourse_prj.DbConnection;
import eduCourse_prj.VO.RegVO;
import eduCourse_prj.VO.ScoreVO;
import eduCourse_prj.VO.StdntAnswerVO;
import eduCourse_prj.VO.StdntTestVO;
import eduCourse_prj.VO.TestPageVO;


public class StdntTestDAO {
	
	private static StdntTestDAO stDAO;
	
	private StdntTestDAO() {
	}
	
	public static StdntTestDAO getInstance() {
		if(stDAO == null) {
			stDAO = new StdntTestDAO();
		} // end if
		return stDAO;
	} // getInstance
	
	/**
	 * DB에서 학생 시험응시를 위한 수강과목 리스트를 가져오는 method
	 * @param stdnt_number
	 * @param course_code
	 * @return
	 * @throws SQLException 
	 */
	public List<StdntTestVO> slctAllStdntTestList(int stdnt_number) throws SQLException{
		List<StdntTestVO> listSTVO = new ArrayList<StdntTestVO>();
		StdntTestVO stVO = null;
		
		DbConnection dbCon = DbConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String id = "scott";
			String pass = "tiger";
			
			con = dbCon.getConnection(id, pass);
			
			StringBuilder slctAllStdntTestList = new StringBuilder();
			slctAllStdntTestList.append("select d.dept_name, c.course_name, r.course_code, p.prof_name, l.test_flag, s.score, c.credit_hours ")
								.append("from register r ")
								.append("join course c on c.course_code = r.course_code ")
								.append("join dept d on d.dept_code = c.dept_code ")
								.append("join lecture l on l.course_code = r.course_code ")
								.append("join professor p on p.prof_number = r.prof_number ")
								.append("left outer join score s on s.register_number = r.register_number ")
								.append("where r.std_number = ? and l.lect_delete_flag = 'N'");
			
			pstmt = con.prepareStatement(slctAllStdntTestList.toString());
			pstmt.setInt(1, stdnt_number);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				stVO = new StdntTestVO(rs.getString("dept_name"), rs.getString("course_name"), rs.getString("course_code"), 
									 rs.getString("prof_name"), rs.getString("test_flag"), rs.getInt("score"), rs.getInt("credit_hours"));
				listSTVO.add(stVO);
			} // end while
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		} // end finally
		
		return listSTVO;
	} // slctAllStdntTestList
	
	/**
	 * DB에서 문제 번호와 문제 내용, 보기를 가져오는 method
	 * @param course_code
	 * @return
	 * @throws SQLException
	 */
	public List<TestPageVO> slctTestPage(String course_code) throws SQLException{
		List<TestPageVO> listTPVO = new ArrayList<TestPageVO>();
		TestPageVO tpVO = null;
		
		DbConnection dbCon = DbConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String id = "scott";
			String pass = "tiger";
			
			con = dbCon.getConnection(id, pass);
			
			String slctTestQuestion = "select question_number, question_content, answer from test_question where course_code = ?";
			
			pstmt = con.prepareStatement(slctTestQuestion);
			pstmt.setString(1, course_code);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				tpVO = new TestPageVO(rs.getInt("question_number"), rs.getString("question_content"), rs.getString("answer"));
				listTPVO.add(tpVO);
			} // end while
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		} // end finally

		return listTPVO;
	} // slctTestPage
	
	/**
	 * 학생의 답안을 DB의 std_answer 테이블에 추가하는 method
	 * @param saVO
	 * @throws SQLException 
	 */
	public void insertTestAnswer(StdntAnswerVO saVO) throws SQLException {
		DbConnection dbCon = DbConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			String id = "scott";
			String pass = "tiger";
			con = dbCon.getConnection(id, pass);
			
			StringBuilder insertStdAnswer = new StringBuilder();
			insertStdAnswer.append("insert into std_answer(register_number, question_number, std_answer, course_code) ")
							.append("values(?, ?, ?, ?)");
			
			pstmt = con.prepareStatement(insertStdAnswer.toString());
			
			pstmt.setInt(1, saVO.getRegister_number());
			pstmt.setInt(2, saVO.getQuestion_number());
			pstmt.setString(3, saVO.getStd_answer());
			pstmt.setString(4, saVO.getCourse_code());
			
			pstmt.executeUpdate();
		} finally {
			dbCon.dbClose(null, pstmt, con);
		} // end finally
	} // insertTestAnswer
	
	/**
	 * 학생의 해당 과목 register number를 가져오기 위한 method
	 * @param stdnt_number
	 * @param course_code
	 * @return
	 * @throws SQLException
	 */
	public RegVO slctOneStdntReg(int stdnt_number, String course_code) throws SQLException {
		RegVO rVO = null;
		DbConnection dbCon = DbConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String id = "scott";
			String pass = "tiger";
			con = dbCon.getConnection(id, pass);
			
			String slctOneStdntReg = "select register_number from register where std_number = ? and course_code = ?";
			pstmt = con.prepareStatement(slctOneStdntReg);
			pstmt.setInt(1, stdnt_number);
			pstmt.setString(2, course_code);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				rVO = new RegVO(rs.getInt("register_number"));
			} // end if
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		} // end finally
		
		return rVO;
	} // slctOneStdntReg
	
	/**
	 * 학생의 점수를 DB의 score 테이블에 추가하는 method
	 * @param sVO
	 * @throws SQLException 
	 */
	public void insertScore(ScoreVO sVO) throws SQLException {
		DbConnection dbCon = DbConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			String id = "scott";
			String pass = "tiger";
			con = dbCon.getConnection(id, pass);
			
			String insertScore = "insert into score(register_number, score, test_date) values(?, ?, null)";
			
			pstmt = con.prepareStatement(insertScore);
			
			pstmt.setInt(1, sVO.getReg_number());
			pstmt.setInt(2, sVO.getScore());
			
			pstmt.executeUpdate();
		} finally {
			dbCon.dbClose(null, pstmt, con);
		} // end finally
	} // insertScore
	
	/**
	 * 학생의 시험 응시 여부를 확인하기 위해 점수 테이블을 조회하는 method
	 * @return
	 * @throws SQLException
	 */
	public ScoreVO slctScore(int stdnt_number, String course_code) throws SQLException { 
		ScoreVO sVO = null;
		
		DbConnection dbCon = DbConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String id = "scott";
			String pass = "tiger";
			con = dbCon.getConnection(id, pass);
			
			StringBuilder slctOneScore = new StringBuilder();
			slctOneScore.append("select r.register_number, s.score ")
						.append("from register r ")
						.append("join score s on s.register_number = r.register_number ")
						.append("where r.std_number = ? and r.course_code = ?");
			
			pstmt = con.prepareStatement(slctOneScore.toString());
			pstmt.setInt(1, stdnt_number);
			pstmt.setString(2, course_code);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				sVO = new ScoreVO(rs.getInt("register_number"), rs.getInt("score"));
			} // end if
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		} // end finally
		return sVO;
	} // slctScore

		
	

} // class
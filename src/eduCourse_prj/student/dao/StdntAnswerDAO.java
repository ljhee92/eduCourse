package eduCourse_prj.student.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eduCourse_prj.DbConnection;

import eduCourse_prj.VO.StdntAnswerVO;
import eduCourse_prj.VO.SubStdntAnswerVO;

public class StdntAnswerDAO {
	
	private static StdntAnswerDAO saDAO;
	
	private StdntAnswerDAO() {
		
	}
	
	public static StdntAnswerDAO getInstance() {
		if(saDAO == null) {
			saDAO = new StdntAnswerDAO();
		}
		return saDAO;
	}
	
	
	public StdntAnswerVO slctExamAnswer(String course_code) throws SQLException {
		StdntAnswerVO saVO = null;
		
		DbConnection dbCon = DbConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String id = "scott";
			String pass = "tiger";
			
			con = dbCon.getConnection(id, pass);
			
			StringBuilder slctAnswer = new StringBuilder();
			slctAnswer.append("SELECT s.question_number, s.std_answer, t.answer ");
			slctAnswer.append("FROM std_answer s ");
			slctAnswer.append("JOIN test_question t ");
			slctAnswer.append("ON s.course_code = t.course_code AND s.question_number = t.question_number ");
			slctAnswer.append("WHERE s.course_code = ?");

			String slctAnswerQuery = slctAnswer.toString();
			
			pstmt = con.prepareStatement(slctAnswerQuery);
			pstmt.setString(1, course_code);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				saVO = new StdntAnswerVO(rs.getInt("question_number"),rs.getString("std_answer"),rs.getString("question_number"));
			} // end while
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		} // end finally
		
		return saVO;
	}//slctExamAnswer
	
	public List<SubStdntAnswerVO> slctExamCrsList(int std_number) throws SQLException {
		SubStdntAnswerVO ssaVO = null;
		List<SubStdntAnswerVO> crsList = new ArrayList<SubStdntAnswerVO>();
		DbConnection dbCon = DbConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String id = "scott";
			String pass = "tiger";
			
			con = dbCon.getConnection(id, pass);
			
			StringBuilder slctQuery = new StringBuilder();
			slctQuery.append("SELECT r.register_number,c.course_name,c.course_code ")
			            .append("FROM register r ")
			            .append("JOIN course c ON c.course_code = r.course_code ")
			            .append("JOIN dept d ON d.dept_code = c.dept_code ")
			            .append("JOIN lecture l ON l.course_code = r.course_code ")
			            .append("JOIN professor p ON p.prof_number = r.prof_number ")
			            .append("LEFT OUTER JOIN score s ON s.register_number = r.register_number ")
			            .append("WHERE r.std_number = ? AND l.lect_delete_flag = 'N' AND s.score IS NOT NULL");
			String slctCrsQuery = slctQuery.toString();
			
			pstmt = con.prepareStatement(slctCrsQuery);
			pstmt.setInt(1, std_number);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ssaVO = new SubStdntAnswerVO(rs.getInt("register_number"),rs.getString("course_code"),rs.getString("course_name"));
				
				crsList.add(ssaVO);
			} // end while
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		} // end finally
				
		return crsList;
	}//slctExamAnswer
	
	public List<StdntAnswerVO> slctExamAnswer(String course_code,int register_number) throws SQLException{
		StdntAnswerVO saVO = null;
		List<StdntAnswerVO> answerList = new ArrayList<StdntAnswerVO>();
		DbConnection dbCon = DbConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String id = "scott";
			String pass = "tiger";
			
			con = dbCon.getConnection(id, pass);
			
			StringBuilder slctQuery = new StringBuilder();
			slctQuery.append("SELECT s.question_number, s.std_answer, t.answer ")
			            .append("FROM std_answer s ")
			            .append("JOIN test_question t ON s.course_code = t.course_code ")
			            .append("AND s.question_number = t.question_number ")
			            .append("JOIN register r ON s.register_number = r.register_number ")
			            .append("WHERE s.course_code = ? AND r.REGISTER_NUMBER = ?");
			String slctAnswerQuery = slctQuery.toString();
			
			pstmt = con.prepareStatement(slctAnswerQuery);
			pstmt.setString(1, course_code);
			pstmt.setInt(2, register_number);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				saVO = new StdntAnswerVO(rs.getInt("question_number"),rs.getString("std_answer"),rs.getString("answer"));
				
				answerList.add(saVO);
			} // end while
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		} // end finally
				
		return answerList;
	}
	
}

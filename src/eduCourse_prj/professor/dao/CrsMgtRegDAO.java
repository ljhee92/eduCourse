package eduCourse_prj.professor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import eduCourse_prj.DbConnection;
import eduCourse_prj.VO.CrsVO;
import eduCourse_prj.VO.LectureVO;

public class CrsMgtRegDAO {
	private static CrsMgtRegDAO cmrDAO;
	
	private CrsMgtRegDAO() {
	}
	
	public static CrsMgtRegDAO getInstance() {
		if(cmrDAO == null) {
			cmrDAO = new CrsMgtRegDAO();
		} // end if
		return cmrDAO;
	} // getInstance
	
	/**
	 * 교수모드 > 강의과목관리 > 등록, 수정 시 선택한 과목명의 과목코드를 가져오는 method
	 * @param CrsName
	 * @return
	 * @throws SQLException
	 */
	public CrsVO slctOneCrsCode(String CrsName) throws SQLException {
		CrsVO cVO = null;
		DbConnection dbCon = DbConnection.getInstance();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String id = "scott";
			String pass = "tiger";
			
			con = dbCon.getConnection(id, pass);
			
			String slctOneCrsCode = "select course_code, credit_hours from course where course_name = ?";
			pstmt = con.prepareStatement(slctOneCrsCode);
			pstmt.setString(1, CrsName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				cVO = new CrsVO(rs.getString("course_code"), CrsName, rs.getInt("credit_hours"));
			} // end if
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		} // end finally
		
		return cVO;
	} // slctOneCrsCode
	
	/**
	 * 교수관리 > 강의과목관리 > 등록 시 Lecture 테이블로 저장하는 method
	 * @param ltVO
	 * @throws SQLException
	 */
	public void insertLect(LectureVO ltVO) throws SQLException {
		DbConnection dbCon = DbConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			String id = "scott";
			String pass = "tiger";
			
			con = dbCon.getConnection(id, pass);
			
			StringBuilder insertLect = new StringBuilder();
			insertLect.append("insert into lecture(prof_number, course_code, capacity, lect_room) ")
					 .append("values(?, ?, ?, ?)");
			
			pstmt = con.prepareStatement(insertLect.toString());
			
			pstmt.setInt(1, ltVO.getProf_number());
			pstmt.setString(2, ltVO.getCourse_code());
			pstmt.setInt(3, ltVO.getCapacity());
			pstmt.setString(4, ltVO.getLect_room());
			
			pstmt.executeUpdate();
		} finally {
			dbCon.dbClose(null, pstmt, con);
		} // end finally
	} // insertLect
	
} // class
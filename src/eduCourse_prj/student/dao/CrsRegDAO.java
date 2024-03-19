package eduCourse_prj.student.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eduCourse_prj.DbConnection;
import eduCourse_prj.VO.CrsRegVO;

public class CrsRegDAO {
	
	private static CrsRegDAO crDAO;
	
	private CrsRegDAO() {
	}
	
	public static CrsRegDAO getInstance() {
		if(crDAO == null) {
			crDAO = new CrsRegDAO();
		} // end if
		return crDAO;
	} // getInstance
	
	/**
	 * DB에서 로그인한 학생의 수강신청 가능 과목을 가져오는 method
	 * @param stdnt_number
	 * @return
	 * @throws SQLException
	 */
	public List<CrsRegVO> slctAllCrsReg(int stdnt_number) throws SQLException {
		List<CrsRegVO> listCrsRegVO = new ArrayList<CrsRegVO>();
		CrsRegVO crVO = null;
		DbConnection dbCon = DbConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String id = "scott";
			String pass = "tiger";
			
			con = dbCon.getConnection(id, pass);
			
			StringBuilder selectAllCrsReg = new StringBuilder();
			selectAllCrsReg.append("select d.dept_name, c.course_name, c.course_code, l.lect_room, l.capacity, c.credit_hours ")
							.append("from dept d ")
							.append("join student s on s.dept_code = d.dept_code ")
							.append("right outer join course c on c.dept_code = d.dept_code ")
							.append("join lecture l on l.course_code = c.course_code ")
							.append("where s.std_number = ?");
			
			pstmt = con.prepareStatement(selectAllCrsReg.toString());
			pstmt.setInt(1, stdnt_number);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				crVO = new CrsRegVO(rs.getString("dept_name"), rs.getString("course_name"), rs.getString("course_code"), rs.getString("lect_room"), rs.getInt("capacity"), rs.getInt("credit_hours"));
				listCrsRegVO.add(crVO);
			} // end while
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		} // end finally
		
		return listCrsRegVO;
	} // slctAllCrsReg
	
	/**
	 * DB에서 수강신청 가능 과목 중 한 과목을 가져오는 method
	 * @param crs_code
	 * @return
	 * @throws SQLException
	 */
	public CrsRegVO slctOneCrsReg(String crs_code) throws SQLException {
		CrsRegVO crVO = null;
		DbConnection dbCon = DbConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String id = "scott";
			String pass = "tiger";
			
			con = dbCon.getConnection(id, pass);
			
			StringBuilder selectOneCrsReg = new StringBuilder();
			selectOneCrsReg.append("select d.dept_name, c.course_name, c.course_code, l.lect_room, l.capacity, c.credit_hours ")
							.append("from dept d ")
							.append("join course c on c.dept_code = d.dept_code ")
							.append("join lecture l on l.course_code = c.course_code ")
							.append("where c.course_code = ?");
			
			pstmt = con.prepareStatement(selectOneCrsReg.toString());
			pstmt.setString(1, crs_code);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				crVO = new CrsRegVO(rs.getString("dept_name"), rs.getString("course_name"), rs.getString("course_code"), rs.getString("lect_room"), rs.getInt("capacity"), rs.getInt("credit_hours"));
			} // end if
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		} // end finally
		
		return crVO;
	} // slctOneCrsReg

} // CrsRegDAO
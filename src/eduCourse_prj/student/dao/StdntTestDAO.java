package eduCourse_prj.student.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eduCourse_prj.DbConnection;
import eduCourse_prj.VO.StdntTestVO;
import oracle.jdbc.proxy.annotation.Pre;

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

		
	

} // class
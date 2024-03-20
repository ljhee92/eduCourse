package eduCourse_prj.professor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eduCourse_prj.DbConnection;
import eduCourse_prj.VO.ScoreVO;
import eduCourse_prj.VO.DeptVO;
import eduCourse_prj.VO.LoginVO;

public class ScoreDAO {
	private static ScoreDAO sDAO;
	public ScoreDAO() {
	}
	
	public static ScoreDAO getInstance() {
		if (sDAO == null) {
			sDAO = new ScoreDAO();
		} // end if
		return sDAO;
	}
	
	/**
	 * 교수 로그인 정보를 가져오기 위한 DAO
	 * @param lVO
	 * @return
	 * @throws SQLException
	 */
	public List<ScoreVO> slctAllScore() throws SQLException {

		DbConnection dbCon = DbConnection.getInstance();
		ScoreVO sVO = null;
		List<ScoreVO> list = new ArrayList<ScoreVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			String id = "scott";
			String pass = "tiger";
			con = dbCon.getConnection(id, pass);

			String selectQuery = "SELECT d.dept_name, s.std_name, sc.score"
					+ "FROM dept d\r\n"
					+ "JOIN student s ON d.dept_code = s.dept_code"
					+ "JOIN register r ON s.std_number = r.std_number"
					+ "JOIN score sc ON r.register_number = sc.register_number;";
			pstmt = con.prepareStatement(selectQuery);

			// 5. 쿼리 실행 및 결과 처리
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sVO = new ScoreVO(rs.getInt("DEPT_NAME"), rs.getString("STD_NAME"), rs.getInt("SCORE"));

				 System.out.println(sVO);

				list.add(sVO);

			}

		} finally {

			dbCon.dbClose(null, pstmt, con);
		} // end finally

		return list;

	}// slctAllDept
	
	
}

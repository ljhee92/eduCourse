package eduCourse_prj.student.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eduCourse_prj.DbConnection;
import eduCourse_prj.VO.CrsRegVO;
import eduCourse_prj.VO.LectureVO;
import eduCourse_prj.VO.RegVO;

public class CrsRegDAO {

	private static CrsRegDAO crDAO;

	private CrsRegDAO() {
	}

	public static CrsRegDAO getInstance() {
		if (crDAO == null) {
			crDAO = new CrsRegDAO();
		} // end if
		return crDAO;
	} // getInstance

	/**
	 * DB에서 로그인한 학생의 수강신청 가능 과목을 가져오는 method
	 * 
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
			selectAllCrsReg.append(
					"select d.dept_name, c.course_name, c.course_code, l.lect_room, l.capacity, c.credit_hours ")
					.append("from dept d ").append("join student s on s.dept_code = d.dept_code ")
					.append("right outer join course c on c.dept_code = d.dept_code ")
					.append("join lecture l on l.course_code = c.course_code ")
					.append("where s.std_number = ? and l.lect_delete_flag = 'N'").append("order by c.course_code");

			pstmt = con.prepareStatement(selectAllCrsReg.toString());
			pstmt.setInt(1, stdnt_number);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				crVO = new CrsRegVO(rs.getString("dept_name"), rs.getString("course_name"), rs.getString("course_code"),
						rs.getString("lect_room"), rs.getInt("capacity"), rs.getInt("credit_hours"));
				listCrsRegVO.add(crVO);
			} // end while
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		} // end finally

		return listCrsRegVO;
	} // slctAllCrsReg

	/**
	 * DB에서 수강신청 가능 과목 중 한 과목을 가져오는 method
	 * 
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
			selectOneCrsReg.append(
					"select d.dept_name, c.course_name, c.course_code, l.lect_room, l.capacity, c.credit_hours ")
					.append("from dept d ").append("join course c on c.dept_code = d.dept_code ")
					.append("join lecture l on l.course_code = c.course_code ").append("where c.course_code = ?");

			pstmt = con.prepareStatement(selectOneCrsReg.toString());
			pstmt.setString(1, crs_code);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				crVO = new CrsRegVO(rs.getString("dept_name"), rs.getString("course_name"), rs.getString("course_code"),
						rs.getString("lect_room"), rs.getInt("capacity"), rs.getInt("credit_hours"));
			} // end if
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		} // end finally

		return crVO;
	} // slctOneCrsReg

	/**
	 * 수강바구니에 담긴 과목을 DB의 수강 테이블에 추가하는 method
	 * 
	 * @param rVO
	 * @throws SQLException
	 */
	public void insertReg(List<RegVO> listRVO) throws SQLException {
		DbConnection dbCon = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;

		try {
			String id = "scott";
			String pass = "tiger";
			con = dbCon.getConnection(id, pass);

			// 수강식별번호 확인
			int reg_number = 0, lastRegNum = 0;
			String checkRegNum = "";

			for (RegVO rVO : listRVO) {
				checkRegNum = "select max(register_number) from register";

				pstmt = con.prepareStatement(checkRegNum);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					// 수강식별번호가 존재한다면 가장 마지막번호+1로 설정
					lastRegNum = rs.getInt(1);
					reg_number = lastRegNum + 1;
				} // end if

				StringBuilder insertReg = new StringBuilder();
				// 수강식별번호가 존재하지 않는다면 0으로 설정
				insertReg.append("insert into register(register_number, std_number, prof_number, course_code) ")
						.append("values(?, ?, (select prof_number from lecture where course_code = ?), ?)");

				pstmt2 = con.prepareStatement(insertReg.toString());

				pstmt2.setInt(1, reg_number);
				pstmt2.setInt(2, rVO.getStdnt_number());
				pstmt2.setString(3, rVO.getCourse_code());
				pstmt2.setString(4, rVO.getCourse_code());

				pstmt2.executeUpdate();
			} // end for
		} finally {
			dbCon.dbClose(rs, pstmt, con);
			dbCon.dbClose(null, pstmt2, con);
		} // end finally
	} // insertReg

	/**
	 * 수강바구니에 담긴 한과목을 DB의 수강 테이블에 추가하는 method
	 * 
	 * @param rVO
	 * @throws SQLException
	 */
	public void insertReg(RegVO rVO) throws SQLException {
		DbConnection dbCon = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;

		try {
			String id = "scott";
			String pass = "tiger";
			con = dbCon.getConnection(id, pass);

			// 수강식별번호 확인
			int reg_number = 0, lastRegNum = 0;
			String checkRegNum = "select max(register_number) from register";

			pstmt = con.prepareStatement(checkRegNum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// 수강식별번호가 존재한다면 가장 마지막번호+1로 설정
				lastRegNum = rs.getInt(1);
				reg_number = lastRegNum + 1;
			} // end if

			StringBuilder insertReg = new StringBuilder();
			// 수강식별번호가 존재하지 않는다면 0으로 설정
			insertReg.append("insert into register(register_number, std_number, prof_number, course_code) ")
					.append("values(?, ?, (select prof_number from lecture where course_code = ?), ?)");

			pstmt2 = con.prepareStatement(insertReg.toString());

			pstmt2.setInt(1, reg_number);
			pstmt2.setInt(2, rVO.getStdnt_number());
			pstmt2.setString(3, rVO.getCourse_code());
			pstmt2.setString(4, rVO.getCourse_code());

			pstmt2.executeUpdate();

		} finally {
			dbCon.dbClose(rs, pstmt, con);
			dbCon.dbClose(null, pstmt2, con);
		} // end finally
	} // insertReg

	/**
	 * 로그인한 학생의 수강 신청 내역 추가를 위해<br>
	 * DB 수강, 강의, 과목 테이블에 있는 정보를 가져오는 method
	 * 
	 * @return
	 */
	public List<RegVO> slctAllReg(int stdnt_number) throws SQLException {
		List<RegVO> listRegVO = new ArrayList<RegVO>();
		RegVO rVO = null;
		DbConnection dbCon = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String id = "scott";
			String pass = "tiger";

			con = dbCon.getConnection(id, pass);

			StringBuilder selectAllReg = new StringBuilder();
			selectAllReg.append(
					"select distinct r.register_number, d.dept_name, c.course_name, c.course_code, l.lect_room, l.capacity, ")
					.append("c.credit_hours, p.prof_name, r.std_number, r.prof_number ").append("from register r ")
					.append("join lecture l on l.course_code = r.course_code ")
					.append("join professor p on p.prof_number = l.prof_number ")
					.append("join course c on c.course_code = l.course_code ")
					.append("join dept d on d.dept_code = c.dept_code ")
					.append("where std_number = ? and l.lect_delete_flag = 'N'").append("order by c.course_code");

			pstmt = con.prepareStatement(selectAllReg.toString());
			pstmt.setInt(1, stdnt_number);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rVO = new RegVO(rs.getString("dept_name"), rs.getString("course_name"), rs.getString("lect_room"),
						rs.getInt("capacity"), rs.getInt("credit_hours"), rs.getString("prof_name"),
						rs.getInt("std_number"), rs.getInt("prof_number"), rs.getString("course_code"));
				listRegVO.add(rVO);
			} // end while
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		} // end finally

		return listRegVO;
	} // slctAllReg

	/**
	 * 수강신청한 과목의 수강인원을 증가시키는 method
	 * 
	 * @param course_code
	 * @return
	 * @throws SQLException
	 */
	public void updateCapacited(List<LectureVO> listLtVO) throws SQLException {
		DbConnection dbCon = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;

		try {
			String id = "scott";
			String pass = "tiger";
			con = dbCon.getConnection(id, pass);

			// 수강인원 확인
			int capacited = 1, lastCapacited = 0;
			String checkCapacited = "", updateCapacited = "";

			for (LectureVO ltVO : listLtVO) {
				checkCapacited = "select max(capacited) from lecture where course_code = ?";
				pstmt = con.prepareStatement(checkCapacited);

				pstmt.setString(1, ltVO.getCourse_code());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					// 수강인원이 존재한다면 가장 마지막번호+1로 설정
					lastCapacited = rs.getInt(1);
					capacited = lastCapacited + 1;
				} // end if

				// 수강식별번호가 존재하지 않는다면 1로 설정
				updateCapacited = "update lecture set capacited = ? where course_code = ?";
				pstmt2 = con.prepareStatement(updateCapacited);

				pstmt2.setInt(1, capacited);
				pstmt2.setString(2, ltVO.getCourse_code());

				pstmt2.executeUpdate();
			} // end for
		} finally {
			dbCon.dbClose(rs, pstmt, con);
			dbCon.dbClose(null, pstmt2, con);
		} // end finally
	} // updateCapacited

	/**
	 * 수강신청한 한과목의 수강인원을 증가시키는 method
	 * 
	 * @param course_code
	 * @return
	 * @throws SQLException
	 */
	public void updateCapacited(LectureVO lVO) throws SQLException {
		DbConnection dbCon = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;

		try {
			String id = "scott";
			String pass = "tiger";
			con = dbCon.getConnection(id, pass);

			// 수강인원 확인
			int capacited = 1, lastCapacited = 0;
			String checkCapacited = "", updateCapacited = "";

			checkCapacited = "select max(capacited) from lecture where course_code = ?";
			pstmt = con.prepareStatement(checkCapacited);

			pstmt.setString(1, lVO.getCourse_code());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// 수강인원이 존재한다면 가장 마지막번호+1로 설정
				lastCapacited = rs.getInt(1);
				capacited = lastCapacited + 1;
			} // end if

			// 수강식별번호가 존재하지 않는다면 1로 설정
			updateCapacited = "update lecture set capacited = ? where course_code = ?";
			pstmt2 = con.prepareStatement(updateCapacited);

			pstmt2.setInt(1, capacited);
			pstmt2.setString(2, lVO.getCourse_code());

			pstmt2.executeUpdate();

		} finally {
			dbCon.dbClose(rs, pstmt, con);
			dbCon.dbClose(null, pstmt2, con);
		} // end finally
	} // updateCapacited

	/**
	 * 해당 과목을 수강신청시 수강가능인원 - 수강중인 인원 체크 method
	 * 
	 * @throws SQLException
	 */
	public boolean checkCapacited(LectureVO lVO) throws SQLException {

		boolean check = false;
		DbConnection dbCon = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;

		try {
			String id = "scott";
			String pass = "tiger";
			con = dbCon.getConnection(id, pass);

			String checkQuery = " select CAPACITY-CAPACITED as checkAble	" + " from  LECTURE	"
					+ " where COURSE_CODE =  ?  ";

			pstmt = con.prepareStatement(checkQuery);

			pstmt.setString(1, lVO.getCourse_code());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt("checkAble") > 0) {

					check = true;
				}

			} // end if

		} finally {
			dbCon.dbClose(rs, pstmt, con);

		} // end finally

		return check;

	}

} // CrsRegDAO
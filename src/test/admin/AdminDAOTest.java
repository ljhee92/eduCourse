package test.admin;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import eduCourse_prj.VO.LoginVO;
import eduCourse_prj.admin.dao.AdminDAO;

class AdminDAOTest {

	@DisplayName("로그인 결과")
    @Test
	void AdminLoginValidTest() throws SQLException {
		// @Disabled
	      
	      
	    // given 배경 지식 : 뭘 줬냐!
	    AdminDAO lDAO = AdminDAO.getInstance();
	    // when
	    LoginVO lVO = new LoginVO("adminTest", "qqqq");
	    LoginVO lrVO = lDAO.adminLogin(lVO);
	    String name = lrVO.getName();
	    // then 결과 : 맞냐 틀리냐 같냐 안같냐
	    // assertNotNull(lrVO);
		assertEquals(name, "예찬");//같은지?
		
		// given 배경 지식 : 뭘 줬냐!
		AdminDAO lDAO2 = AdminDAO.getInstance();
		// when
		LoginVO lVO2 = new LoginVO("admin", "master");
		LoginVO lrVO2 = lDAO.adminLogin(lVO2);
		String name2 = lrVO2.getName();
		// then 결과 : 맞냐 틀리냐 같냐 안같냐
		// assertNotNull(lrVO);
		assertEquals(name2, "손지민");//같은지?
	  }// testSelectLogin
	

}

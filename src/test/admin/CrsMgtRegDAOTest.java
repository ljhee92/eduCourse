package test.admin;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import eduCourse_prj.VO.CrsRegVO;
import eduCourse_prj.VO.CrsVO;
import eduCourse_prj.VO.LoginVO;
import eduCourse_prj.admin.dao.AdminDAO;
import eduCourse_prj.professor.dao.CrsMgtRegDAO;

class CrsMgtRegDAOTest {

	@Test
	void testSlctProfOneLect() {
		// given
		CrsMgtRegDAO cmrDAO = CrsMgtRegDAO.getInstance();
		String courseName = "시험등록 test2"; // 테스트할 강의 과목명

		// when
		try {
			CrsRegVO result = cmrDAO.slctProfOneLect(courseName);

			// then
			assertNotNull(result); // 조회 결과가 null이 아닌지 확인
			assertEquals(courseName, result.getCourse_name()); // 조회한 과목명과 예상한 과목명이 일치하는지 확인
			assertEquals(7, result.getCapacity());
		} catch (SQLException e) {
			fail("예외가 발생하였습니다: " + e.getMessage());
		}
	}//testSlctProfOneLect
	
	@DisplayName("교수의 강의 과목 조회 테스트 - 배열")
	@Test
	void testSlctProfLect() throws SQLException{
		// given
		CrsMgtRegDAO cmrDAO = CrsMgtRegDAO.getInstance();
		int prof_number = 100403140; // 테스트에 맞는 교수 번호 설정

		// when
		List<CrsVO> actualCourses = cmrDAO.slctProfLect(prof_number);

		 //System.out.println(actualCourses.get(0));
		 //System.out.println( actualCourses.toArray(new CrsVO[0]) );
		// then
		
		CrsVO[] expectedCourses = new CrsVO[] 
				{ 
				new CrsVO("M0001", "MATH_1", "첫 학과", 1),
				new CrsVO("M0002", "MATH_2", "첫 학과", 1),
				new CrsVO("X0101", "시험등록testtest", "첫 학과", 1),
				new CrsVO("q2323", "교수삭제", "첫 학과", 1),
				new CrsVO("X1234", "시험등록 test", "첫 학과", 1),
				new CrsVO("T6565", "시험테스트", "첫 학과", 1),
				new CrsVO("T0011", "T1", "첫 학과", 1),
				new CrsVO("T0012", "T2", "첫 학과", 1),
				new CrsVO("T0013", "T3", "첫 학과", 1)
				// 추가적으로 예상되는 결과들을 여기에 배열 형태로 추가할 수 있습니다.
				
		};
		List<CrsVO> expectedCourses1 = new ArrayList<CrsVO>();
		expectedCourses1.add(new CrsVO("M0001", "MATH_1", "첫 학과", 1));
		expectedCourses1.add(new CrsVO("M0002", "MATH_2", "첫 학과", 1));
		expectedCourses1.add(new CrsVO("X0101", "시험등록testtest", "첫 학과", 1));
		expectedCourses1.add(new CrsVO("q2323", "교수삭제", "첫 학과", 1));
		expectedCourses1.add(new CrsVO("X1234", "시험등록 test", "첫 학과", 1));
		expectedCourses1.add(new CrsVO("T6565", "시험테스트", "첫 학과", 1));
		expectedCourses1.add(new CrsVO("T0011", "T1", "첫 학과", 1));
		expectedCourses1.add(new CrsVO("T0012", "T2", "첫 학과", 1));
		expectedCourses1.add(new CrsVO("T0013", "T3", "첫 학과", 1));

		List<CrsVO> expectedCourses2 = new ArrayList<CrsVO>();
		expectedCourses2.add(new CrsVO("M0001", "MATH_1", "첫 학과", 1));
		expectedCourses2.add(new CrsVO("M0002", "MATH_2", "첫 학과", 1));
		expectedCourses2.add(new CrsVO("X0101", "시험등록testtest", "첫 학과", 1));
		expectedCourses2.add(new CrsVO("q2323", "교수삭제", "첫 학과", 1));
		expectedCourses2.add(new CrsVO("X1234", "시험등록 test", "첫 학과", 1));
		expectedCourses2.add(new CrsVO("T6565", "시험테스트", "첫 학과", 1));
		expectedCourses2.add(new CrsVO("T0011", "T1", "첫 학과", 1));
		expectedCourses2.add(new CrsVO("T0012", "T2", "첫 학과", 1));
		expectedCourses2.add(new CrsVO("T0013", "T3", "첫 학과", 1));
		
//		assertArrayEquals(expectedCourses1.toArray(), actualCourses.toArray());
//		System.out.println("ddddddddddddd");
		//System.out.println((expectedCourses1.get(0)).equals(expectedCourses2.get(0)));
		
		assertArrayEquals(expectedCourses1.toArray(), expectedCourses2.toArray());
		
//		for(int i = 0; i < actualCourses.size(); i++) {
//			actualCourses.get(0).getDeptName().equals(expectedCourses[0].getDeptName());
//		}
//		assertThat(expectedCourses).
// 	
		
		
		
		
		
		
		
		//System.out.println(actualCourses.get(0).toString());
		//System.out.println(expectedCourses[0].toString());
		//System.out.println(expectedCourses[0].toString().equals(actualCourses.get(0).toString()));
//		assertArrayEquals(expectedCourses,actualCourses.toArray()) ;
//		Assertions.assertThat(actualCourses).isEqualTo(expectedCourses);
		//assertTrue(Arrays.equals(expectedCourses, actualCourses.toArray()));
//		assertEquals(Arrays.asList(expectedCourses), actualCourses);
		//System.out.println(actualCourses.get(0).hashCode());
		//System.out.println(expectedCourses[0].hashCode());
	}

	
	
	
	
	@Test
	void testArrEquals() {
		String[] expectedArray = {"apple", "banana", "orange"};
		
		//System.out.println(expectedArray.hashCode());
		
		
		String[] actualArray = {"apple", "banana", "orange"};
		//System.out.println(actualArray.hashCode());
		//System.out.println(expectedArray + " " + actualArray);
		
		assertArrayEquals(expectedArray, actualArray); // 두 배열이 동일하므로 테스트 통과
	}

	
	
	
	
	
}//class

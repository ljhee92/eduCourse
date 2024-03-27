package eduCourse_prj.VO;

public class TestQustVO {
	private int test_qust_id;
	private int qust_number;
	private String qust_content;
	private int answer;
	private int prof_number;
	private String crs_code;

	public TestQustVO(int qust_number, String qust_content, int answer, int prof_number, String crs_code) {
		super();
		this.qust_number = qust_number;
		this.qust_content = qust_content;
		this.answer = answer;
		this.prof_number = prof_number;
		this.crs_code = crs_code;
	}

	public int getTest_qust_id() {
		return test_qust_id;
	}

	public int getQust_number() {
		return qust_number;
	}

	public String getQust_content() {
		return qust_content;
	}

	public int getAnswer() {
		return answer;
	}

	public int getProf_number() {
		return prof_number;
	}

	public String getCrs_code() {
		return crs_code;
	}

}

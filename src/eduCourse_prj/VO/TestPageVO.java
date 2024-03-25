package eduCourse_prj.VO;

public class TestPageVO {
	
	private int question_number;
	private String question_content;
	
	public TestPageVO() {
	}

	public TestPageVO(int question_number, String question_content) {
		this.question_number = question_number;
		this.question_content = question_content;
	}

	public int getQuestion_number() {
		return question_number;
	}

	public String getQuestion_content() {
		return question_content;
	}

	@Override
	public String toString() {
		return "TestPageVO [question_number=" + question_number + ", question_content=" + question_content + "]";
	}

} // class
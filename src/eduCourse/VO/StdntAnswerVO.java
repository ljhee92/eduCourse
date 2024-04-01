package eduCourse.VO;

public class StdntAnswerVO {

	private int register_number;
	private int question_number;
	private String std_answer;
	private String answer;
	private String course_code;

	public StdntAnswerVO() {
	}

	public StdntAnswerVO(int register_number, int question_number, String std_answer, String course_code) {
		this.register_number = register_number;
		this.question_number = question_number;
		this.std_answer = std_answer;
		this.course_code = course_code;
	}

	public StdntAnswerVO(int question_number, String std_answer, String answer) {
		super();
		this.question_number = question_number;
		this.std_answer = std_answer;
		this.answer = answer;
	}

	public int getRegister_number() {
		return register_number;
	}

	public int getQuestion_number() {
		return question_number;
	}

	public String getStd_answer() {
		return std_answer;
	}

	public String getCourse_code() {
		return course_code;
	}

	public String getAnswer() {
		return answer;
	}

	@Override
	public String toString() {
		return "StdntAnswerVO [register_number=" + register_number + ", question_number=" + question_number
				+ ", std_answer=" + std_answer + ", course_code=" + course_code + "]";
	}

} // class
package eduCourse.VO;

public class TestPageVO {

	private int question_number;
	private String question_content;
	private String question_split_content;
	private String option1, option2, option3, option4;
	private String answer;

	public TestPageVO() {
	}

	public TestPageVO(int question_number, String question_content, String answer) {
		this.question_number = question_number;
		this.question_content = question_content;
		this.answer = answer;
		splitContent();
	}

	private void splitContent() {
		String[] contents = this.question_content.split("\n\n");
		this.question_split_content = contents[0];

		String option = contents[1];
		String[] options = option.split("\t");
		this.option1 = options[0];
		this.option2 = options[1];
		this.option3 = options[2];
		this.option4 = options[3];
	} // splitContent

	public int getQuestion_number() {
		return question_number;
	}

	public String getQuestion_content() {
		return question_content;
	}

	public String getQuestion_split_content() {
		return question_split_content;
	}

	public String getOption1() {
		return option1;
	}

	public String getOption2() {
		return option2;
	}

	public String getOption3() {
		return option3;
	}

	public String getOption4() {
		return option4;
	}

	public String getAnswer() {
		return answer;
	}

	@Override
	public String toString() {
		return "TestPageVO [question_number=" + question_number + ", question_content=" + question_content
				+ ", question_split_content=" + question_split_content + ", option1=" + option1 + ", option2=" + option2
				+ ", option3=" + option3 + ", option4=" + option4 + ", answer=" + answer + "]";
	}

} // class
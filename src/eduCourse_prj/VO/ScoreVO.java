package eduCourse_prj.VO;

public class ScoreVO {
	private int reg_number, score, std_num, prof_id;
	
	private String std_name, dept_name, test_date;
	
	public ScoreVO(int reg_number, int score) {
		super();
		this.reg_number = reg_number;
		this.score = score;
	}
	
	public ScoreVO(int reg_number,String stdnt_name, int score) {
		super();
		this.reg_number = reg_number;
		this.std_name = stdnt_name;
		this.score = score;
	}
	
	
	
	public ScoreVO(int reg_number, int score, String test_date) {
		this.reg_number = reg_number;
		this.score = score;
		this.test_date = test_date;
	}

	public ScoreVO( int stdnt_num, String stdnt_name, String dept_name, int score) {//결과 반환
		super();
		this.score = score;
		this.std_num = stdnt_num;
		this.std_name = stdnt_name;
		this.dept_name = dept_name;
	}
	public ScoreVO(int reg_number, int score, int stdnt_num, String stdnt_name, int prof_id) {
		super();
		this.reg_number = reg_number;
		this.score = score;
		this.std_num = stdnt_num;
		this.std_name = stdnt_name;
		this.prof_id = prof_id;
	}

	public int getReg_number() {
		return reg_number;
	}

	public int getScore() {
		return score;
	}

	public int getStd_num() {
		return std_num;
	}

	public int getProf_id() {
		return prof_id;
	}

	public String getStd_name() {
		return std_name;
	}

	public String getDept_name() {
		return dept_name;
	}

	public String getTest_date() {
		return test_date;
	}


	
	
}

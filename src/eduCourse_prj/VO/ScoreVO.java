package eduCourse_prj.VO;

public class ScoreVO {
	private int reg_number, score;
	
	private String test_date, stdnt_name;
	public ScoreVO(int reg_number, int score) {
		super();
		this.reg_number = reg_number;
		this.score = score;
	}
	public ScoreVO(int reg_number,String stdnt_name, int score) {
		super();
		this.reg_number = reg_number;
		this.stdnt_name = stdnt_name;
		this.score = score;
	}
	public int getReg_number() {
		return reg_number;
	}
	public int getScore() {
		return score;
	}
	public String getStdnt_name() {
		return stdnt_name;
	}
	
	
}

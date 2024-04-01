package eduCourse.VO;

public class CrsVO {
	private String courCode;// 과목코드
	private String courName;// 과목명
	private int creditHour;// 학점
	private String courInputDate;// 입력일
	private String courDelFlag;// 삭제플레그
	private int deptCode;// 학과번호
	private String deptName;

	public CrsVO() {
		super();
	}

	public CrsVO(String courCode, String courName) {
		super();
		this.courCode = courCode;
		this.courName = courName;
	}

	public CrsVO(String courCode, String courName, String deptName) {
		super();
		this.courCode = courCode;
		this.courName = courName;
		this.deptName = deptName;
	}

	public CrsVO(String courCode, String courName, String deptName, int deptCode) {
		super();
		this.courCode = courCode;
		this.courName = courName;
		this.deptName = deptName;
		this.deptCode = deptCode;
	}

	public CrsVO(String courCode, String courName, int creditHour, int deptCode) {
		super();
		this.courCode = courCode;
		this.courName = courName;
		this.creditHour = creditHour;
		this.deptCode = deptCode;
	}

	public CrsVO(String courCode, String courName, int creditHour, String courInputDate, String courDelFlag,
			int deptCode) {
		super();
		this.courCode = courCode;
		this.courName = courName;
		this.creditHour = creditHour;
		this.courInputDate = courInputDate;
		this.courDelFlag = courDelFlag;
		this.deptCode = deptCode;
	}

	public CrsVO(String courCode, String courName, int creditHour, int deptCode, String deptName) {
		super();
		this.courCode = courCode;
		this.courName = courName;
		this.creditHour = creditHour;
		this.deptCode = deptCode;
		this.deptName = deptName;
	}

	public CrsVO(String courCode, String courName, int creditHour, String courInputDate, String courDelFlag,
			int deptCode, String deptName) {
		super();
		this.courCode = courCode;
		this.courName = courName;
		this.creditHour = creditHour;
		this.courInputDate = courInputDate;
		this.courDelFlag = courDelFlag;
		this.deptCode = deptCode;
		this.deptName = deptName;
	}

	public CrsVO(String courCode, String courName, int creditHour) {
		super();
		this.courCode = courCode;
		this.courName = courName;
		this.creditHour = creditHour;
	}

	public String getDeptName() {
		return deptName;
	}

	public String getCourCode() {
		return courCode;
	}

	public String getCourName() {
		return courName;
	}

	public int getCreditHour() {
		return creditHour;
	}

	public String getCourInputDate() {
		return courInputDate;
	}

	public String getCourDelFlag() {
		return courDelFlag;
	}

	public int getDeptCode() {
		return deptCode;
	}

	@Override
	public String toString() {
		return "CrsVO [courCode=" + courCode + ", courName=" + courName + ", creditHour=" + creditHour
				+ ", courInputDate=" + courInputDate + ", CourDelFlag=" + courDelFlag + ", deptCode=" + deptCode
				+ ", deptName=" + deptName + "]";
	}

}

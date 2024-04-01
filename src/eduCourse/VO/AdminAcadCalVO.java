package eduCourse.VO;

public class AdminAcadCalVO {

	private String memo;
	private String yearMonthDay;

	public AdminAcadCalVO() {
	}

	public AdminAcadCalVO(String memo, String yearMonthDay) {
		super();
		this.memo = memo;
		this.yearMonthDay = yearMonthDay;
	}

	public String getMemo() {
		return memo;
	}

	public String getYearMonthDay() {
		return yearMonthDay;
	}

}

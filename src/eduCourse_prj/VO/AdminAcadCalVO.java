package eduCourse_prj.VO;

public class AdminAcadCalVO {
	   private int year;
	    private int month;
	    private int day;
	    private String memo;

	    public AdminAcadCalVO(int year, int month, int day,String memo) {
			this.year = year;
			this.month = month;
			this.day = day;
			this.memo = memo;
		}

		// 각 필드의 Getter 및 Setter 메서드
	    public int getYear() {
	        return year;
	    }

	    public void setYear(int year) {
	        this.year = year;
	    }

	    public int getMonth() {
	        return month;
	    }

	    public void setMonth(int month) {
	        this.month = month;
	    }

	    public int getDay() {
	        return day;
	    }

	    public void setDay(int day) {
	        this.day = day;
	    }

	    public String getMemo() {
	        return memo;
	    }

	    public void setMemo(String memo) {
	        this.memo = memo;
	    }
}

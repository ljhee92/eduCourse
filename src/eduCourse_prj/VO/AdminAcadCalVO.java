package eduCourse_prj.VO;

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
		public void setMemo(String memo) {
			this.memo = memo;
		}
		public String getYearMonthDay() {
			return yearMonthDay;
		}
		public void setYearMonthDay(String yearMonthDay) {
			this.yearMonthDay = yearMonthDay;
		}
	    
	    
}

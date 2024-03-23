package eduCourse_prj.VO;

public class LectureVO {
	
	private int prof_number;
	private String course_code;
	private int capacity;
	private int capacited;
	private String lect_delete_flag;
	private String lect_room;
	
	public LectureVO() {
	}
	
	public LectureVO(String course_code) {
		this.course_code = course_code;
	}
	

	public LectureVO(int prof_number, String course_code, int capacity, int capacited, String lect_delete_flag,
			String lect_room) {
		this.prof_number = prof_number;
		this.course_code = course_code;
		this.capacity = capacity;
		this.capacited = capacited;
		this.lect_delete_flag = lect_delete_flag;
		this.lect_room = lect_room;
	}

	public int getProf_number() {
		return prof_number;
	}

	public String getCourse_code() {
		return course_code;
	}

	public int getCapacity() {
		return capacity;
	}

	public int getCapacited() {
		return capacited;
	}

	public String getLect_delete_flag() {
		return lect_delete_flag;
	}

	public String getLect_room() {
		return lect_room;
	}

	@Override
	public String toString() {
		return "LectureVO [prof_number=" + prof_number + ", course_code=" + course_code + ", capacity=" + capacity
				+ ", capacited=" + capacited + ", lect_delete_flag=" + lect_delete_flag + ", lect_room=" + lect_room
				+ "]";
	}
	
} // class
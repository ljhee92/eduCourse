package eduCourse.VO;

public class LectureRoomVO {

	private String lect_room_num;
	private int dept_code;

	public LectureRoomVO() {
	}

	public LectureRoomVO(String lect_room_num) {
		this.lect_room_num = lect_room_num;
	}

	public LectureRoomVO(String lect_room_num, int dept_code) {
		this.lect_room_num = lect_room_num;
		this.dept_code = dept_code;
	}

	public String getLect_room_num() {
		return lect_room_num;
	}

	public int getDept_code() {
		return dept_code;
	}

	@Override
	public String toString() {
		return "LectureRoomVO [lect_room_num=" + lect_room_num + ", dept_code=" + dept_code + "]";
	}

} // class
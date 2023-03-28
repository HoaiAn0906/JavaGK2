package entity;

import java.util.Date;

public class Section {
	private String sectionNo;
	private String semester;
	private int year;
	private Date startDate;
	private Date endDate;
	private String day;
	private String time;
	private String room;
	private int seats;
	private Instructor instructor;
	public Section(String sectionNo, String semester, int year, Date startDate, Date endDate, String day, String time,
			String room, int seats, Instructor instructor) {
		super();
		this.sectionNo = sectionNo;
		this.semester = semester;
		this.year = year;
		this.startDate = startDate;
		this.endDate = endDate;
		this.day = day;
		this.time = time;
		this.room = room;
		this.seats = seats;
		this.instructor = instructor;
	}
	public Section() {
		// TODO Auto-generated constructor stub
	}
	public String getSectionNo() {
		return sectionNo;
	}
	public void setSectionNo(String sectionNo) {
		this.sectionNo = sectionNo;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	public Instructor getInstructor() {
		return instructor;
	}
	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}
	@Override
	public String toString() {
		return "Section [sectionNo=" + sectionNo + ", semester=" + semester + ", year=" + year + ", startDate="
				+ startDate + ", endDate=" + endDate + ", day=" + day + ", time=" + time + ", room=" + room + ", seats="
				+ seats + ", instructor=" + instructor + "]";
	}
	
}

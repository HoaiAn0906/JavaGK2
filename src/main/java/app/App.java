package app;

import dao.CoursesDao;

public class App {
	public static void main(String[] args) {
		CoursesDao coursesDao = new CoursesDao();
		coursesDao.getCourses("Biology").forEach(c -> System.out.println(c));
//		coursesDao.getNumberOfCoursesByInstructor().entrySet().forEach(e->System.out.println(e.getKey()+" : "+e.getValue()));
//		System.out.println(coursesDao.updateTheRoomOfSection("BIO101", "BIO101-01", "A3"));
	}
}

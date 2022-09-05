package assignment5;

public class init {
	public static void Init_Program() {
		management.students.add(new student("유동연", "dongs0125", "1234"));
		management.students.add(new student("장현웅", "sas1200", "1234")); //학생 선언
		
		management.professors.add(new professor("이미연", "mylee", "1234"));
		management.professors.add(new professor("이정원", "jungwony", "1234"));
		management.professors.add(new professor("이세돌", "saedol", "1234")); //교수님 선언
		
		management.professors.get(0).register_lecture("전프", "월B목B");
		management.professors.get(0).register_lecture("전프", "수B금B");
		
		management.professors.get(1).register_lecture("자료구조", "화A금A");
		management.professors.get(1).register_lecture("자료구조", "화C금C");
		
		management.professors.get(2).register_lecture("C++", "월B금B");
		management.professors.get(2).register_lecture("Python", "화C금C"); //교수님의 강의 목록 선언
		
		
	} 
}

package assignment3;

public class Main {
	public static void main(String[] args) {
		Museum m = new Museum(); //박물관 선언
		Adult v1 = new Adult(25, 5000); //25살에 5천원의 입장료를 가진 어른 선언
		Member v2 = new Member(30);     //30살에 회원 선언
		Student v3 = new Student(14, 1000);  //14살에 1천원의 입장료를 가진 학생 선언
		Child v4 = new Child(6);             //6살 어린이 선언
		Student v5 = new Student(18, 5000);  //18살에 5천원의 입장료를 가진 학생 선언
		Student v6 = new Student(15, 2000);  //15살에 2천원의 입장료를 가진 학생 선언
		Child v7 = new Child(8);             //8살 어린이 선언
		
		m.enter(v1); //어른 입장
		m.enter(v2); //회원 입장
		m.enter(v3); //학생 입장료 제한 확인
		m.enter(v4); //어린이 입장
		m.enter(v5); //학생 나이 제한 확인
		
		m.exit(v1); //어른 퇴장
		m.exit(v3); //입장 못한 사람 퇴장 확인
		m.enter(v6); //학생 입장
		m.enter(v7); //어린이 나이 제한 확인
	}

}
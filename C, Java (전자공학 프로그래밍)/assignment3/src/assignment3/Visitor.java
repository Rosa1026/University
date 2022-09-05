package assignment3;

interface Common {
	int getAge();
	boolean getStatus();
} //Visitor 함수에서 사용하기 전에 common interface 선언

public abstract class Visitor implements Common{
	int age;        //나이 변수 선언
	boolean status; //상태 변수 선언
	final static int STUDENT_FEE = 2000; //학생의 입장료 2천원으로 고정
	final static int ADULT_FEE = 5000; //어른의 입장료 5천원으로 고정
	
	public abstract boolean pay(); //돈을 냈는지 안 냈는지
	public abstract boolean isMember(); //회원인지 아닌지
	
	public int getAge() {
		return age; //age 반환
	}
	public boolean getStatus() {
		return this.status; //status 반환
	}

}

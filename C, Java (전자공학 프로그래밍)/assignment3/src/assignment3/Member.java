package assignment3;

public class Member extends Visitor{
	boolean status;           //회원이라면 입장료가 0이므로 따로 변수 선언 X
	
	public Member(int age) {
		this.age = age;    
		this.status = false; 
	}
	
	public boolean pay() {
		return true;         //회원은 입장료를 내지않으므로 pay 항상 true 반환
	}
	
	public boolean isMember() {
		return true;         //회원이므로 true 반환
	}
}

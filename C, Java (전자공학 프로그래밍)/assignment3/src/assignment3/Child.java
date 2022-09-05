package assignment3;

public class Child extends Visitor{
	boolean status;               //입장료가 0이므로 입장했는지에 관련한 변수만 선언
	
	public Child(int age) {       //다른 것들과 다르게 나이만을 이용해 호출
		this.age = age;           //입력된 age값 저장
		this.status = false;      //입장 관련 변수 false로 초기화
	}
	
	public boolean pay() {
		return true;             //입장료가 0이므로 pay는 항상 true 반환
	}
	
	public boolean isMember() {  //회원으로 불러오지 않으므로 회원값 false
		return false;
	}
}

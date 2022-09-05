package assignment5;

public class member {
	private String name;
	private String id;
	private String pw;
	
	public member(String name, String id, String pw)
	{
		this.name = name;  //이름
		this.id = id;      //아이디
		this.pw = pw;      //비밀번호
	}
	
	public String get_id()
	{
		return this.id;    //아이디를 리턴 -> login 클래스 내에서 확인 위해 사용
	}
	
	public String get_pw()
	{
		return this.pw;    //비밀번호를 리턴 -> login 클래스 내에서 확인 위해 사용
	}
	
	public void change_pw(String pw) {
		this.pw = pw;     //매개변수 비밀번호를 저장
	}
	public String get_name()
	{
		return this.name; //이름을 리턴
	}

}

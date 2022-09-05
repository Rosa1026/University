package assignment5;

public class login {
	static String id, pw, name; //id, pw, name을 저장하기 위함
	
	public static boolean check_duplicate_id(String id) //아이디 중복을 확인하는 메쏘드
	{

		for(int i = 0; i < management.students.size(); i++)
		{
			if(id.equals(management.students.get(i).get_id())) //management에 있는 학생의 이름과 같으면
				return false;                                  //false를 반환
		}
		
		for(int i = 0; i < management.professors.size(); i++)  
		{
			if(id.equals(management.professors.get(i).get_id())) //management에 있는 교수님의 성함과 같으면
				return false;                                    //false를 반환
		}
		
		return true;                                             //그렇지 않다면 true 반환
	}
	
	public static member check_id_pw(String id, String pw)       //입력된 아이디와 비밀번호가 저장되어있는 아이디와 비밀번호와 맞는지를 확인하고 member 형을 반환하는 메소드
	{
		
		for(int i = 0; i < management.students.size(); i++)
		{
			if(id.equals(management.students.get(i).get_id()) && pw.equals(management.students.get(i).get_pw())) //학생의 아이디와 비밀번호가 매개변수로 들어온 값과 같다면
				return management.students.get(i);               //맞는 학생을 반환
		}
		
		for(int i = 0; i < management.professors.size(); i++)
		{
			if(id.equals(management.professors.get(i).get_id()) && pw.equals(management.professors.get(i).get_pw())) //교수님의 아이디와 비밀번호가 매개변수로 들어온 값과 같다면
				return management.professors.get(i);             //맞는 교수님을 반환
		}
		
		return null; //같은 사람이 없으므로 매개변수가 잘못 들어온 것이므로 null 반환
		
	}
	
}

package assignment5;

public class lecture {
	private String lecture_name;
	private String lecture_prof;
	private String lecture_time; //문자열을 저장하기 위해 선언
	private int time_num1;
	private int time_num2; //숫자 저장하기 위해 선언
	
	public lecture(String lecture_name, String professor, String time) {
		
		this.lecture_name = lecture_name;
		this.lecture_prof = professor;
		this.lecture_time = time; //메쏘드를 선언할 때 들어오는 매개변수를 저장
		time_num1 = day_to_int(time.substring(0,1)) * 5 + alphabet_to_int(time.substring(1,2)); 
		time_num2 = day_to_int(time.substring(2,3)) * 5 + alphabet_to_int(time.substring(3,4)); //매개변수 time의 substring을 보고 등록되어있는 요일과 교시를 저장
	}

	public String get_name()
	{
		return this.lecture_name; //강의 이름을 리턴
	}
	public String get_prof()
	{
		return this.lecture_prof; //강의를 하시는 교수님의 이름을 리턴
	}
	public String get_time()
	{
		return this.lecture_time; //강의 시간을 리턴
	}
	public int get_time1()
	{
		return this.time_num1; //time1을 리턴
	}
	public int get_time2()
	{
		return this.time_num2; //time2을 리턴
	}
	
	public static int day_to_int(String day) //매개변수 day를 숫자로 변경
	{
		if(day.equals("월"))
			return 0;
		else if(day.equals("화"))
			return 1;
		else if(day.equals("수"))
			return 2;
		else if(day.equals("목"))
			return 3;
		else if(day.equals("금"))
			return 4;
		else
		{
			System.out.println("잘못된 입력입니다. (월~금)으로 입력해주세요"); //강의 등록 시 월~금이 아닌 다른 요일 입력했을 시 출력 -> 이클립스 내에서 출력
			return -1;
		}
	}
	public static int alphabet_to_int(String alphabet) //교시를 숫자로 변경
	{
		if(alphabet.equals("A"))
			return 0;
		else if(alphabet.equals("B"))
			return 1;
		else if(alphabet.equals("C"))
			return 2;
		else if(alphabet.equals("D"))
			return 3;
		else if(alphabet.equals("E"))
			return 4;
		else
		{
			System.out.println("잘못된 입력입니다. (A~E)으로 입력해주세요"); //강의 등록 시 A~E교시가 아닌 다른 교시 입력했을 시 출력
			return -1;
		}
	}
}

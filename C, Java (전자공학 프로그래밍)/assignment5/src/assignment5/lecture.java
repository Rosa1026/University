package assignment5;

public class lecture {
	private String lecture_name;
	private String lecture_prof;
	private String lecture_time; //���ڿ��� �����ϱ� ���� ����
	private int time_num1;
	private int time_num2; //���� �����ϱ� ���� ����
	
	public lecture(String lecture_name, String professor, String time) {
		
		this.lecture_name = lecture_name;
		this.lecture_prof = professor;
		this.lecture_time = time; //�޽�带 ������ �� ������ �Ű������� ����
		time_num1 = day_to_int(time.substring(0,1)) * 5 + alphabet_to_int(time.substring(1,2)); 
		time_num2 = day_to_int(time.substring(2,3)) * 5 + alphabet_to_int(time.substring(3,4)); //�Ű����� time�� substring�� ���� ��ϵǾ��ִ� ���ϰ� ���ø� ����
	}

	public String get_name()
	{
		return this.lecture_name; //���� �̸��� ����
	}
	public String get_prof()
	{
		return this.lecture_prof; //���Ǹ� �Ͻô� �������� �̸��� ����
	}
	public String get_time()
	{
		return this.lecture_time; //���� �ð��� ����
	}
	public int get_time1()
	{
		return this.time_num1; //time1�� ����
	}
	public int get_time2()
	{
		return this.time_num2; //time2�� ����
	}
	
	public static int day_to_int(String day) //�Ű����� day�� ���ڷ� ����
	{
		if(day.equals("��"))
			return 0;
		else if(day.equals("ȭ"))
			return 1;
		else if(day.equals("��"))
			return 2;
		else if(day.equals("��"))
			return 3;
		else if(day.equals("��"))
			return 4;
		else
		{
			System.out.println("�߸��� �Է��Դϴ�. (��~��)���� �Է����ּ���"); //���� ��� �� ��~���� �ƴ� �ٸ� ���� �Է����� �� ��� -> ��Ŭ���� ������ ���
			return -1;
		}
	}
	public static int alphabet_to_int(String alphabet) //���ø� ���ڷ� ����
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
			System.out.println("�߸��� �Է��Դϴ�. (A~E)���� �Է����ּ���"); //���� ��� �� A~E���ð� �ƴ� �ٸ� ���� �Է����� �� ���
			return -1;
		}
	}
}

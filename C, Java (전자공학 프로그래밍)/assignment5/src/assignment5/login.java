package assignment5;

public class login {
	static String id, pw, name; //id, pw, name�� �����ϱ� ����
	
	public static boolean check_duplicate_id(String id) //���̵� �ߺ��� Ȯ���ϴ� �޽��
	{

		for(int i = 0; i < management.students.size(); i++)
		{
			if(id.equals(management.students.get(i).get_id())) //management�� �ִ� �л��� �̸��� ������
				return false;                                  //false�� ��ȯ
		}
		
		for(int i = 0; i < management.professors.size(); i++)  
		{
			if(id.equals(management.professors.get(i).get_id())) //management�� �ִ� �������� ���԰� ������
				return false;                                    //false�� ��ȯ
		}
		
		return true;                                             //�׷��� �ʴٸ� true ��ȯ
	}
	
	public static member check_id_pw(String id, String pw)       //�Էµ� ���̵�� ��й�ȣ�� ����Ǿ��ִ� ���̵�� ��й�ȣ�� �´����� Ȯ���ϰ� member ���� ��ȯ�ϴ� �޼ҵ�
	{
		
		for(int i = 0; i < management.students.size(); i++)
		{
			if(id.equals(management.students.get(i).get_id()) && pw.equals(management.students.get(i).get_pw())) //�л��� ���̵�� ��й�ȣ�� �Ű������� ���� ���� ���ٸ�
				return management.students.get(i);               //�´� �л��� ��ȯ
		}
		
		for(int i = 0; i < management.professors.size(); i++)
		{
			if(id.equals(management.professors.get(i).get_id()) && pw.equals(management.professors.get(i).get_pw())) //�������� ���̵�� ��й�ȣ�� �Ű������� ���� ���� ���ٸ�
				return management.professors.get(i);             //�´� �������� ��ȯ
		}
		
		return null; //���� ����� �����Ƿ� �Ű������� �߸� ���� ���̹Ƿ� null ��ȯ
		
	}
	
}

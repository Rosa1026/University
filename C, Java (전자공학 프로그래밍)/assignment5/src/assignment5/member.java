package assignment5;

public class member {
	private String name;
	private String id;
	private String pw;
	
	public member(String name, String id, String pw)
	{
		this.name = name;  //�̸�
		this.id = id;      //���̵�
		this.pw = pw;      //��й�ȣ
	}
	
	public String get_id()
	{
		return this.id;    //���̵� ���� -> login Ŭ���� ������ Ȯ�� ���� ���
	}
	
	public String get_pw()
	{
		return this.pw;    //��й�ȣ�� ���� -> login Ŭ���� ������ Ȯ�� ���� ���
	}
	
	public void change_pw(String pw) {
		this.pw = pw;     //�Ű����� ��й�ȣ�� ����
	}
	public String get_name()
	{
		return this.name; //�̸��� ����
	}

}

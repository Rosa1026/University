package assignment4;

public class Visitor {
	private String name; //VIP, Common ������ ���� name ����
	private int money;   //���� ���ϴ� Ȥ�� �Ա��Ϸ��� �ݾ� ����
	private boolean type; // true �� �Ա�, false�� ����
	
	public Visitor(String name, int money, boolean type)
	{
		this.name = name;
		this.money = money;
		this.type = type;
	}
	
	public Visitor(int money, boolean type)
	{
		this.name = "�͸�";
		this.money = money;
		this.type = type;
	}
	
	public int GetMoney()
	{
		return money;
	} //Visitor�� money �޾ƿ�
	
	public boolean CheckType()
	{
		return this.type;
	} //Visitor�� type �޾ƿ� -> �������� �Ա�����
	
	public String GetName()
	{
		return this.name;
	} //Visitor�� �̸� �޾ƿ� -> VIP���� Common����
	
}
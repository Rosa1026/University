package assignment4;

public class Visitor {
	private String name; //VIP, Common 나누기 위한 name 변수
	private int money;   //고객이 원하는 혹은 입금하려는 금액 변수
	private boolean type; // true 면 입금, false면 대출
	
	public Visitor(String name, int money, boolean type)
	{
		this.name = name;
		this.money = money;
		this.type = type;
	}
	
	public Visitor(int money, boolean type)
	{
		this.name = "익명";
		this.money = money;
		this.type = type;
	}
	
	public int GetMoney()
	{
		return money;
	} //Visitor의 money 받아옴
	
	public boolean CheckType()
	{
		return this.type;
	} //Visitor의 type 받아옴 -> 대출인지 입금인지
	
	public String GetName()
	{
		return this.name;
	} //Visitor의 이름 받아옴 -> VIP인지 Common인지
	
}
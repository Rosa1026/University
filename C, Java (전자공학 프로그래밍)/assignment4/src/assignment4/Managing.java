package assignment4;

public class Managing implements Runnable{
	public static int totalTime = 15; //15초 동안 진행 위해 15 선언
	public static int money = 15000;  //시작 잔액 15000원으로 선언
	public static int time = 0;       //시작 초 0초
	public static Waiting common;     
	public static Waiting vip;        //Waiting 객체 2개 선언
	
	public static Visitor commonVisitors[];
	public static Visitor vipVisitors[];   //Vistitor 객체를 가지는 배열 선언
	
	
	@Override
	public void run() {
		common.PrintInfo();
		vip.PrintInfo();
		System.out.println("현재 은행 재고는 "+money+"입니다."); //현재 잔액 출력
		System.out.println("-----------시간 : "+time+"초-----------"); //현재 시간 출력
		time+=1; //시간 1씩 증가
	}
	
	public static boolean CheckLoan(int money) //대출 요청 시 잔액 확인
	{
		if(Managing.money - money < 0) { //현재 money에서 입력된 money를 뺐을 때 0보다 작으면
			return false; //잔액 부족으로 대출 불가
		}
		else { //0보다 크므로
			return true; //대출 가능
		}
	}
	
	public void init(Waiting common, Waiting vip) //Waiting으로 선언된 객체를 매개변수로 받음
	{
		Managing.common = common; //매개변수로 들어온 common을 Managing.common에 저장
		Managing.vip = vip;       //매개변수로 들어온 vip을 Managing.vip에 저장
		
		commonVisitors = new Visitor[totalTime]; //Visitor[15]로 commonVisitor 생성
		vipVisitors = new Visitor[totalTime];    //Visitor[15]로 vipVisitor 생성
		
		commonVisitors[0] = new Visitor("A", 3000, false);
		commonVisitors[1] = new Visitor("B", 3000, false);
		commonVisitors[2] = new Visitor("C", 3000, false);
		commonVisitors[3] = new Visitor("D", 3000, false); //객체 4개 생성
		
		vipVisitors[0] = new Visitor("VA", 3000, false);
		vipVisitors[1] = new Visitor("VB", 6000, false);
		vipVisitors[2] = new Visitor("VC", 6000, false);
		vipVisitors[3] = new Visitor("VD", 3000, true); //객체 4개 생성
	}
	

}
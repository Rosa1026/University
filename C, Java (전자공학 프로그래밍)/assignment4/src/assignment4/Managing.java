package assignment4;

public class Managing implements Runnable{
	public static int totalTime = 15; //15�� ���� ���� ���� 15 ����
	public static int money = 15000;  //���� �ܾ� 15000������ ����
	public static int time = 0;       //���� �� 0��
	public static Waiting common;     
	public static Waiting vip;        //Waiting ��ü 2�� ����
	
	public static Visitor commonVisitors[];
	public static Visitor vipVisitors[];   //Vistitor ��ü�� ������ �迭 ����
	
	
	@Override
	public void run() {
		common.PrintInfo();
		vip.PrintInfo();
		System.out.println("���� ���� ���� "+money+"�Դϴ�."); //���� �ܾ� ���
		System.out.println("-----------�ð� : "+time+"��-----------"); //���� �ð� ���
		time+=1; //�ð� 1�� ����
	}
	
	public static boolean CheckLoan(int money) //���� ��û �� �ܾ� Ȯ��
	{
		if(Managing.money - money < 0) { //���� money���� �Էµ� money�� ���� �� 0���� ������
			return false; //�ܾ� �������� ���� �Ұ�
		}
		else { //0���� ũ�Ƿ�
			return true; //���� ����
		}
	}
	
	public void init(Waiting common, Waiting vip) //Waiting���� ����� ��ü�� �Ű������� ����
	{
		Managing.common = common; //�Ű������� ���� common�� Managing.common�� ����
		Managing.vip = vip;       //�Ű������� ���� vip�� Managing.vip�� ����
		
		commonVisitors = new Visitor[totalTime]; //Visitor[15]�� commonVisitor ����
		vipVisitors = new Visitor[totalTime];    //Visitor[15]�� vipVisitor ����
		
		commonVisitors[0] = new Visitor("A", 3000, false);
		commonVisitors[1] = new Visitor("B", 3000, false);
		commonVisitors[2] = new Visitor("C", 3000, false);
		commonVisitors[3] = new Visitor("D", 3000, false); //��ü 4�� ����
		
		vipVisitors[0] = new Visitor("VA", 3000, false);
		vipVisitors[1] = new Visitor("VB", 6000, false);
		vipVisitors[2] = new Visitor("VC", 6000, false);
		vipVisitors[3] = new Visitor("VD", 3000, true); //��ü 4�� ����
	}
	

}
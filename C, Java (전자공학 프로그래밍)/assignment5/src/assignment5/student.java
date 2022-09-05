package assignment5;

import java.util.ArrayList;

public class student extends member{
   private ArrayList<lecture> lectures = new ArrayList<>(); //���Ǹ� �����ϱ� ���� �迭 ����
   
   public student(String name, String id, String pw)
   {
      super(name, id, pw); //�л��� �̸�, id, ��й�ȣ
   }
   
   public int get_num_of_lectures()
   {
	   return this.lectures.size(); //������û�� ���� ���� ��ȯ
   }
 
   public lecture get_index_lecture(int index)
   {
	   return lectures.get(index); //������ index ��ȯ
   }
   public boolean register_lecture(lecture lec)
   {
        if(this.duplication_check(lec) && this.schedule_check(lec)) { //���� ��� �ð��� �ߺ����� �������� �ʾ�����
           this.lectures.add(lec); //lec�� ��û�� ���Ǹ�Ͽ� �߰�
           return true; //true ��ȯ -> ������û ����
        }
        else
        	return false; //false ��ȯ -> ������û ����
  
   }
   public boolean duplication_check(lecture check) //���� ���� ������û�ߴ��� Ȯ��
   {
      for(lecture temp : this.lectures)
      {
         if(check == temp) //check�� temp�� ������
         {
            System.out.println("�ߺ���û �ϼ̽��ϴ�. Ȯ���Ͻð� �ٽ� ��û�� �ּ���.");
            System.out.println("��ġ�� ������ : " + temp.get_name()); //���� ���
            return false; //false ��ȯ
         }
         
      }
      return true; //�ƴ϶�� true ��ȯ
   }
   
   
   public boolean schedule_check(lecture check) //�ð� ��ġ���� Ȯ��
   {
   
      int time1, time2;
      time1 = check.get_time1();
      time2 = check.get_time2();
      for (lecture temp : this.lectures) {
         
         if(time1 == temp.get_time1() || time1 == temp.get_time2() || time2 == temp.get_time1() || time2 == temp.get_time2())
         { //temp�� time1, time2�� check�� time1, time2�� �ϳ��� ��ġ���� Ȯ��
            System.out.println("�ð��� ��ġ�� ������ �����մϴ�. Ȯ���غ��ð� �ٽ� ��û�� �ּ���");
            System.out.println("��ġ�� ������ : " + temp.get_name());
            System.out.println(); //���� ���
            return false; //false ��ȯ -> ��ġ�Ƿ� ������û ����
         }
         
      }
      return true; //true ��ȯ -> ������û ����
   }
   
} 
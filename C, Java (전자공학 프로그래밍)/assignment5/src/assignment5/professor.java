package assignment5;

import java.util.ArrayList;

public class professor extends member{
	private ArrayList<lecture> lectures = new ArrayList<>(); //����� ���� �������� �迭 ����
	
	public professor(String name, String id, String pw)
	{
		super(name, id, pw); //�������� �̸�, id, ��й�ȣ
	}
	public int get_num_of_lectures()
    {
	    return this.lectures.size(); //����� ���� ���� ��ȯ
    }
 
	public lecture get_index_lecture(int index)
	{
     	return lectures.get(index); //������ index ��ȯ
	}
	
	public boolean register_lecture(lecture lec) { //���� ��� �޼ҵ�
		
		if(schedule_check(lec)) //schedule_check�޼ҵ� ���� �� true�� ��ȯ�ƴٸ�
		{
			lectures.add(lec); //lectures�� lec �߰�
			management.lectures.add(lec);
			return true; //true ��ȯ -> ���ǵ�� ����
		}
		return false; //false ��ȯ -> ���ǵ�� ����
		
	}
	
	public void register_lecture(String lecture_name, String time) { //���� ��� �޼ҵ�
		
		lecture new_lecture = new lecture(lecture_name, this.get_name(), time); //new lecture ����
		if(schedule_check(new_lecture)) //new_lecture�� �Ű������� �޼ҵ� ���� �� true ��ȯ�ߴٸ�
		{
			lectures.add(new_lecture); //lectures�� �߰�
			management.lectures.add(new_lecture);
		}
			
	}
	
	
	public boolean schedule_check(lecture check) //�ߺ��Ǵ� �ð��� ������ üũ
	{
	
		int time1, time2;
		time1 = check.get_time1();
		time2 = check.get_time2();
		for (lecture temp : this.lectures) {
			
			if(time1 == temp.get_time1() || time1 == temp.get_time2() || time2 == temp.get_time1() || time2 == temp.get_time2())
				//temp�� time1, time2�� check�� time1, time2�� �ϳ��� ��ġ���� Ȯ��
				return false; //false ��ȯ -> ���ǵ�� ����
			
		}
		return true; //true ��ȯ -> ���ǵ�� ����
	}

}

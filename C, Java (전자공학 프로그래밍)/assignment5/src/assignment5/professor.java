package assignment5;

import java.util.ArrayList;

public class professor extends member{
	private ArrayList<lecture> lectures = new ArrayList<>(); //등록한 강의 저장위한 배열 생성
	
	public professor(String name, String id, String pw)
	{
		super(name, id, pw); //교수님의 이름, id, 비밀번호
	}
	public int get_num_of_lectures()
    {
	    return this.lectures.size(); //등록한 강의 숫자 반환
    }
 
	public lecture get_index_lecture(int index)
	{
     	return lectures.get(index); //강의의 index 반환
	}
	
	public boolean register_lecture(lecture lec) { //강의 등록 메소드
		
		if(schedule_check(lec)) //schedule_check메소드 실행 시 true가 반환됐다면
		{
			lectures.add(lec); //lectures에 lec 추가
			management.lectures.add(lec);
			return true; //true 반환 -> 강의등록 성공
		}
		return false; //false 반환 -> 강의등록 실패
		
	}
	
	public void register_lecture(String lecture_name, String time) { //강의 등록 메소드
		
		lecture new_lecture = new lecture(lecture_name, this.get_name(), time); //new lecture 생성
		if(schedule_check(new_lecture)) //new_lecture를 매개변수로 메소드 실행 시 true 반환했다면
		{
			lectures.add(new_lecture); //lectures에 추가
			management.lectures.add(new_lecture);
		}
			
	}
	
	
	public boolean schedule_check(lecture check) //중복되는 시간이 없는지 체크
	{
	
		int time1, time2;
		time1 = check.get_time1();
		time2 = check.get_time2();
		for (lecture temp : this.lectures) {
			
			if(time1 == temp.get_time1() || time1 == temp.get_time2() || time2 == temp.get_time1() || time2 == temp.get_time2())
				//temp의 time1, time2가 check의 time1, time2와 하나라도 겹치는지 확인
				return false; //false 반환 -> 강의등록 실패
			
		}
		return true; //true 반환 -> 강의등록 성공
	}

}

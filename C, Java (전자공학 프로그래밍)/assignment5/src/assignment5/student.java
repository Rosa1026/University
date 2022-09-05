package assignment5;

import java.util.ArrayList;

public class student extends member{
   private ArrayList<lecture> lectures = new ArrayList<>(); //강의를 저장하기 위해 배열 선언
   
   public student(String name, String id, String pw)
   {
      super(name, id, pw); //학생의 이름, id, 비밀번호
   }
   
   public int get_num_of_lectures()
   {
	   return this.lectures.size(); //수강신청한 강의 숫자 반환
   }
 
   public lecture get_index_lecture(int index)
   {
	   return lectures.get(index); //강의의 index 반환
   }
   public boolean register_lecture(lecture lec)
   {
        if(this.duplication_check(lec) && this.schedule_check(lec)) { //강의 명과 시간을 중복으로 선택하지 않았으면
           this.lectures.add(lec); //lec을 신청한 강의목록에 추가
           return true; //true 반환 -> 수강신청 성공
        }
        else
        	return false; //false 반환 -> 수강신청 실패
  
   }
   public boolean duplication_check(lecture check) //같은 과목 수강신청했는지 확인
   {
      for(lecture temp : this.lectures)
      {
         if(check == temp) //check와 temp가 같으면
         {
            System.out.println("중복신청 하셨습니다. 확인하시고 다시 신청해 주세요.");
            System.out.println("겹치는 수업명 : " + temp.get_name()); //문구 출력
            return false; //false 반환
         }
         
      }
      return true; //아니라면 true 반환
   }
   
   
   public boolean schedule_check(lecture check) //시간 겹치는지 확인
   {
   
      int time1, time2;
      time1 = check.get_time1();
      time2 = check.get_time2();
      for (lecture temp : this.lectures) {
         
         if(time1 == temp.get_time1() || time1 == temp.get_time2() || time2 == temp.get_time1() || time2 == temp.get_time2())
         { //temp의 time1, time2가 check의 time1, time2와 하나라도 겹치는지 확인
            System.out.println("시간이 겹치는 수업이 존재합니다. 확인해보시고 다시 신청해 주세요");
            System.out.println("겹치는 수업명 : " + temp.get_name());
            System.out.println(); //문구 출력
            return false; //false 반환 -> 겹치므로 수강신청 실패
         }
         
      }
      return true; //true 반환 -> 수강신청 성공
   }
   
} 
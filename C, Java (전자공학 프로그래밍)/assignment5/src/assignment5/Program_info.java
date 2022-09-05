package assignment5;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Program_info extends JFrame{
	 Program_info() {
		 setTitle("Program Info"); //팝업창의 이름을 Program Info로 지정
		 
	     JPanel WindowContainer = new JPanel(); //panel 선언
	     setContentPane(WindowContainer);       //panel에 content를 넣기위한 작업
	     
	     JLabel j1 = new JLabel("<html><body style='text-align:center;'>만든이 : 안민규<br />학번 : 201620837<br />업데이트 날짜 : 2021-06-15</body></html>"); //레이블 선언
	     
	     WindowContainer.add(j1); //선언한 레이블을 panel에 추가
	     
	     setSize(300,100);        //사이즈는 300,100
	     setResizable(false);     //사이즈 변경 불가
	     setVisible(true);        //창 보이게 설정
	    }
}
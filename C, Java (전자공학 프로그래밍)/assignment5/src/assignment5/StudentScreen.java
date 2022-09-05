package assignment5;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StudentScreen extends JFrame implements ActionListener {
	private St_RegisterScreen rsc; //St_RegisterScreen 형식의 rsc 선언
	private JTabbedPane jtp;       //JTabbedPane jtp -> panel
	
	
	public StudentScreen() {
		
		jtp = new JTabbedPane();   //Panel 선언
		rsc = new St_RegisterScreen(); //RegisterScreen 선언
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //화면에 close 버튼 (x)
		JMenuBar jmb = new JMenuBar();	//메뉴바 생성
		
		JMenu menu1 = new JMenu("기능"); //메뉴1 - 기능 - 로그아웃, 종료
		JMenu menu2 = new JMenu("정보"); //메뉴2 - 정보 - 프로그램 정보
		
		jmb.add(menu1);
		jmb.add(menu2);  //menu1,2를 메뉴바에 추가
		
		
		JMenuItem jmi1 = new JMenuItem("로그아웃");
		JMenuItem jmi2 = new JMenuItem("종료"); //메뉴1에 들어갈 아이템 선언
		
		menu1.add(jmi1);
		menu1.add(jmi2); //메뉴1에 아이템 2개 추가
		
		JMenuItem jmi3 = new JMenuItem("프로그램 정보"); //메뉴2에 들어갈 아이템 선언
		
		menu2.add(jmi3); //메뉴2에 아이템 추가
		
		jmi1.addActionListener(this);
		jmi2.addActionListener(this);
		jmi3.addActionListener(this); //목록 3개 모두 클릭이라는 이벤트에 반응해야하므로 listener 선언
		
		setJMenuBar(jmb); //메뉴바 set
		
		jtp.addTab("수강신청", rsc); //panel에 수강신청이라는 이름의 rsc 추가
		getContentPane().add(jtp);
				
		setTitle("Student Panel");//이름은 Student Panel
		setSize(400,500);
		setVisible(true);

	}
	public void actionPerformed(ActionEvent ae) //이벤트에 반응
	{
		String s = ae.getActionCommand();
		if (s == "로그아웃") { //이벤트가 발생한 곳에 저장된 문자열이 로그아웃이라면
			LoginScreen login = new LoginScreen(); //새로운 로그인 창 선언
			login.setVisible(true); //새로운 로그인 창 보이게 설정
			setVisible(false);      //본래 있던 창은 안보이게 설정
		} 
		else if (s == "종료") {      //종료라면
			new New_Window2(2);    //New_Window2에 매개변수 2를 넣고 메소드 실행
		}
		else if (s == "프로그램 정보") { //프로그램 정보라면
			Program_info info = new Program_info(); //Program_info() 선언
			info.setVisible(true);                  //프로그램 정보 보이는 창 보이게 설정
		}
	}
	
}

class St_RegisterScreen extends JPanel implements ActionListener{ //rsc
	
	int num_of_lectures = 6;
	ButtonGroup gb = new ButtonGroup(); //버튼 그룹 생성
	JCheckBox[] jc = new JCheckBox[num_of_lectures + 1]; //체크박스 생성
	private student st; //student 형식 객체 생성
	
	St_RegisterScreen() {
		st = (student) management.now_member; //management에 저장되어있는 현재 멤버를 student형으로 바꾸어 st에 저장
		num_of_lectures = management.lectures.size(); //강의의 사이즈를 num of lectures에 저장
		GridLayout b1 = new GridLayout(num_of_lectures + 2,4,10,10); //행 : 강의 목록 +2, 열 : 4, 줄간격 : 10,10 으로 하는 grid layout 설정
		setLayout(b1); //앞서 설정한 레이아웃으로 rsc 설정
		
		
		String[] content = {"선택","과목명","시간","교수명"}; //4열에 들어갈 목록 이름
		
		
		JButton btn = new JButton("수강신청"); //수강신청 적혀있는 버튼 생성
		
		for(int i = 0; i <4; i++)
			add(new JLabel(content[i])); //목록 이름을 차례대로 레이블 선언하여 판넬에 붙임 첫번째 행
		
		for(int i = 0; i < num_of_lectures ; i++)
		{
			jc[i] = new JCheckBox(""); //i에 체크박스 생성 -> 순서대로 체크박스 (선택칸)
			add(jc[i]); //생성한 체크박스 붙임
			add(new JLabel(management.lectures.get(i).get_name())); //강의 이름(과목명)
			add(new JLabel(management.lectures.get(i).get_time())); //시간(시간)
			add(new JLabel(management.lectures.get(i).get_prof())); //교수님 성함(교수명)
			gb.add(jc[i]); //앞서 선언한 체크박스를 버튼 그룹으로 설정
			jc[i].addActionListener(this); //체크박스 클릭하면서 이벤트가 발생하므로 listner 선언
						
		}
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel("")); //num of lectures 행까지 다 채웠으므로 마지막 남은 한 행을 위해 공백 3개
		add(btn);            //버튼 추가 -> 이로 인해 앞서 grid layout의 행을 num of lectures+2로 설정
		
		btn.addActionListener(this); //버튼 또한 눌리면서 이벤트가 발생하므로 listner 선언
		
		// TODO Auto-generated constructor stub
	}
	public void actionPerformed(ActionEvent ae) {
		String s = ae.getActionCommand();
		lecture temp;
		if(s == "수강신청") //이벤트가 발생한 곳에 저장된 문자열이 수강신청이라면
		{
			st = (student) management.now_member; //st에 now_member 저장
			for(int i =0; i < num_of_lectures; i++) //강의 수만큼 반복
			{
				if(jc[i].isSelected())              //선택된 체크박스 확인
				{
					temp = management.lectures.get(i); //선택된 체크박스 번호 i에 맞는 과목 temp에 저장
					
					if(st.register_lecture(temp)) //temp를 student.register_lecture 메소드의 매개변수로 사용해 호출해서 1이라면 -> 수강 신청함
					{
						new New_Window2(0);       //new_window2 메소드 매개변수0으로 호출
						
					}
					else                          //수강신청이 되지 않았다면
					{
						new New_Window2(1);       //new_window2 메소드 매개변수1으로 호출
					}
					
				}
			}
		}
		
	}

	
}

class New_Window2 extends JFrame implements ActionListener{ //new_window2 메소드
	 New_Window2(int flag) {
	        setTitle("Alert"); //생성된 팝업 창의 이름은 Alert - 알림
	        
	        JPanel NewWindowContainer = new JPanel(); //Panel 생성
	        setContentPane(NewWindowContainer);
	        
	        FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 5, 5); //flowlayout으로 설정
	        
	        JButton btn1 = new JButton("종료"); //종료 버튼
	        JButton btn2 = new JButton("취소"); //취소 버튼 -> 실수로 종료를 눌렀을 때 바로 종료되지 않게끔 추가구현
	        
	        if(flag == 0) //new_window2 메소드를 호출하는 매개변수가 0이라면
	        {
	        	NewWindowContainer.add(new JLabel("선택하신 강의가 정상적으로 신청되었습니다.")); //신청 성공 메세지
		        
		        setSize(300,100);
		        setResizable(false);
		        setVisible(true);
	        }   
	        else if(flag == 1) //매개변수가 1이라면
	        {
	        	NewWindowContainer.add(new JLabel("선택하신 강의를 신청할 수 없습니다."));
	        	NewWindowContainer.add(new JLabel("시간 및 강의를 다시 확인해 주세요.")); //신청 실패 메세지
		        
		        setSize(300,100);
		        setResizable(false);
		        setVisible(true);
	        }
	        else if(flag == 2) //매개변수가 2라면
	        {
		        NewWindowContainer.setLayout(fl); //flowlayout 적용
		        
	        	NewWindowContainer.add(new JLabel("프로그램을 종료하시겠습니까?")); //한번 더 물어보는 문구와 함께
	        	NewWindowContainer.add(btn1);
	        	NewWindowContainer.add(btn2); //앞서 생성한 버튼 추가
		        
		        setSize(200,100);
		        setResizable(false);
		        setVisible(true);
	        }
	        
			btn1.addActionListener(this);
			btn2.addActionListener(this); //두 버튼 모두 이벤트 listner로 선언
	  }
	 public void actionPerformed(ActionEvent ae) {
			String s = ae.getActionCommand();
			if(s == "종료") //눌린게 종료라면
				System.exit(0); //시스템 종료
			else if(s == "취소") //눌린게 취소라면
				setVisible(false); //Alert 창 지움
	 }
				
}

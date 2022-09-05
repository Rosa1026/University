package assignment5;

import java.util.Scanner;
import java.util.ArrayList;

public class management {
	public static ArrayList<student> students = new ArrayList<student>();
	public static ArrayList<professor> professors = new ArrayList<professor>();
	public static ArrayList<lecture> lectures = new ArrayList<lecture>();
	
	public static Scanner scanner = new Scanner(System.in);
	
	public static member now_member;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		init.Init_Program();
		new LoginScreen();
	}
	
}

package main;

import java.util.Scanner;

import dao.AdminDao;
import dao.AdminDaoImpl;
import dao.FacultyDaoImpl;
import exceptions.AdminException;
import exceptions.FacultyException;
import colors.ConsoleColors;
import useCase.adminUseCase;
import useCase.facultyUseCase;

public class Main {
	
	public static void main(String[] args) {
		System.out.println(ConsoleColors.CYAN + "1. Admin Login");
		System.out.println("2. Faculty Login");
		System.out.println("3. Close" + ConsoleColors.RESET);
		Scanner sc=new Scanner(System.in);
		System.out.println();
		System.out.println("put your choice");
		int x=sc.nextInt();
		if(x==1) {
			
//			Scanner sc=new Scanner(System.in);
			System.out.println("give username");
			String username=sc.next();
			System.out.println("give pasword");
			String password=sc.next();
			try {
				AdminDao ad=new AdminDaoImpl();
				Boolean b=ad.LoginAdmim(username, password);
				System.out.println();
				if(b) {
					System.out.println(ConsoleColors.GREEN_BACKGROUND+"Log In Successful..."+ConsoleColors.RESET);
					System.out.println(ConsoleColors.GREEN+"Welcome :)"+ConsoleColors.RESET);
					adminUseCase adm=new adminUseCase();
					adm.adminOptions();
				
				}else {
					main(args);
				}
				
				
				
			} catch (AdminException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				System.out.println(e.getMessage());
				main(args);
			}
			
			
		}else if(x==2) {
			System.out.println("give username");
			String username=sc.next();
			System.out.println("give password");
			String password=sc.next();
			try {
				boolean x1=new FacultyDaoImpl().FacultyLogin(username, password);
				
				if(x1) {
					System.out.println(ConsoleColors.GREEN_BACKGROUND+"Log In Successful..."+ConsoleColors.RESET);
					System.out.println(ConsoleColors.GREEN+"Welcome :)"+ConsoleColors.RESET);
					facultyUseCase.facultyOptions();
				}else {
					main(args);
				}
				
			} catch (FacultyException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());;
				
			}
			
			
		}else if(x==3) {
			System.out.println(ConsoleColors.BANANA_YELLOW+"Application closed....See You Soon..."+ConsoleColors.RESET);
		}else {
			System.out.println(ConsoleColors.RED_BOLD_BRIGHT+"Unexpected value:"+ x+ConsoleColors.RESET );
			main(args);
		}

	
		
		
		
		
		

	}

}

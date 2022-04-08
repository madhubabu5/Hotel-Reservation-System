package ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

public class MainMenu {
	
	public boolean validateEmail(String email) {
		final String emailRegex = "^(.+)@(.+).com$";		
		Pattern pat = Pattern.compile(emailRegex);
		if (email == null)
		return false;
		return pat.matcher(email).matches();
	}
	public String takeStringInput(String msg) {
		Scanner sc = new Scanner(System.in);
		String text = "no";
		do {
			try {
				System.out.println(msg);
				text = sc.next();
			}
			catch(InputMismatchException e) {
				System.out.println("Error: Invalid input");
			}
			sc.nextLine();
		}while(text=="no");
		return text;
	}
	
	public int takeIntInput() {
		Scanner sc = new Scanner(System.in);
		int choice = -1;
		do {
			try {
				System.out.println("Please select a number for the menu option");
				choice = sc.nextInt();
			}
			catch(InputMismatchException e) {
				System.out.println("Error: Invalid input");
			}
			sc.nextLine();
		}while(choice<=0);
		
		return choice;
	}
	
	public Date takeDateInput(String msg) {
		Scanner sc = new Scanner(System.in);
		System.out.println(msg);
		String strDate = sc.next();
		Date d;
	    try
	    {
	        d= new SimpleDateFormat("dd-MM-yyyy").parse(strDate);
	        return d;
	    }
	    catch (ParseException e)
	    {
	        System.out.println(strDate+" is Invalid Date format");
	        return takeDateInput(msg);
	    }
	}
	public MainMenu() {
		System.out.println("-----------------------------------------------");
		System.out.println("1. Find and reserve a room");
		System.out.println("2. See my reservations");
		System.out.println("3. Create an accout");
		System.out.println("4. Admin");
		System.out.println("5. Exit");
		System.out.println("-----------------------------------------------");
		Scanner sc = new Scanner(System.in);
		int choice= takeIntInput();
		HotelResource hotelResource = HotelResource.getInstance();
		AdminResource adminResource = AdminResource.getInstance();
		switch(choice) {
			case 1:	
					String  msg = "Enter checkIn Date dd-mm-year example 01-04-2022"; 
					Date checkIn = takeDateInput(msg);
					
					msg = "Enter CheckOut Date dd-mm-year  example  03-04-2022";
					Date checkOut = takeDateInput(msg);
					Collection<IRoom> availableRooms = hotelResource.findARoom(checkIn, checkOut);
					
					if(availableRooms.isEmpty()) {
						System.out.println("no rooms available");
						hotelResource.showAvailableDates();
					}
					else {
						System.out.println(availableRooms);
						System.out.println("Would you like to book a room? y/n");
						String wantBooking = sc.next();
						while(wantBooking.equalsIgnoreCase("y")==false && wantBooking.equalsIgnoreCase("n")==false) {
							System.out.println("please provide input y or n");
							wantBooking = sc.next();
						}
						if(wantBooking.equalsIgnoreCase("y")) {
							System.out.println("Do you have an account with us ? y/n");
							String haveAccount = sc.next();
								
							while(haveAccount.equalsIgnoreCase("y")==false && haveAccount.equalsIgnoreCase("n")==false) {
								System.out.println("please provide input y or n");
								haveAccount = sc.next();
							}
							if(haveAccount.equalsIgnoreCase("y")) {
								
								
								System.out.println("Enter Email Format: name@domain.com");
								String mail = sc.next();
								
								while(validateEmail(mail)==false) {
									System.out.println("Invalid email, please Enter Email Format: name@domain.com");
									mail = sc.next();
								}
								
								while(hotelResource.getCustomer(mail)==null) {
									System.out.println("no customer with this email,please enter valid email id");
									mail = sc.next();
								}
								System.out.println("What room number that you would like to reserve");
								String reqRoomNumber = sc.next();
								IRoom reqRoom = hotelResource.getRoom(reqRoomNumber);
								
								while(!availableRooms.contains(reqRoom)) {
									System.out.println("please enter room number from the available rooms");
									reqRoomNumber = sc.next();
									reqRoom = hotelResource.getRoom(reqRoomNumber);
								}
									
								Customer customer = hotelResource.getCustomer(mail);
								IRoom room = hotelResource.getRoom(reqRoomNumber);
									
								Reservation resv = hotelResource.bookARoom(mail, room, checkIn, checkOut);
								System.out.println(resv);
							}
							else {
								System.out.println("Plese create the account first");
								new MainMenu();
							}
						}
						else {
							new MainMenu();
						}
					}
					new MainMenu();
					break;
			case 2:
					try {
					adminResource.displayAllReservations();
					}
					catch(Exception e) {
						e.getLocalizedMessage();
					}
					new MainMenu();
					break;
			case 3:
					msg = "Enter email format: name@domain.com";
					String email = takeStringInput(msg);
					while(validateEmail(email)==false) {
						System.out.println("Invalid email, please Enter Email Format: name@domain.com");
						email = sc.next();
					}
					System.out.println("Enter firstname ");
					String firstName = sc.next();
					System.out.println("Enter lastname");
					String lastName = sc.next();
					
					hotelResource.createACustomer(email, firstName, lastName);
					System.out.println("Welcome to the Hotel Reservation System");
					new MainMenu();
					break;
			case 4:
					new AdminMenu();
					break;
			case 5:
					System.out.println("successfully exited from application");
					System.exit(0);
					break;
			default:
					System.out.println("Enter valid menu number");
					new MainMenu();
		}
	}
}
		

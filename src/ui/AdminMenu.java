package ui;

import java.util.Date;
import java.util.Scanner;

import api.AdminResource;
import api.HotelResource;
import model.Room;
import model.RoomType;
import service.CustomerService;
import service.ReservationService;

public class AdminMenu {
	
	public AdminMenu() {
		System.out.println("-----------------------------------------------");
		System.out.println("1. See All Customers");
		System.out.println("2. See All Rooms");
		System.out.println("3. See All Reservations");
		System.out.println("4. Add A Room");
		System.out.println("5. Back to Main Menu");
		System.out.println("-----------------------------------------------");
		System.out.println("please select a number for menu option");
		Scanner sc = new Scanner(System.in);
		int adminChoice = sc.nextInt();
//		CustomerService customerService = CustomerService.getInstance();
//		ReservationService reservationService = ReservationService.getInstance();
		HotelResource hotelResource = HotelResource.getInstance();
		AdminResource adminResource = AdminResource.getInstance();
		switch(adminChoice) {
		case 1: 
				System.out.println(adminResource.getAllCustomers());
				new AdminMenu();
				break;
		case 2:
				System.out.println(adminResource.getAllRooms());
				new AdminMenu();
				break;
		case 3:
				adminResource.displayAllReservations();
				new AdminMenu();
				break;
		case 4:
				System.out.println("Enter a room number");
				String roomNumber = sc.next();
				
				// if room is already created
				while(hotelResource.getRoom(roomNumber)!=null) {
					System.out.println("This room is already created, please enter different room number");
					roomNumber = sc.next();
				}
				
				System.out.println("Enter price per night");
				double price = sc.nextDouble();
				System.out.println("Enter room type:1 for single bed, 2 for double bed");
				int rm = sc.nextInt();
				RoomType rt = RoomType.SINGLE;
				if(rm==2) {
					rt = RoomType.DOUBLE;
				}
				Room room = new Room(roomNumber,price,rt);
				adminResource.addRoom(room);
				new AdminMenu();
				break;
		case 5:
				new MainMenu();
				break;
		}
	}
	
	

}

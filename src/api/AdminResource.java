package api;

import java.util.Collection;
import java.util.List;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

public class AdminResource {
	// provide a static reference
	CustomerService customerService = CustomerService.getInstance();
	ReservationService reservationService = ReservationService.getInstance();
	
	static AdminResource  adminResource = new AdminResource();
	
	private AdminResource() {
		
	}
	
	public static AdminResource getInstance() {
		return adminResource;
	}
	
	public AdminResource(CustomerService customerService) {
//		customerService = new CustomerService();
//		reservationService = new ReservationService();
		this.customerService = customerService;
	}
	
	public Customer getCustomer(String email) {
		return customerService.getCustomer(email);
	}
	
//	public void addRoom(List<IRoom> rooms) {
//		
//	}
	
	public void addRoom(IRoom room) {
		reservationService.addRoom(room);
	}
	
	public Collection<IRoom> getAllRooms(){
		return reservationService.getRooms();
	}
	
	public Collection<Customer> getAllCustomers(){
		return customerService.getAllCustomers();
	}
	
	public void displayAllReservations() {
		reservationService.printAllReservation();
	}
}

package api;

import java.util.Collection;
import java.util.Date;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

public class HotelResource {
	
	static CustomerService customerService = CustomerService.getInstance();
	static ReservationService reservationService = ReservationService.getInstance();
	// provide a static reference
	
	static HotelResource hotelResource = new HotelResource();
	
	private HotelResource() {
		
	}
	
	public void showAvailableDates() {
		reservationService.showAvailableDates();
	}
	public static HotelResource getInstance() {
		return hotelResource;
	}
	
	public Customer getCustomer(String email) {
		return customerService.getCustomer(email);
	}
	
	public void createACustomer(String email,String firstName,String lastName) {
		customerService.addCustomer(email, firstName, lastName);
	}
	public IRoom getRoom(String roomNumber) {
		return reservationService.getARoom(roomNumber);
	}
	
	public Reservation bookARoom(String customerEmail,IRoom room,Date checkInDate,Date checkOutDate) {
		Customer customer = customerService.getCustomer(customerEmail);
		return reservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
	}
	
	public Collection<Reservation> getCustomersReservations(String customerEmail){
		Customer customer = customerService.getCustomer(customerEmail);
		return reservationService.getCustomersReservation(customer);
	}
	
	public Collection<IRoom> findARoom(Date checkInDate,Date checkOutDate){
		return reservationService.findRooms(checkInDate, checkOutDate);
	}
}

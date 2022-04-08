package service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;

public class ReservationService {
	private List<Reservation>  reservations;
	private List<IRoom> rooms;
	static ReservationService reservationService =new ReservationService();
	
	private ReservationService() {
		this.reservations = new ArrayList<Reservation>();
		this.rooms = new ArrayList<IRoom>();
	}
	
	public static ReservationService getInstance() {
		return reservationService;
	}
	
	public List<Reservation> getReservations() {
		return reservations;
	}

	void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public List<IRoom> getRooms() {
		return rooms;
	}

	
	
	public void addRoom(IRoom room) {
		rooms.add(room);
		return ;
	}
	public IRoom getARoom(String roomId) {
		for(IRoom room:rooms) {
			if(room.getRoomNumber().equalsIgnoreCase(roomId)) {
				return room;
			}
		}
		return null;
	}
	public Reservation reserveARoom(Customer customer,IRoom room,Date checkInDate,Date checkOutDate) {
		Reservation res = new Reservation(customer,room,checkInDate,checkOutDate);
		reservations.add(res);
		return res;
	}
	
	public Collection<IRoom> findRooms(Date checkInDate,Date checkOutDate){
		
		Collection<IRoom> availableRooms = new HashSet<IRoom>();
			for(Reservation res:reservations) {
				/*check if checkInDate and checkOutDate lies between already reserved Dates */
				if(checkInDate.compareTo(res.checkInDate)>=0 && checkInDate.compareTo(res.checkOutDate)<=0) {
					continue;
				}
				if(checkOutDate.compareTo(res.checkInDate)>=0 && checkOutDate.compareTo(res.checkOutDate)<=0) {
					continue;
				}
				
				// checkinDate < resv.chechinDate && checkout > resv.checkOut
				if(checkInDate.compareTo(res.checkInDate)<0 && checkOutDate.compareTo(res.checkOutDate)>0) {
					continue;
				}
				availableRooms.add(res.room);
			}
			for(IRoom room:rooms) {
				boolean found = false;
				for(Reservation res:reservations) {
					if(room.getRoomNumber().equalsIgnoreCase(res.room.getRoomNumber())) {
						found = true;
						break;
					}
				}
				if(found==false) {
					availableRooms.add(room);
				}
			}
		return availableRooms;
	}
	
	public  void showAvailableDates(){
		for(Reservation resv:reservations) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date d = resv.checkOutDate;
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			c.add(Calendar.DAY_OF_MONTH, 1);
			String dateAfter = sdf.format(c.getTime());
			System.out.println("room number "+resv.room.getRoomNumber()+" is available from "+dateAfter);
		}
	}
	public Collection<Reservation> getCustomersReservation(Customer customer){
		Collection<Reservation> customerReservations = new ArrayList<Reservation>();
		for(Reservation res:reservations) {
			if(customer.getEmail().equalsIgnoreCase(res.customer.getEmail())) {
				customerReservations.add(res);
			}
		}
		return customerReservations;
	}
	
	public void printAllReservation() {
		
		for(Reservation res:reservations) {
			System.out.println(res);
		}
		
	}
	// Create collections to store and 	retrieve a reservation
	
	// provide a static reference
}

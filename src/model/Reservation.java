package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Reservation {
	public Customer customer;
	public IRoom room;
	public Date checkInDate;
	public Date checkOutDate;
	public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
		super();
		this.customer = customer;
		this.room = room;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
	}
	public String toString() {
		return "Reservation\n"+this.customer.getFirstName()+this.customer.getLastName()+"\n"+"Room="+this.room.getRoomNumber()+"  "+this.room.getRoomType()+  "\ncheckInDate="+this.checkInDate+"\ncheckOutDate="+this.checkOutDate+"\n";
	}
	@Override
	public int hashCode() {
		return Objects.hash(checkInDate, checkOutDate, customer, room);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservation other = (Reservation) obj;
		return Objects.equals(checkInDate, other.checkInDate) && Objects.equals(checkOutDate, other.checkOutDate)
				&& Objects.equals(customer, other.customer) && Objects.equals(room, other.room);
	}
	
	
}

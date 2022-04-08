package model;

import java.util.Objects;

public class Room implements IRoom {

	@Override
	public int hashCode() {
		return Objects.hash(enumeration, price, roomNumber);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		return enumeration == other.enumeration && Objects.equals(price, other.price)
				&& Objects.equals(roomNumber, other.roomNumber);
	}

	private String roomNumber;
	private Double price;
	private RoomType enumeration;
	
	public Room(String roomNumber,Double price,RoomType enumeration) {
		this.roomNumber = roomNumber;
		this.price = price;
		this.enumeration = enumeration;
	}
	@Override
	public String getRoomNumber() {
		return this.roomNumber;
	}

	@Override
	public Double getRoomPrice() {
		return this.price;
	}

	@Override
	public RoomType getRoomType() {
		return this.enumeration;
	}

	@Override
	public boolean isFree() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public String toString() {
		return "Room :" + "room number="+ this.getRoomNumber()+"   price=$"+this.getRoomPrice()+"  single or double bed="+this.getRoomType()+ "\n";
	}
}

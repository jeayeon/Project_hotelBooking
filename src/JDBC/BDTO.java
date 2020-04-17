package JDBC;

import java.io.Serializable;

public class BDTO implements Serializable {

	private String id;
	private String room;
	private int people;
	private int checkin;
	private int checkout;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public int getPeople() {
		return people;
	}

	public void setPeople(int people) {
		this.people = people;
	}

	public int getCheckin() {
		return checkin;
	}

	public void setCheckin(int checkin) {
		this.checkin = checkin;
	}

	public int getCheckout() {
		return checkout;
	}

	public void setCheckout(int checkout) {
		this.checkout = checkout;
	}

}

package JDBC;

import java.io.Serializable;

public class BDTO implements Serializable {

	private String renum;
	private String id;
	private String name;
	private String room;
	private int people;
	private String checkin;
	private String checkout;
	private int night; // 몇박
	private int price; //토탈금액으로 저장

	
	
	public String getRenum() {
		return renum;
	}

	public void setRenum(String renum) {
		this.renum = renum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNight() {
		return night;
	}

	public void setNight(int night) {
		this.night = night;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

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

	public String getCheckin() {
		return checkin;
	}

	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}

	public String getCheckout() {
		return checkout;
	}

	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}

}

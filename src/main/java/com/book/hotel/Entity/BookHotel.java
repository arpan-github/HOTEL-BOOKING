package com.book.hotel.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class BookHotel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int BookHotelId;
	private String firstname;
	private String lastname;
	private String email;
	private String phNumber;
	private String checkInDate;
	private String checkOutDate;
	private int adults;
	private int kids;
	private int totalRoom;
	private String ac;
	private Date bookingtime;

	@ManyToOne
	@JsonIgnore
	private User user;
	
	@ManyToOne
	@JsonIgnore
	private Post post;

	public int getBookHotelId() {
		return BookHotelId;
	}

	public void setBookHotelId(int bookHotelId) {
		BookHotelId = bookHotelId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhNumber() {
		return phNumber;
	}

	public void setPhNumber(String phNumber) {
		this.phNumber = phNumber;
	}

	public String getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}

	public String getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public int getAdults() {
		return adults;
	}

	public void setAdults(int adults) {
		this.adults = adults;
	}

	public int getKids() {
		return kids;
	}

	public void setKids(int kids) {
		this.kids = kids;
	}

	public int getTotalRoom() {
		return totalRoom;
	}

	public void setTotalRoom(int totalRoom) {
		this.totalRoom = totalRoom;
	}

	public String getAc() {
		return ac;
	}

	public void setAc(String ac) {
		this.ac = ac;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BookHotel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Date getBookingtime() {
		return bookingtime;
	}

	public void setBookingtime(Date bookingtime) {
		this.bookingtime = bookingtime;
	}

	public BookHotel(int bookHotelId, String firstname, String lastname, String email, String phNumber,
			String checkInDate, String checkOutDate, int adults, int kids, int totalRoom, String ac,
			Date bookingtime, User user) {
		super();
		BookHotelId = bookHotelId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phNumber = phNumber;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.adults = adults;
		this.kids = kids;
		this.totalRoom = totalRoom;
		this.ac = ac;
		this.bookingtime = bookingtime;
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
}

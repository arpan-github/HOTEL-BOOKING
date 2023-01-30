package com.book.hotel.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
	private String postname;
	private String businessEmail;
	private String hotelLocation;
	private String onePhoneNumber;
	private String twoPhoneNumber;
	private int romePrice;
	private String wiFi;
	private String sqft;
	private String doublebed;
	private String restaurant;
	private String park;
	private String Pool;
	private String postDescription;
	private String image;
	private Date date;
	private String CityName;
	private int ACRoomprice;
	private int totalRoom;
	private int totalacRoom;
	private int totalnonacRoom;
	private String carpark;
	private String Deluxebed;
	private String Dormitorybed;
	private int doublebedPrice;
	private int DeluxebedPrice;
	private int DormitorybedPrice;


	@ManyToOne
	@JoinColumn(name = "catagory_id")
	private Catagory catagory;

	@ManyToOne
	@JsonIgnore
	private User user;

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<BookHotel> bookHotel = new ArrayList<>();
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Comment> comment = new ArrayList<>();

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public String getPostname() {
		return postname;
	}

	public void setPostname(String postname) {
		this.postname = postname;
	}

	public String getBusinessEmail() {
		return businessEmail;
	}

	public void setBusinessEmail(String businessEmail) {
		this.businessEmail = businessEmail;
	}

	public String getHotelLocation() {
		return hotelLocation;
	}

	public void setHotelLocation(String hotelLocation) {
		this.hotelLocation = hotelLocation;
	}

	public String getOnePhoneNumber() {
		return onePhoneNumber;
	}

	public void setOnePhoneNumber(String onePhoneNumber) {
		this.onePhoneNumber = onePhoneNumber;
	}

	public String getTwoPhoneNumber() {
		return twoPhoneNumber;
	}

	public void setTwoPhoneNumber(String twoPhoneNumber) {
		this.twoPhoneNumber = twoPhoneNumber;
	}

	public int getRomePrice() {
		return romePrice;
	}

	public void setRomePrice(int romePrice) {
		this.romePrice = romePrice;
	}

	public String getWiFi() {
		return wiFi;
	}

	public void setWiFi(String wiFi) {
		this.wiFi = wiFi;
	}

	public String getSqft() {
		return sqft;
	}

	public void setSqft(String sqft) {
		this.sqft = sqft;
	}

	public String getDoublebed() {
		return doublebed;
	}

	public void setDoublebed(String doublebed) {
		this.doublebed = doublebed;
	}

	public String getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}

	public String getPark() {
		return park;
	}

	public void setPark(String park) {
		this.park = park;
	}

	public String getPool() {
		return Pool;
	}

	public void setPool(String pool) {
		Pool = pool;
	}

	public String getPostDescription() {
		return postDescription;
	}

	public void setPostDescription(String postDescription) {
		this.postDescription = postDescription;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCityName() {
		return CityName;
	}

	public void setCityName(String cityName) {
		CityName = cityName;
	}

	public int getACRoomprice() {
		return ACRoomprice;
	}

	public void setACRoomprice(int aCRoomprice) {
		ACRoomprice = aCRoomprice;
	}

	public int getTotalRoom() {
		return totalRoom;
	}

	public void setTotalRoom(int totalRoom) {
		this.totalRoom = totalRoom;
	}

	public int getTotalacRoom() {
		return totalacRoom;
	}

	public void setTotalacRoom(int totalacRoom) {
		this.totalacRoom = totalacRoom;
	}

	public int getTotalnonacRoom() {
		return totalnonacRoom;
	}

	public void setTotalnonacRoom(int totalnonacRoom) {
		this.totalnonacRoom = totalnonacRoom;
	}

	public String getCarpark() {
		return carpark;
	}

	public void setCarpark(String carpark) {
		this.carpark = carpark;
	}

	public String getDeluxebed() {
		return Deluxebed;
	}

	public void setDeluxebed(String deluxebed) {
		Deluxebed = deluxebed;
	}

	public String getDormitorybed() {
		return Dormitorybed;
	}

	public void setDormitorybed(String dormitorybed) {
		Dormitorybed = dormitorybed;
	}

	public int getDoublebedPrice() {
		return doublebedPrice;
	}

	public void setDoublebedPrice(int doublebedPrice) {
		this.doublebedPrice = doublebedPrice;
	}

	public int getDeluxebedPrice() {
		return DeluxebedPrice;
	}

	public void setDeluxebedPrice(int deluxebedPrice) {
		DeluxebedPrice = deluxebedPrice;
	}

	public int getDormitorybedPrice() {
		return DormitorybedPrice;
	}

	public void setDormitorybedPrice(int dormitorybedPrice) {
		DormitorybedPrice = dormitorybedPrice;
	}

	public Catagory getCatagory() {
		return catagory;
	}

	public void setCatagory(Catagory catagory) {
		this.catagory = catagory;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<BookHotel> getBookHotel() {
		return bookHotel;
	}

	public void setBookHotel(List<BookHotel> bookHotel) {
		this.bookHotel = bookHotel;
	}

	public Post(Integer postId, String postname, String businessEmail, String hotelLocation, String onePhoneNumber,
			String twoPhoneNumber, int romePrice, String wiFi, String sqft, String doublebed, String restaurant,
			String park, String pool, String postDescription, String image, Date date, String cityName, int aCRoomprice,
			int totalRoom, int totalacRoom, int totalnonacRoom, String carpark, String deluxebed, String dormitorybed,
			int doublebedPrice, int deluxebedPrice, int dormitorybedPrice, Catagory catagory, User user,
			List<BookHotel> bookHotel) {
		super();
		this.postId = postId;
		this.postname = postname;
		this.businessEmail = businessEmail;
		this.hotelLocation = hotelLocation;
		this.onePhoneNumber = onePhoneNumber;
		this.twoPhoneNumber = twoPhoneNumber;
		this.romePrice = romePrice;
		this.wiFi = wiFi;
		this.sqft = sqft;
		this.doublebed = doublebed;
		this.restaurant = restaurant;
		this.park = park;
		Pool = pool;
		this.postDescription = postDescription;
		this.image = image;
		this.date = date;
		CityName = cityName;
		ACRoomprice = aCRoomprice;
		this.totalRoom = totalRoom;
		this.totalacRoom = totalacRoom;
		this.totalnonacRoom = totalnonacRoom;
		this.carpark = carpark;
		Deluxebed = deluxebed;
		Dormitorybed = dormitorybed;
		this.doublebedPrice = doublebedPrice;
		DeluxebedPrice = deluxebedPrice;
		DormitorybedPrice = dormitorybedPrice;
		this.catagory = catagory;
		this.user = user;
		this.bookHotel = bookHotel;
	}

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Comment> getComment() {
		return comment;
	}

	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}

}

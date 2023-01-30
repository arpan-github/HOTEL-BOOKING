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
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer usreId;
	@NotBlank(message = "name not be blank")
	@Size(min = 2, max = 20, message = "min 2 and max 20 characters are allowed !!")
	private String name;
	@NotBlank(message = "Email not be blank")
	private String email;
	@NotBlank(message = "password not be blank")
	private String password;

	@NotBlank(message = "password not be blank")
	private String phnumber;

	private String roll;

	private boolean enabled;

	private Date date;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Post> posts = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Search> search = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<BookHotel> bookHotel = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Catagory> catagory = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Comment> comment = new ArrayList<>();

	public Integer getUsreId() {
		return usreId;
	}

	public void setUsreId(Integer usreId) {
		this.usreId = usreId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhnumber() {
		return phnumber;
	}

	public void setPhnumber(String phnumber) {
		this.phnumber = phnumber;
	}

	public String getRoll() {
		return roll;
	}

	public void setRoll(String roll) {
		this.roll = roll;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public List<Search> getSearch() {
		return search;
	}

	public void setSearch(List<Search> search) {
		this.search = search;
	}

	public List<BookHotel> getBookHotel() {
		return bookHotel;
	}

	public void setBookHotel(List<BookHotel> bookHotel) {
		this.bookHotel = bookHotel;
	}

	public User(Integer usreId,
			@NotBlank(message = "name not be blank") @Size(min = 2, max = 20, message = "min 2 and max 20 characters are allowed !!") String name,
			@NotBlank(message = "Email not be blank") String email,
			@NotBlank(message = "password not be blank") String password,
			@NotBlank(message = "password not be blank") String phnumber, String roll, boolean enabled, Date date,
			List<Post> posts, List<Search> search, List<BookHotel> bookHotel) {
		super();
		this.usreId = usreId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phnumber = phnumber;
		this.roll = roll;
		this.enabled = enabled;
		this.date = date;
		this.posts = posts;
		this.search = search;
		this.bookHotel = bookHotel;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Catagory> getCatagory() {
		return catagory;
	}

	public void setCatagory(List<Catagory> catagory) {
		this.catagory = catagory;
	}

	public List<Comment> getComment() {
		return comment;
	}

	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}

}

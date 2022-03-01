package com.brianfair.javagroupproject.models;


import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="users")
public class User
{
	
    public User() {
    }
    
    public User(String firstName, String lastName,
    		String address, String city, String state,
    		String email, String password) {
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.address = address;
    	this.city = city;
    	this.state = state;
    	this.email = email;
    	this.password = password;
    }
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Size(min = 2, max = 50)
    private String firstName;
    
    @NotNull
    @Size(min = 2, max = 50)
    private String lastName;
    
    @NotNull
    @Size(min = 2, max = 50)
    private String address;
    
    @NotNull
    @Size(min = 2, max = 50)
    private String city;
    
    @NotNull
    @Size(min = 2, max = 50)
    private String state;
    
    @Email(message="Not a valid email!")
    private String email;
    
    
//    @Column(nullable=true, length=150)
//    private String picture;
    
    @NotNull
    @Size(min = 8, max = 150)
    private String password;
    
    @Transient
    private String passwordConfirmation;
    
	// Monday Jan 27, 2021 = "E, MMM dd, yyyy"
	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;
    
	// Monday Jan 27, 2021 = "E, MMM dd, yyyy"
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;
    
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Order>orders;
//	
//	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
//	@JsonIgnore
//	private List<Comment> comments;
//	
//	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
//	@JsonIgnore
//	private List<View> views;
//
//	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable
	(
	    name = "favorites", 
	    joinColumns = @JoinColumn(name = "user_id"), 
	    inverseJoinColumns = @JoinColumn(name = "order_id")
	)
	private List<Order> favoriteOrders;
   

	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	
	public String getDateFormatted()
	{
		String pattern = "E, MMM dd, yyyy";
		Date orderDate = getCreatedAt();
		System.out.println(orderDate);
		String dateToStr = DateFormat.getInstance().format(orderDate);
		System.out.println(dateToStr);
		return dateToStr;
	}

	public List<Order> getFavoriteOrders() {
		return favoriteOrders;
	}

	public void setFavoriteOrders(List<Order> favoriteOrders) {
		this.favoriteOrders = favoriteOrders;
	}

//c4523ba8df196210006438eb002b376c995842b2
    
    
	

	
	
    
    

    
		
	    
	    
}

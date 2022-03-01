package com.brianfair.javagroupproject.models;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="orders")
public class Order
{

	public Order() {
	}
	
	public Order(String method, String size, String crust,
			String quantity, String toppings, String price, User user) {
	}
    
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	    
	@NotNull
	@Size(min = 2, max = 100, message="Must be more than 2 characters long!")
	private String method;
	
	@NotNull
	@Size(min = 2, max = 100, message="Must be more than 2 characters long!")
	private String size;
	
	@NotNull
	@Size(min = 2, max = 100, message="Must be more than 2 characters long!")
	private String crust;
	
	@NotNull
	@Size(min = 1, max = 25, message="Can't be blank!")
	private String quantity;
	
	@NotNull
	@Size(min = 2, max = 200, message="Must be more than 2 characters long!")
	private String toppings;
	
	@NotNull
	@Size(min = 1, max = 200, message="Can't be blank")
	private String price;
	
 	    
	// Monday Jan 27, 2021 = "E, MMM dd, yyyy"
	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;
    
	// Monday Jan 27, 2021 = "E, MMM dd, yyyy"
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;
	
	
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
//	
//	
//	@OneToMany(mappedBy="show", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
//	@JsonIgnore
//	private List<Rate> ratings;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable
	(
	    name = "favorites", 
	    joinColumns = @JoinColumn(name = "order_id"), 
	    inverseJoinColumns = @JoinColumn(name = "user_id")
	)
	private List<User> likers;
//	
//	@OneToMany(mappedBy="algo", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
//	@JsonIgnore
//	private List<Comment> comments;
//	
//	@OneToMany(mappedBy="algo", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
//	@JsonIgnore
//	private List<View> views;

	
//	public String getDateFormatted() {
//		SimpleDateFormat df = new SimpleDateFormat("MMMM dd, yyy");
//		return df.format(this.createdAt);
//	}
	
    @PrePersist
    private void onCreate(){
        this.createdAt = new Date();
    }
    
    @PreUpdate
    private void onUpdate(){
        this.updatedAt = new Date();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getCrust() {
		return crust;
	}

	public void setCrust(String crust) {
		this.crust = crust;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getToppings() {
		return toppings;
	}

	public void setToppings(String toppings) {
		this.toppings = toppings;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public List<User> getLikers() {
		return likers;
	}

	public void setLikers(List<User> likers) {
		this.likers = likers;
	}
	
	public String getDateFormatted()
	{
		String pattern = "E, MMM dd, yyyy";
		Date orderDate = getCreatedAt();
		String dateToStr = DateFormat.getInstance().format(orderDate);
		System.out.println(dateToStr);
		return dateToStr;
	}

    
    	

	
	
   
   
		
	    
	    
}

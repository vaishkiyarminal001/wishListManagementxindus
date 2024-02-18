package com.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class WishlistItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Price is required")
    private String price;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private MyUser user;

	public WishlistItem(Long id, @NotBlank(message = "Name is required") String name,
			@NotBlank(message = "Description is required") String description,
			@NotBlank(message = "Price is required") String price, MyUser user) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.user = user;
	}

	public WishlistItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public MyUser getUser() {
		return user;
	}

	public void setUser(MyUser user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "WishlistItem [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", user=" + user + "]";
	}
    
    
}

package com.sample.demo.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "offer")
public class Offer {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="offer_id")
	private int offerId;
	@Column(name="description",nullable = false)
    private String description;
	@Column(name="currency",nullable = false)
    private String currency;
	@Column(name="price",nullable = false)
    private double price;
	
	public Offer() {}

    public Offer(int offerId, String description, String currency, double price) {
        this.offerId = offerId;
        this.description = description;
        this.currency = currency;
        this.price = price;
    }

	public String getDescription() {
		return description;
	}

	public String getCurrency() {
		return currency;
	}

	public double getPrice() {
		return price;
	}

	public int getOfferId() {
		return offerId;
	}

	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}

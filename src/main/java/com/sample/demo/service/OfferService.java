package com.sample.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sample.demo.bo.Offer;
/*
 * Sample Service to create, delete, find Offer details
 * */
@Service
public interface OfferService {
         public List<Offer> getAllOffers();
         public Offer createOffer(Offer offer);
         public void deleteOffer(int offerId);
}

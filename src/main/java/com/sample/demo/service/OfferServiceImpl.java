package com.sample.demo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sample.demo.bo.Offer;

import com.sample.demo.repository.OfferRepository;

@Service
public class OfferServiceImpl implements OfferService{
    
	private static final Logger log = LoggerFactory.getLogger(OfferServiceImpl.class);
	@Autowired
    private OfferRepository offerRepository;
	/*Get all offer details
     * @input int int
     * @returns Page<Offer> 
     * */
	@Override
	public List<Offer> getAllOffers() {
		return offerRepository.findAll();
	}

	/*Creates a new Offer
     * @input offer
     * @returns offer 
     * */
	@Override
	public Offer createOffer(Offer offer) {
		log.info("Offer details created: "+ offer.getDescription());
        return offerRepository.save(offer);
	}
	/*Deletes offer details
     * @input int
     * @returns Offer 
     * */
	@Override
	public void deleteOffer(int offerId) {
		offerRepository.delete(offerId);
	}

}

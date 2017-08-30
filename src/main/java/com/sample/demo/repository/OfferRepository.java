package com.sample.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sample.demo.bo.Offer;

@Repository
public interface OfferRepository extends CrudRepository<Offer, Integer>{
	 /*To return List of Offers*/
     List<Offer> findAll();
}

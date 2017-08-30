package com.sample.demo.web.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sample.demo.bo.Offer;
import com.sample.demo.service.OfferService;

@RestController
@RequestMapping(value = "/offers")
public class OfferController {
	protected final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private OfferService offerService;
	
	@RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    public void createOffer(@RequestBody Offer offer,
                                 HttpServletRequest request, HttpServletResponse response) {
        Offer createdOffer = this.offerService.createOffer(offer);
        response.setHeader("Location", request.getRequestURL().append("/").append(createdOffer.getOfferId()).toString());
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Offer>> getAllOffers() {
    	ResponseEntity<List<Offer>> responseEntity = new ResponseEntity<List<Offer>>(this.offerService.getAllOffers(),
                HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOffer(@PathVariable("id") Integer id, HttpServletRequest request,
                                 HttpServletResponse response) {
        this.offerService.deleteOffer(id);
    }

}

package com.sample.demo;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.demo.bo.Offer;
import com.sample.demo.repository.OfferRepository;
import com.sample.demo.service.OfferServiceImpl;
import com.sample.demo.web.api.OfferController;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = SampleOffersSpringBootProjectApplication.class)
@Profile("test")
public class OfferControllerIT {
	@InjectMocks
    OfferController controller;
	
	@Mock
	OfferServiceImpl service;
	
	@Mock
	OfferRepository repository;

    @Autowired
    WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void initTests() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    
    @Test
    public void testCreateDeleteOffer() throws Exception {
        Offer offer = mockOffer();
        byte[] offerJson = toJson(offer);

        //CREATE
        MvcResult result = mvc.perform(post("/offers")
                .content(offerJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        long id = getResourceIdFromUrl(result.getResponse().getRedirectedUrl());
       
        //DELETE
        mvc.perform(delete("/offers/" + id))
                .andExpect(status().isNoContent());
    }
    
    private Offer mockOffer() {
    	return new Offer(1, "If the bill is more than £9000, discount will be 10%", "pounds", 9000.0);
    }
    private List<Offer> mockOfferList() {
    	List<Offer> offers = Arrays.asList(
                new Offer(1, "If the bill is more than £9000, discount will be 10%", "pounds", 9000.0),
                new Offer(2, "If the bill is more than £15000, discount will be 12%", "pounds", 15000.0));
    	return offers;
    }
    private byte[] toJson(Object obj) throws Exception {
        ObjectMapper map = new ObjectMapper();
        return map.writeValueAsString(obj).getBytes();
    }
    private long getResourceIdFromUrl(String locationUrl) {
        String[] parts = locationUrl.split("/");
        return Long.valueOf(parts[parts.length - 1]);
    }

}

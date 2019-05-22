package com.hsc.authenication.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hsc.authenication.dto.SubscriberDto;
import com.hsc.authenication.entity.Subscriber;
import com.hsc.authenication.service.SubscriberServie;
import com.hsc.authentication.mapper.SubscriberMapper;

@SuppressWarnings("rawtypes")
@RestController
public class SubscriberController {
	
	@Autowired
	private SubscriberServie subscriberServie;
	
	
	/**
	 * Returns all subscribers based on request param( not mandatory) 
	 * if request param not present, it will return all subscribers
	 * */
	@GetMapping("/subscriber/load")
	public ResponseEntity getAllSubscribers(@RequestParam(required=false) boolean active){

		List<Subscriber> subscribers =(active)? subscriberServie.getAllActiveSubscribers()
												:subscriberServie.getAllSubscribers();
		return new ResponseEntity<>(SubscriberMapper.toSubscriberDtos(subscribers),HttpStatus.OK);
		
	}
	
	@PostMapping("/subscriber")
	public ResponseEntity createSubscriber(@Valid @RequestBody SubscriberDto subscriberDto){
		return new ResponseEntity<>(subscriberServie.create(subscriberDto),HttpStatus.CREATED);
	}
	
	@PutMapping("/subscriber")
	public ResponseEntity updateSubscriber(@Valid @RequestBody SubscriberDto subscriberDto){
		return new ResponseEntity<>(subscriberServie.update(subscriberDto),HttpStatus.OK);
	}
	
	
	
	@DeleteMapping("/subscriber/{subscriberId}")
	public ResponseEntity deleteSubscriber(@PathVariable Long subscriberId){
		return new ResponseEntity<>(subscriberServie.delete(subscriberId),HttpStatus.OK);
	}
	
}

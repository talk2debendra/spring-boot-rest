package com.hsc.authenication.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsc.authenication.dto.SubscriberDto;
import com.hsc.authenication.entity.Subscriber;
import com.hsc.authenication.exception.ResourceNotFoundException;
import com.hsc.authenication.exception.SubscriberException;
import com.hsc.authenication.repository.SubscriberRepository;
import com.hsc.authenication.service.SubscriberServie;
import com.hsc.authentication.mapper.SubscriberMapper;

@Service
public class SubscriberServiceImpl implements SubscriberServie{

	@Autowired
	private SubscriberRepository subscriberRepository; 
	

	@Override
	public Subscriber getSubscriberByUserName(String userName) {
		return subscriberRepository.getSubscriberByUserName(userName.toLowerCase())
				.orElse(null);
	}

	
	@Override
	public Subscriber getSubscriberByAuthToken(String authToken) {
		return subscriberRepository.getSubscriberByAuthToken(authToken).orElse(null);
	}

	
	
	
	@Override
	public Subscriber getSubscriberByUserNameNotInThis(String userName, Long subscriberId) {
		return subscriberRepository.getSubscriberByUserNameAndNotInThisId(userName.toLowerCase(),subscriberId)
				.orElse(null);
	}
	
	@Override
	public Subscriber create(SubscriberDto subscriberDto) {
		
		if(getSubscriberByUserName(subscriberDto.getUserName())!=null){
			throw new SubscriberException();
		}
		Subscriber subscriber=SubscriberMapper.toSubscriber(subscriberDto);
		
		Long id=subscriberRepository.save(subscriber).getId();
		subscriber.setSubscriberId(subscriber.getSubscriberId()+String.valueOf(id));
		
		return subscriberRepository.save(subscriber);
	}


	@Override
	public Subscriber update(SubscriberDto subscriberDto) {
		Subscriber subscriber = getSubscriberById(subscriberDto.getId());
		
		if(getSubscriberByUserNameNotInThis(subscriberDto.getUserName(),subscriberDto.getId())!=null){
			throw new SubscriberException();
		}
		
		return subscriberRepository.save(SubscriberMapper.toSubscriber(subscriberDto, subscriber));
	}


	@Override
	public boolean delete(Long subscriberId) {
		Subscriber subscriber = getSubscriberById(subscriberId);
		subscriber.setIsActive(false);
		subscriberRepository.save(subscriber);
		return true;
	}


	@Override
	public Subscriber getSubscriberById(Long subscriberId) {
		return subscriberRepository.getSubscriberById(subscriberId).orElseThrow(ResourceNotFoundException::new);
	}


	@Override
	public List<Subscriber> getAllActiveSubscribers() {
		return subscriberRepository.getAllActiveSubscribers();
	}


	@Override
	public List<Subscriber> getAllSubscribers() {
		return subscriberRepository.findAll();
	}

	@Override
	public Subscriber update(Subscriber subscriber) {
		return subscriberRepository.save(subscriber);
	}

}

package com.hsc.authenication.service;

import java.util.List;

import com.hsc.authenication.dto.SubscriberDto;
import com.hsc.authenication.entity.Subscriber;
import com.hsc.authenication.exception.ResourceNotFoundException;

public interface SubscriberServie {

	
	/**
	 * Checks whether the subscriber is exist on DB based on provided username(case insensitive) 
	 * @param userName 
	 * @return null/Subscriber <p> If found , return the same Subscriber otherwise null</p>
	 * */
	public Subscriber getSubscriberByUserName(String userName);

	
	/**
	 * Checks whether the subscriber is exist on DB based on provided authToken 
	 * @param authToken 
	 * @return null/Subscriber <p> If found , return the same Subscriber otherwise null</p>
	 * */
	public Subscriber getSubscriberByAuthToken(String authToken);
	
	
	
	/**
	 * Checks whether the subscriber is exist on DB based on provided username(case insensitive) 
	 * (Exclude provided Id) 
	 *  
	 * @param userName 
	 * @param subscriberId
	 * @return null/Subscriber <p> If found , return the same Subscriber otherwise null</p>
	 * */
	public Subscriber getSubscriberByUserNameNotInThis(String userName,Long subscriberId);
	
	
	
	/**
	 * @param userName userName of the subscriber
	 * @return true/false <p> if found , return true else return false</p>
	 * */
	public default boolean isSubscriberExists(String userName) {
    	return (getSubscriberByUserName(userName)!=null);
    }
	
	/**
	 * Creates a new Subscriber and save into aligned DB
	 * @param subscriberDto 
	 * @return {@link Subscriber} Returns created Subscriber
	 * */
	public Subscriber create(SubscriberDto subscriberDto);
	

	/**
	 * Update the Subscriber and save into aligned DB
	 * If the requested Subscriber doesn't exists, throws {@link ResourceNotFoundException} 
	 * @param subscriberDto 
	 * @return {@link Subscriber}Returns updated Subscriber
	 * @throws ResourceNotFoundException
	 * */
	public Subscriber update(SubscriberDto subscriberDto);
	
	public Subscriber update(Subscriber subscriber);
	
	/**
	 * @param subscriberId 
	 * @return true/false
	 * */
	public boolean delete(Long subscriberId);
	
	
	/**
	 * Load the {@link Subscriber} from DB by ID. If the requested resource doesn't exists,
	 * throws {@link ResourceNotFoundException}
	 * @param subscriberId 
	 * @return Subscriber
	 * @throws ResourceNotFoundException If the requested resource not found
	 * */
	public Subscriber getSubscriberById(Long subscriberId);
	
	
	/**
	 * Get all active(isActive=1) subscribers
	 * @return List<{@link Subscriber}>
	 * */
	public List<Subscriber> getAllActiveSubscribers();
	
	/**
	 * Get all active and inactive(isActive=1 OR isActive=1) subscribers
	 * @return List<{@link Subscriber}>
	 * */
	public List<Subscriber> getAllSubscribers();
	
}

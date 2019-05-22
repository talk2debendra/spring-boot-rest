package com.hsc.authentication.mapper;

import java.util.ArrayList;
import java.util.List;

import com.hsc.authenication.dto.SubscriberDto;
import com.hsc.authenication.entity.Subscriber;
import com.hsc.authenication.utilities.UUIDGenerator;

public class SubscriberMapper {

	public static Subscriber toSubscriber(SubscriberDto subscriberDto){
		Subscriber subscriber = new Subscriber();
		subscriber.setUserName(subscriberDto.getUserName());
		subscriber.setPassword(subscriberDto.getPassword());
		subscriber.setIsActive(true);
		subscriber.setSubscriberId(UUIDGenerator.generateUUID());
		return subscriber;
	}
	public static Subscriber toSubscriber(SubscriberDto subscriberDto,Subscriber subscriber){
		subscriber.setUserName(subscriberDto.getUserName());
		subscriber.setPassword(subscriberDto.getPassword());
		
		return subscriber;
	}
	
	public static SubscriberDto toSubscriberDto(Subscriber subscriber){
		SubscriberDto subscriberDto = new SubscriberDto();
		subscriberDto.setId(subscriber.getId());
		subscriberDto.setActive(subscriber.getIsActive());
		subscriberDto.setUserName(subscriber.getUserName());
		subscriberDto.setPassword(subscriber.getPassword());
		subscriberDto.setAuthToken(
				AuthTokenMapper.toAuthToken(subscriber.getAuthToken(),
				subscriber.getRefreshToken(),subscriber.getTokenExpiry(),subscriber.getSubscriberId())
		);
		return subscriberDto;
	}
	
	
	
	public static List<SubscriberDto> toSubscriberDtos(List<Subscriber> subscribers){
		
		List<SubscriberDto> dtos = new ArrayList<>();
		subscribers.forEach(subscriber->
			dtos.add(toSubscriberDto(subscriber))
		);
		return dtos;
	}
	
	
	
}



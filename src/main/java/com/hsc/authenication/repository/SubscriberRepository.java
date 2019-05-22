package com.hsc.authenication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hsc.authenication.entity.Subscriber;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Long>{

	@Query("FROM Subscriber AS subscriber"
			+ " WHERE subscriber.isActive=true "
			+ " AND subscriber.userName=:userName")
	public Optional<Subscriber> getSubscriberByUserName(@Param("userName") String userName);

	
	@Query("FROM Subscriber AS subscriber"
			+ " WHERE subscriber.isActive=true "
			+ " AND subscriber.authToken=:authToken")
	public Optional<Subscriber> getSubscriberByToken(@Param("authToken") String authToken);
	
	
	
	
	@Query("FROM Subscriber AS subscriber"
			+ " WHERE subscriber.isActive=true "
			+ " AND subscriber.userName=:userName"
			+ " AND subscriber.id!=:subscriberId")
	 public Optional<Subscriber> getSubscriberByUserNameAndNotInThisId(@Param("userName")String lowerCase,@Param("subscriberId") Long subscriberId);
	
	
	
	@Query("FROM Subscriber AS subscriber"
			+ " WHERE subscriber.isActive=true ")
	public List<Subscriber> getAllActiveSubscribers();
	
	
	@Query("FROM Subscriber AS subscriber"
			+ " WHERE subscriber.isActive=true "
			+ " AND subscriber.authToken=:authToken")
	public Optional<Subscriber> getSubscriberByAuthToken(@Param("authToken") String authToken);
	
	
	
	@Query("FROM Subscriber AS subscriber"
			+ " WHERE subscriber.isActive=true "
			+ " AND subscriber.id=:subscriberId")	
	public Optional<Subscriber> getSubscriberById(@Param("subscriberId") Long subscriberId);


	
	
	
}

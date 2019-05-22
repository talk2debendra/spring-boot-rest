package com.hsc.authenication.utilities;

import java.util.UUID;

public class UUIDGenerator {

	public static synchronized   String generateUUID(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-","");
	}
}

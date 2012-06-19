package de.merten.umgr.vaadin;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

public class PasswordGenerator {

	public static void main(String[] args) {
		
		ShaPasswordEncoder encoder = new ShaPasswordEncoder();
		
		String password = encoder.encodePassword("dev;2011", "jmey");
		
		System.out.println(password);
		
	}
}

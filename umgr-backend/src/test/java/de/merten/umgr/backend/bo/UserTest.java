package de.merten.umgr.backend.bo;

import org.junit.Assert;
import org.junit.Test;

public class UserTest {

	@Test
	public void testDaysConsumptionCalculation() {
		User user = new User();
		user.setPassword("test");
		Assert.assertTrue("Passwordsetting OK", "test".equals(user.getPassword()));
	}
}

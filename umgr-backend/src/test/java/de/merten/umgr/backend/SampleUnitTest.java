package de.merten.umgr.backend;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContextBackend-test.xml"})
// @ConfigurationContext(locations = {"app-context.xml", "test-context.xml"})
public class SampleUnitTest {

	@Test
	public void testTrue() {
		Assert.assertTrue(true);
	}
	// Ein kommentar, der entfernt werden kann.
}

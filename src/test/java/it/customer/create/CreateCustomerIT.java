package it.customer.create;

import it.support.RestClient;
import net.sf.lightair.LightAir;
import net.sf.lightair.annotation.Setup;
import net.sf.lightair.annotation.Verify;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;

import static it.support.Matchers.patternMatch;

@RunWith(LightAir.class)
@Setup
@Verify
public class CreateCustomerIT {

	@Test
	public void full() {
		RestClient.from(this)
				.requestName("full")
				.post("/api/customers")
				.emptyResponse()
				.statusCode(HttpStatus.SC_CREATED)
				.location(patternMatch("^[0-9]+$"));
	}
}

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

	private void ok(String request) {
		RestClient.from(this)
				.requestName(request)
				.post("/api/customers")
				.emptyResponse()
				.statusCode(HttpStatus.SC_CREATED)
				.location(patternMatch("^[0-9]+$"));
	}

	@Test
	public void full() {
		ok("full");
	}

	@Test
	public void minimal() {
		ok("minimal");
	}

	private void validation(String request) {
		RestClient.from(this)
				.requestName(request)
				.post("/api/customers")
				.responseName(request, ctx -> ctx.set("$.timestamp", "REPLACED"))
				.statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY);
	}

	@Test
	@Verify("CreateCustomerIT.xml")
	public void empty() {
		validation("empty");
	}

	@Test
	@Verify("CreateCustomerIT.xml")
	public void emptyValues() {
		validation("emptyValues");
	}

	@Test
	@Verify("CreateCustomerIT.xml")
	public void longValues() {
		validation("longValues");
	}

	@Test
	@Verify("CreateCustomerIT.xml")
	// mobile and email both null
	public void noContact() {
		validation("noContact");
	}

	@Test
	@Verify("CreateCustomerIT.xml")
	public void duplicateTaxNo() {
		validation("duplicateTaxNo");
	}
}

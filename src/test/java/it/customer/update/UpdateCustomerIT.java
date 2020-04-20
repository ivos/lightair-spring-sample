package it.customer.update;

import it.support.RestClient;
import net.sf.lightair.LightAir;
import net.sf.lightair.annotation.Setup;
import net.sf.lightair.annotation.Verify;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(LightAir.class)
@Setup.List({@Setup("../../delete-all.xml"), @Setup})
@Verify
public class UpdateCustomerIT {

	private String getPath(Long id) {
		return "/api/customers/" + id;
	}

	private void ok(String request) {
		RestClient.from(this)
				.requestName(request)
				.ifMatch(123L)
				.put(getPath(1374184902L))
				.emptyResponse()
				.statusCode(HttpStatus.SC_NO_CONTENT);
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
				.ifMatch(123L)
				.put(getPath(1374184902L))
				.responseName(request, ctx -> ctx.set("$.timestamp", "REPLACED"))
				.statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY);
	}

	@Test
	@Verify("UpdateCustomerIT.xml")
	public void empty() {
		validation("empty");
	}

	@Test
	@Verify("UpdateCustomerIT.xml")
	public void emptyValues() {
		validation("emptyValues");
	}

	@Test
	@Verify("UpdateCustomerIT.xml")
	public void longValues() {
		validation("longValues");
	}

	@Test
	@Verify("UpdateCustomerIT.xml")
	// mobile and email both null
	public void noContact() {
		validation("noContact");
	}

	@Test
	@Verify("UpdateCustomerIT.xml")
	public void duplicateTaxNo() {
		validation("duplicateTaxNo");
	}

	private void optimisticLock(Long version, String response) {
		RestClient.from(this)
				.requestName("full")
				.ifMatch(version)
				.put(getPath(1374184902L))
				.responseName(response, ctx -> ctx.set("$.timestamp", "REPLACED"))
				.statusCode(HttpStatus.SC_CONFLICT);
	}

	@Test
	@Verify("UpdateCustomerIT.xml")
	public void oldVersion() {
		optimisticLock(122L, "optimisticLock");
	}

	@Test
	@Verify("UpdateCustomerIT.xml")
	public void noVersion() {
		optimisticLock(null, "optimisticLock");
	}

	@Test
	@Verify("UpdateCustomerIT.xml")
	public void notFound() {
		RestClient.from(this)
				.get(getPath(666L))
				.responseName("notFound", ctx -> ctx.set("$.timestamp", "REPLACED"))
				.statusCode(HttpStatus.SC_NOT_FOUND);
	}
}

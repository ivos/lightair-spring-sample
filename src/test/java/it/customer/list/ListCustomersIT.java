package it.customer.list;

import it.support.RestClient;
import net.sf.lightair.LightAir;
import net.sf.lightair.annotation.Setup;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(LightAir.class)
@Setup
public class ListCustomersIT {

	private static final String PATH = "/api/customers";

	private void ok(String params, String response) {
		RestClient.from(this)
				.get(PATH + params)
				.responseName(response)
				.statusCode(HttpStatus.SC_OK);
	}

	@Test
	public void name() {
		ok("?search=Name A", "name");
	}

	@Test
	public void taxNo() {
		ok("?search=TaxNo A", "taxNo");
	}

	@Test
	public void mobile() {
		ok("?search=Mobile 5", "mobile");
	}

	@Test
	public void email() {
		ok("?search=Email A", "email");
	}

	@Test
	public void web() {
		ok("?search=Web A", "web");
	}

	@Test
	public void emptySearch() {
		ok("?search=", "all");
	}

	@Test
	public void noSearch() {
		ok("", "all");
	}
}

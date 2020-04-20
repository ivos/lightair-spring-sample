package it.customer.get;

import it.support.RestClient;
import net.sf.lightair.LightAir;
import net.sf.lightair.annotation.Setup;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(LightAir.class)
@Setup.List({@Setup("../../delete-all.xml"), @Setup})
public class GetCustomerIT {

	private String getPath(Long id) {
		return "/api/customers/" + id;
	}

	private void ok(Long id, Long version, String response) {
		RestClient.from(this)
				.get(getPath(id))
				.responseName(response)
				.statusCode(HttpStatus.SC_OK)
				.eTag(version);
	}

	@Test
	public void full() {
		ok(1374184902L, 122L, "full");
	}

	@Test
	public void minimalMobile() {
		ok(1374184903L, 123L, "minimalMobile");
	}

	@Test
	public void minimalEmail() {
		ok(1374184904L, 124L, "minimalEmail");
	}

	@Test
	public void notFound() {
		RestClient.from(this)
				.get(getPath(666L))
				.responseName("notFound", ctx -> ctx.set("$.timestamp", "REPLACED"))
				.statusCode(HttpStatus.SC_NOT_FOUND);
	}
}

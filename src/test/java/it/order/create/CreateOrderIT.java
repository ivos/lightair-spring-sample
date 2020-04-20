package it.order.create;

import it.support.RestClient;
import it.support.TimeMachine;
import net.sf.lightair.LightAir;
import net.sf.lightair.annotation.Setup;
import net.sf.lightair.annotation.Verify;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static it.support.Matchers.patternMatch;

@RunWith(LightAir.class)
@Setup.List({@Setup("../../delete-all.xml"), @Setup})
@Verify
public class CreateOrderIT {

	private static final String PATH = "/api/orders";

	@Before
	public void setUp() {
		TimeMachine.setFixed("2020-03-04T10:11:12");
	}

	@After
	public void tearDown() {
		TimeMachine.setSystem();
	}

	private void ok(String request) {
		RestClient.from(this)
				.requestName(request)
				.post(PATH)
				.emptyResponse()
				.statusCode(HttpStatus.SC_CREATED)
				.location(patternMatch("^[0-9]+$"));
	}

	@Test
	// no existing order number sequence
	public void full() {
		ok("full");
	}

	@Test
	public void minimal() {
		ok("minimal");
	}

	@Test
	@Setup({"CreateOrderIT.xml", "updateSeq.xml"})
	public void updateSeq() {
		ok("minimal");
	}

	private void validation(String request) {
		RestClient.from(this)
				.requestName(request)
				.post(PATH)
				.responseName(request, ctx -> ctx.set("$.timestamp", "REPLACED"))
				.statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY);
	}

	@Test
	@Verify("CreateOrderIT.xml")
	public void empty() {
		validation("empty");
	}

	@Test
	@Verify("CreateOrderIT.xml")
	public void emptyValues() {
		validation("emptyValues");
	}

	@Test
	@Verify("CreateOrderIT.xml")
	public void dueDateYesterday() {
		validation("dueDateYesterday");
	}

	@Test
	public void dueDateToday() {
		ok("dueDateToday");
	}
}

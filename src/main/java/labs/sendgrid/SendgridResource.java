package labs.sendgrid;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Tag(name = "sendgrid")
@Path("/sendgrid")
public class SendgridResource {

	@Inject
	SendgridService service;

	@POST
	public void sendEmail() throws IOException {
		service.sendEmail();
	}

}

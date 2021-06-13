package labs.sendgrid;

import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Attachments;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;

@ApplicationScoped
public class SendgridService {

	public void sendEmail() throws IOException {
		// sendWithMailHelper();
		send();
	}

	private void send() throws IOException {
		Mail mail = new Mail();

		Email fromEmail = new Email();
		fromEmail.setName("Robson Developer");
		fromEmail.setEmail("robsondeveloper@gmail.com");
		mail.setFrom(fromEmail);

		mail.setSubject("Hello World from the Twilio SendGrid Java Library");

		Personalization personalization = new Personalization();
		Email to = new Email();
		to.setName("Example User 1");
		to.setEmail("robson.costa@gmail.com");
		personalization.addTo(to);
		to.setName("Example User 2");
		to.setEmail("robsonyagami@yahoo.com.br");
		personalization.addTo(to);
		personalization.setSubject("Hello World from the Personalized Twilio SendGrid Java Library");
		mail.addPersonalization(personalization);

		Content content = new Content();
		content.setType("text/plain");
		content.setValue("some text here 1");
		mail.addContent(content);
		content.setType("text/html");
		content.setValue("<html><body>some text here 2</body></html>");
		mail.addContent(content);

		Attachments attachments = new Attachments();
		attachments
				.setContent("TG9yZW0gaXBzdW0gZG9sb3Igc2l0IGFtZXQsIGNvbnNlY3RldHVyIGFkaXBpc2NpbmcgZWxpdC4gQ3JhcyBwdW12");
		attachments.setType("application/pdf");
		attachments.setFilename("balance_001.pdf");
		attachments.setDisposition("attachment");
		attachments.setContentId("Balance Sheet");
		mail.addAttachments(attachments);

		Attachments attachments2 = new Attachments();
		attachments2.setContent("BwdW");
		attachments2.setType("image/png");
		attachments2.setFilename("banner.png");
		attachments2.setDisposition("inline");
		attachments2.setContentId("Banner");
		mail.addAttachments(attachments2);

		SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			sg.api(request);
		} catch (IOException ex) {
			throw ex;
		}
	}

	// minimum needed code to send an email with the /mail/send Helper
	private void sendWithMailHelper() throws IOException {
		Email from = new Email("robsondeveloper@gmail.com");
		String subject = "Sending with Twilio SendGrid is Fun";
		Email to = new Email("robson.costa@gmail.com");
		Content content = new Content("text/plain", "and easy to do anywhere, even with Java");
		Mail mail = new Mail(from, subject, to, content);

		SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
		System.out.println(sg.getVersion());
		System.out.println(sg.getHost());
		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);
			System.out.println(response.getStatusCode());
			System.out.println(response.getBody());
			System.out.println(response.getHeaders());
		} catch (IOException ex) {
			throw ex;
		}
	}

}

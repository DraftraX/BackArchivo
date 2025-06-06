package unsm.archivo.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import unsm.archivo.request.EmailRequest;

@Service
public class EmailService {

	private JavaMailSender javaMailSender;
	private TemplateEngine templateEngine;

	public EmailService(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
		this.javaMailSender = javaMailSender;
		this.templateEngine = templateEngine;
	}

	public void sendEmail(EmailRequest request) {
		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			Context context = new Context();
			Map<String, Object> model = new HashMap<>();
			model.put("username", request.getUsername());
			model.put("url", "http://localhost:5173/newpassword?emailTo=" + request.getMailTo());
			context.setVariables(model);
			String htmlText = templateEngine.process("email-template", context);
			helper.setFrom(request.getMailFrom());
			helper.setTo(request.getMailTo());
			helper.setSubject(request.getSubject());
			helper.setText(htmlText, true);
			javaMailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
		public boolean sendVerificationCode(String email, String code) {
		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			Context context = new Context();
			Map<String, Object> model = new HashMap<>();
			model.put("code", code);
			context.setVariables(model);
			String htmlText = templateEngine.process("verification-email", context);
			
			helper.setFrom("eduysting@gmail.com");
			helper.setTo(email);
			helper.setSubject("Código de Verificación - Archivo UNSM");
			helper.setText(htmlText, true);
			
			javaMailSender.send(message);
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}
}

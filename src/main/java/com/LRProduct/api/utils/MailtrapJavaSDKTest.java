package com.LRProduct.api.utils;

import io.mailtrap.client.MailtrapClient;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.request.emails.Address;
import io.mailtrap.model.request.emails.MailtrapMail;

import java.util.List;

public class MailtrapJavaSDKTest {

    private static final String TOKEN = "9019d19ccf7d23f6373457a23fd6085c";
    private static final String SENDER_EMAIL = "burnvitor2@gmail.com";
    private static final String RECIPIENT_EMAIL = "burniervitor@gmail.com";
    private static final String REPLY_TO_EMAIL = "reply_to@domain.com"; //optional

    public static void main(String[] args) {
        final MailtrapConfig config = new MailtrapConfig.Builder()
                .token(TOKEN)
                .build();

        final MailtrapClient client = MailtrapClientFactory.createMailtrapClient(config);

        final MailtrapMail mail = MailtrapMail.builder()
                .from(new Address(SENDER_EMAIL))
                .to(List.of(new Address(RECIPIENT_EMAIL)))
                .replyTo(new Address(REPLY_TO_EMAIL, "Vincent Vega"))
                .subject("Email enviado.")
                .text("Welcome to Mailtrap Sending!")
                .build();

        // Send email using Mailtrap Sending API
        try {
            System.out.println(client.send(mail));
        } catch (Exception e) {
            System.out.println("Caught exception : " + e);
        }

        // Or send email using Mailtrap Testing API
//        try {
//            long inboxId = 3824065L;
//
//            // Either instantiate a new client
//            MailtrapClient sandboxClient = MailtrapClientFactory.createMailtrapClient(
//                    new MailtrapConfig.Builder()
//                            .sandbox(true)
//                            .inboxId(inboxId)
//                            .token(TOKEN)
//                            .build());
//
//            System.out.println(sandboxClient.send(mail));
//
//            // Or reuse already created client
//            client.switchToEmailTestingApi(inboxId);
//
//            System.out.println(client.send(mail));
//
//            // Or use Testing API directly
//            System.out.println(client.testingApi().emails().send(mail, inboxId));
//        } catch (Exception e) {
//            System.out.println("Caught exception : " + e);
//        }
    }
}
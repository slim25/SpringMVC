package mentorship.program.jms.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Component
public class MessageSender {

    private final String DEFAULT_DESTINATION_BOX = "messageBox";
    private final String AUTHENTICATION_MANAGER_NAME = "AuthenticationManager";

    public static final String ADMIN_DESTINATION_BOX = "messageBox";
    public static final String ADMIN_AUTHENTICATION_MANAGER_NAME = "AdminAuthenticationManager";

    private int counter = 0;
    @Autowired
    private JmsTemplate jmsTemplate;


    public void sendMessage(String destinationBox, String to, String message){
        jmsTemplate.setPriority(1);
        jmsTemplate.convertAndSend(destinationBox, new mentorship.program.model.Message(to,message));
    }

    public void sendFailureMessage(String destinationBox, String to, String message){
        jmsTemplate.setPriority(5);
        jmsTemplate.convertAndSend(destinationBox, new mentorship.program.model.Message(to,message), msg -> {
             msg.setStringProperty("Operation", "ERROR");
            return msg;
        });
    }

    public void sendMessageWithDefaultParameter(String message) {
        jmsTemplate.convertAndSend(DEFAULT_DESTINATION_BOX, new mentorship.program.model.Message(AUTHENTICATION_MANAGER_NAME,message));

        }
    }


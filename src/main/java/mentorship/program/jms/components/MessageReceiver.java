package mentorship.program.jms.components;

import mentorship.program.model.Message;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.annotation.JmsListeners;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

@Component
public class MessageReceiver {

    @JmsListener(destination = "messageBox", containerFactory = "myFactory")
    public void receiveMessage(Message message) throws JMSException {

        System.out.println("Received [message]: <" + message + ">");
    }

    @JmsListener(destination = "messageBox", containerFactory = "myFactory", selector = "Operation = 'ERROR'")
    public void receiveErrorMessage(Message message) {
        System.out.println("Received [ERROR message] : <" + message + ">");
    }

}

package mentorship.program.listener;

import mentorship.program.jms.components.MessageSender;
import mentorship.program.model.persistance.LogginAction;
import mentorship.program.service.JMManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
public class CustomLogoutSuccessHandler extends
        SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private MessageSender messageSener;
    @Autowired
    private JMManagementService jmManagementService;

    @Override
    public void onLogoutSuccess
            (HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        String refererUrl = request.getHeader("Referer");
        String eventInfo = "Event : onLogoutSuccess; refererUrl : " + refererUrl;
        messageSener.sendMessageWithDefaultParameter( eventInfo );
        jmManagementService.saveLogInOutInfo(LogginAction.OUT, authentication.getName(), eventInfo, new Date());
        super.onLogoutSuccess(request, response, authentication);
    }
}

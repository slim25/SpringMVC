package mentorship.program.filter;

import mentorship.program.jms.components.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Oleksandr_Tertyshnyi on 10/28/2016.
 */
@Component
public class PageLoadingFilter implements Filter {

    @Autowired
    private MessageSender messageSener;


    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain)
            throws IOException, ServletException {
        Map<String, String[]> requestParameters = request.getParameterMap();

        if(requestParameters != null && requestParameters.size() != 0) {
            String parameters = requestParameters
                    .entrySet().stream()
                    .map(entry -> entry.getKey() + " : " + Arrays.stream(entry.getValue()).map(e -> e.toString()).collect(Collectors.joining(",")))
                    .collect(Collectors.joining(" ;"));

            System.out.println("Inside custom doFilter");
            messageSener.sendMessageWithDefaultParameter("Event : doFilter with parameters : " + parameters);
        }
        filterChain.doFilter(request,response);

    }

    public void destroy() {
    }
}

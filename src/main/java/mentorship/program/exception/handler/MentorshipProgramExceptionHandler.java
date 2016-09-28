package mentorship.program.exception.handler;

import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Created by Oleksandr_Tertyshnyi on 9/25/2016.
 */

@ControllerAdvice
public class MentorshipProgramExceptionHandler {

    @ExceptionHandler(value = BindException.class)
    public ModelAndView toResponse(Exception exception) {
        exception.printStackTrace();
        ModelAndView view = new ModelAndView();
        view.setViewName("404");
        view.addObject("message", "Validation error : " + exception.getMessage());
        return view;
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView exceptionHandler(Exception exception) {
        ModelAndView view = new ModelAndView();
        view.setViewName("404");
        view.addObject("message", exception.getMessage());
        return view;
    }

}

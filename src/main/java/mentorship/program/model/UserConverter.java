package mentorship.program.model;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by Oleksandr_Tertyshnyi on 10/17/2016.
 */
public class UserConverter implements Converter<String,User> {

    @Override
    public User convert(String s) {

        System.out.println("asfdda");
        return null;
    }
}

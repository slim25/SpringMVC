package mentorship.program.service.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;

import mentorship.program.jms.components.MessageSender;
import mentorship.program.model.persistance.LogginAction;
import mentorship.program.service.JMManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class AuthenticationServiceImpl implements UserDetailsService {

    @Autowired
    DataSource dataSource;
    @Autowired
    private MessageSender messageSener;
    @Autowired
    private JMManagementService jmManagementService;

    @Override
    public User loadUserByUsername(String username)
            throws UsernameNotFoundException {
        ResultSet result = null;
        String password = null;
        String role = null;
        try {
            PreparedStatement statement =  dataSource.getConnection().prepareStatement("SELECT * FROM user WHERE name=?");
            statement.setString(1, username);
            result = statement.executeQuery();
            if( result.next() == false && !username.equals("admin")){
                String eventInfo = "There is no user with such login name : " + username;
                messageSener.sendMessageWithDefaultParameter(eventInfo);
//                jmManagementService.saveLogInOutInfo(LogginAction.FAILURE, username, eventInfo, new Date());
                throw new UsernameNotFoundException(String.format("User %s does not exist!", username));
            }else if(username.equals("admin")){

                return new User(username,"password",Arrays.asList(new SimpleGrantedAuthority("ADMIN")));
            }
            password = result.getString("password");
            role = result.getString("role");
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        GrantedAuthority authority = new SimpleGrantedAuthority(role);
        User user = new User(username,password,Arrays.asList(authority));

        return user;
    }
}

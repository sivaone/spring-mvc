package beans.controllers;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Siva_Alla
 */
@Controller
public class LoginController {
    
    @RequestMapping("/login")
    public String login(HttpServletRequest request){
        System.out.println("Context path: "+request.getContextPath());
        return "login";
    }
    
    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome";
    }
    
    @RequestMapping("/403")
    public String accessDenied(){
        return "403";
    }
    
    @RequestMapping("/loginFailed")
    public String loginFailed(){
        return "loginFailed";
    }
    
    @RequestMapping("/logout")
    public String logout(){
        return "logout";
    }
}

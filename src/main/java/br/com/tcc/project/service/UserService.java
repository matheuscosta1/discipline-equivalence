package br.com.tcc.project.service;

import br.com.tcc.project.security.UserSpringSecurity;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {
    public static UserSpringSecurity authenticated(){
        try {
            return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception exception){
            return null;
        }
    }
}

package br.com.tcc.project.service;

import br.com.tcc.project.command.repositoy.UserRepository;
import br.com.tcc.project.command.repositoy.model.ProfileDocument;
import br.com.tcc.project.command.repositoy.model.UserDocument;
import br.com.tcc.project.domain.Profile;
import br.com.tcc.project.security.UserSpringSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDocument user = userRepository.findByEmail(email);
        if(user == null ) {
            throw new UsernameNotFoundException(email);
        }

        return new UserSpringSecurity(user.getId(), user.getEmail(), user.getPassword(), convertPerfis(user.getPerfis()));
    }


    public Set<Profile> convertPerfis(List<ProfileDocument> profileDocumentList) {
        Set<Profile> profileSet = new HashSet<>();
        profileDocumentList.forEach(profileDocument -> profileSet.add(Profile.toEnum(profileDocument.getPerfil())));
        return profileSet;
    }
}

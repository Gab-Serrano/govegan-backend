package cl.govegan.msauthuser.user.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cl.govegan.msauthuser.user.config.UserDetailsImpl;
import cl.govegan.msauthuser.user.models.User;
import cl.govegan.msauthuser.user.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService{

   @Autowired
   private UserRepository userRepository;

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      
      User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
      
      return new UserDetailsImpl(user);
   }

}

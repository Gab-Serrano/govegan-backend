package cl.govegan.msauth.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

   @PostMapping(value = "user")
   public String user() {
       
       return "User from secure endpoint";
   }
   
   
}

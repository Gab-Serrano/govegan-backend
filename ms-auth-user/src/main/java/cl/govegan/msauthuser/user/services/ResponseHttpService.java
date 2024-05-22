package cl.govegan.msauthuser.user.services;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseHttpService {

   public int status;
   public String message;
   public Object data;
   
}

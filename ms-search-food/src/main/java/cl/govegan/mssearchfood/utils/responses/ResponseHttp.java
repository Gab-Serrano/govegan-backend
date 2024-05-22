package cl.govegan.mssearchfood.utils.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class ResponseHttp<T> {
   private int status;
   private String message;
   private T data;
}

package cl.govegan.mssearchrecipe.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class ResponseEntityBody {
   private String message;
   private Object data;

   public ResponseEntityBody(String message, String status, Object data) {
      this.message = message;
      this.data = data;
   }
}

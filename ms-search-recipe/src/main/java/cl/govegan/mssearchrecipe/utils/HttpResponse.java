package cl.govegan.mssearchrecipe.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class HttpResponse {
   private int status;
   private Object data;
}

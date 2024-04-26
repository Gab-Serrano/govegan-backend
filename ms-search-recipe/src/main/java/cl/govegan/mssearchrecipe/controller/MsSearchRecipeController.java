package cl.govegan.mssearchrecipe.controller;

import org.springframework.web.bind.annotation.RestController;

import cl.govegan.mssearchrecipe.utils.ResponseEntityBody;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1")
public class MsSearchRecipeController {

    @GetMapping("/status")
    public ResponseEntity<ResponseEntityBody> checkApiStatus() {
        return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseEntityBody("API is running", null));
    }

}

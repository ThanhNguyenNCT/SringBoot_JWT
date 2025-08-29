package Cybersoft.ExJPA_Security.controller;

import Cybersoft.ExJPA_Security.payload.request.RegisterRequest;
import Cybersoft.ExJPA_Security.payload.respone.BaseResponse;
import Cybersoft.ExJPA_Security.services.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username,
                                   @RequestParam String password) {
//        SecretKey key = Jwts.SIG.HS256.key().build();
//        String secretKey = Encoders.BASE64.encode(key.getEncoded());
//        System.out.println("Secret Key: " + secretKey);
        String token = authenticationService.checkLogin(username, password);
        boolean issuccess = token != null && !token.isEmpty();
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(issuccess ? 200 : 500);
        baseResponse.setMessage(issuccess ? "Login success" : "Login failed");
        baseResponse.setData(issuccess ? token:null);
        return ResponseEntity.ok(baseResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest){
        authenticationService.register(registerRequest.getUsername(), registerRequest.getPassword());
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(200);
        baseResponse.setMessage("Register successful");
        baseResponse.setData(null);
        return ResponseEntity.ok(baseResponse);
    }
}

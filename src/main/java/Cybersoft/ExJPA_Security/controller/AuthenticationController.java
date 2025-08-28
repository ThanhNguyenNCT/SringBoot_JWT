package Cybersoft.ExJPA_Security.controller;

import Cybersoft.ExJPA_Security.payload.request.RegisterRequest;
import Cybersoft.ExJPA_Security.payload.respone.BaseRespone;
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
        BaseRespone baseRespone = new BaseRespone();
        baseRespone.setCode(issuccess ? 200 : 500);
        baseRespone.setMessage(issuccess ? "Login success" : "Login failed");
        baseRespone.setData(issuccess ? token:null);
        return ResponseEntity.ok(baseRespone);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest){
        authenticationService.register(registerRequest.getUsername(), registerRequest.getPassword());
        BaseRespone baseRespone = new BaseRespone();
        baseRespone.setCode(200);
        baseRespone.setMessage("Register successful");
        baseRespone.setData(null);
        return ResponseEntity.ok(baseRespone);
    }
}

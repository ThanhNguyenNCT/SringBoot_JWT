package Cybersoft.ExJPA_Security.services;

public interface AuthenticationService {
    String checkLogin(String username, String password);
    void register(String username, String password);

}

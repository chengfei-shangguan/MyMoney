package simu.tech.service;


import simu.tech.util.AuthToken;

public interface AuthService {
    AuthToken login(String username, String password, String clientId, String clientSecret);

    void logout(String jti);
}

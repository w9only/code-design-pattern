package cn.yangwq.api.auth;

import org.junit.Test;

public class ApiAuthTest {

    @Test
    public void testApiRequest(){
        String originalUrl = "https://www.yangwq.cn";
        String appId = "testAppId";
        String password = "testPassword";
        Long timestamp = 1610844369726L;
        AuthToken clientToken = AuthToken.generate(originalUrl, appId, password, timestamp);
        ApiRequest apiRequest = new ApiRequest(originalUrl, clientToken.getToken(), appId, timestamp);

        CredentialStorage credentialStorage = new StaticCredentialStorage();
        ApiAuthenticator authenticator = new DefaultApiAuthenticator(credentialStorage);
        authenticator.auth(apiRequest);
    }

    @Test
    public void testFullUrl(){
        String fullUrl = "https://www.yangwq.cn?token=ea53cf16d7473d5b8f2a5bdc0943e4da&appId=testAppId&timestamp=1610844369726";

        CredentialStorage credentialStorage = new StaticCredentialStorage();
        ApiAuthenticator authenticator = new DefaultApiAuthenticator(credentialStorage);
        authenticator.auth(fullUrl);
    }
}

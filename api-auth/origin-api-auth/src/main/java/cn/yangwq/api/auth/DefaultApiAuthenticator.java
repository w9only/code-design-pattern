package cn.yangwq.api.auth;

public class DefaultApiAuthenticator implements ApiAuthenticator {
    private CredentialStorage credentialStorage;

    public DefaultApiAuthenticator(CredentialStorage credentialStorage) {
        this.credentialStorage = credentialStorage;
    }

    /**
     * 通过url解析参数，同时兼容get/post处理
     * @param url
     */
    @Override
    public void auth(String url) {
        auth(ApiRequest.createFromFullUrl(url));
    }

    @Override
    public void auth(ApiRequest apiRequest) {
        String appId = apiRequest.getAppId();
        String token = apiRequest.getToken();
        long timestamp = apiRequest.getTimestamp();
        String originalUrl = apiRequest.getBaseUrl();

        AuthToken clientAuthToken = new AuthToken(token, timestamp);
        if (clientAuthToken.isExpired()) {
            throw new RuntimeException("Token is expired.");
        }
        String password = credentialStorage.getPasswordByAppId(appId);
        AuthToken serverAuthToken = AuthToken.generate(originalUrl, appId, password, timestamp);
        if (!serverAuthToken.match(clientAuthToken)) {
            throw new RuntimeException("Token verfication failed.");
        }
    }

}
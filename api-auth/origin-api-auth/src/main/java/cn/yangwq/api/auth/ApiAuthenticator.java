package cn.yangwq.api.auth;


public interface ApiAuthenticator {

    void auth(String url);

    void auth(ApiRequest apiRequest);
}

package cn.yangwq.api.auth;

public interface CredentialStorage {
    String getPasswordByAppId(String appId);
}

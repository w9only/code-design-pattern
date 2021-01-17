package cn.yangwq.api.auth;

import com.google.common.hash.Hashing;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import static com.google.common.base.Preconditions.checkNotNull;

public  class AuthToken {
    private static final long DEFAULT_EXPIRED_TIME_INTERVAL = 60 * 1000;

    private String token;
    private long createTime;
    private long expiredTimeInterval = DEFAULT_EXPIRED_TIME_INTERVAL;

    public AuthToken(String token, long createTime) {
        this.token = token;
        this.createTime = createTime;
    }

    public AuthToken(String token, long createTime, long expiredTimeInterval) {
        this.token = token;
        this.createTime = createTime;
        this.expiredTimeInterval = expiredTimeInterval;
    }

    public static AuthToken generate(String originalUrl, String appId, String password, Long timestamp) {
        checkNotNull(originalUrl, "url不能为空");
        checkNotNull(appId, "appId不能为空");
        checkNotNull(password, "密码不能为空");
        checkNotNull(timestamp, "时间戳不能为空");

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(originalUrl).append(appId).append(password).append(timestamp);

        String token = Hashing.md5().hashBytes(stringBuffer.toString().getBytes()).toString();
        return new AuthToken(token, new Date().getTime());
    }

    public  String getToken() {
        return this.token;
    }

    public boolean isExpired() {
//        return new Date().getTime() - createTime > this.expiredTimeInterval;
        return false;
    }

    public  boolean match(AuthToken authToken) {
        return token.equals(authToken.getToken());
    }
}

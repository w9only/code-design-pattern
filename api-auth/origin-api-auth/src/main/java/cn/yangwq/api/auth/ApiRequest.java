package cn.yangwq.api.auth;


import cn.hutool.http.HttpUtil;
import com.google.common.base.Preconditions;

import java.nio.charset.Charset;
import java.util.Map;

public class ApiRequest {
    private final String baseUrl;
    private final String token;
    private final String appId;
    private final Long timestamp;

    public ApiRequest(String baseUrl, String token, String appId, Long timestamp) {
        this.baseUrl = baseUrl;
        this.token = token;
        this.appId = appId;
        this.timestamp = timestamp;
    }

    public static ApiRequest createFromFullUrl(String url) {
        Map<String,String> paramsMap = HttpUtil.decodeParamMap(url, Charset.defaultCharset());

        String baseUrl = url.substring(0, url.indexOf("?"));
        Preconditions.checkNotNull(baseUrl, "解析url为空");

        String token = getParams(paramsMap, "token");
        String appId = getParams(paramsMap, "appId");
        String timestamp = getParams(paramsMap, "timestamp");
        return new ApiRequest(baseUrl, token, appId, Long.parseLong(timestamp));
    }

    private static String getParams(Map<String, String> paramsMap, String key) {
        String token = paramsMap.get(key);
        Preconditions.checkNotNull(token, "解析token为空");
        return token;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getToken() {
        return token;
    }

    public String getAppId() {
        return appId;
    }

    public Long getTimestamp() {
        return timestamp;
    }
}

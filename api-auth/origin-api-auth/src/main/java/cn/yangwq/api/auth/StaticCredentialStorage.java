package cn.yangwq.api.auth;

import cn.hutool.core.lang.UUID;
import com.google.common.collect.ImmutableMap;

import java.util.List;
import java.util.Map;

public class StaticCredentialStorage implements CredentialStorage{
    private static   ImmutableMap<String, String> queries = null;

    static {
        ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();

        builder.put("testAppId", "testPassword");

        queries = builder.build();
    }

    @Override
    public String getPasswordByAppId(String appId) {
        return queries.get(appId);
    }
}

package com.logpresso.sonar.onepassword.impl;

import org.apache.felix.ipojo.annotations.*;
import org.araqne.log.api.LoggerConfigOption;
import org.araqne.log.api.MutableStringConfigType;
import org.logpresso.api.profile.AbstractConnectProfileFactory;
import org.logpresso.api.profile.ConnectProfile;
import org.logpresso.api.profile.ConnectProfileFactoryRegistry;

import java.util.*;

@Component(name = "one-password-connect-profile-factory")
@Instantiate
public class OnePasswordConnectProfileFactory extends AbstractConnectProfileFactory {
    @Requires
    private ConnectProfileFactoryRegistry connectProfileFactoryRegistry;

    @Validate
    public void start() {
        connectProfileFactoryRegistry.addFactory(this);
    }

    @Invalidate
    public void stop() {
        if (connectProfileFactoryRegistry != null)
            connectProfileFactoryRegistry.removeFactory(this);
    }

    @Override
    public String getType() {
        return "one-password";
    }

    @Override
    public String getDisplayName( Locale locale) {
        if (locale != null && locale.equals(Locale.KOREAN))
            return "원 패스워드";
        return "One Password";
    }

    @Override
    public String getDescription(Locale locale) {
        if (locale != null && locale.equals(Locale.KOREAN))
            return "원 패스워드의 연결 속성을 관리합니다.";
        return "Manage connection properties of One Password.";
    }

    @Override
    public Set<String> getProtectedConfigKeys() {
        return Set.of("api_key");
    }

    @Override
    public List<LoggerConfigOption> getConfigOptions() {
        LoggerConfigOption endpoint = new MutableStringConfigType("endpoint", t("Endpoint", "엔드포인트"),
                t("REST API URL", "REST API 주소"), true);
        LoggerConfigOption apiKey = new MutableStringConfigType("api_key", t("API key", "API 키"),
                t("GUID format", "GUID 형식"), true);

        return Arrays.asList(endpoint, apiKey);
    }

    @Override
    public String describeConfigs( ConnectProfile profile, Locale locale) {
        String endpoint = profile.getString("endpoint");
        if (locale != null && locale.equals(Locale.KOREAN))
            return "엔드포인트: " + endpoint;
        return "Endpoint: " + endpoint;
    }

    private Map<Locale, String> t(String en, String ko) {
        Map<Locale, String> m = new HashMap<Locale, String>();
        m.put(Locale.ENGLISH, en);
        m.put(Locale.KOREAN, ko);
        return m;
    }
}

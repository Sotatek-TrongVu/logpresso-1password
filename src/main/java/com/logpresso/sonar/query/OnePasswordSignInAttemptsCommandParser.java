package com.logpresso.sonar.query;

import org.araqne.log.api.ValueType;
import org.araqne.logdb.QueryCommand;
import org.araqne.logdb.QueryContext;

import java.util.Locale;

public class OnePasswordSignInAttemptsCommandParser  extends OnePasswordQueryCommandParser{

    public OnePasswordSignInAttemptsCommandParser() {
        setDescription(Locale.ENGLISH, "Get events of one password sign in attempts.");
        setDescription(Locale.KOREAN, "원 패스워드 로그인 시도 이벤트를 조회합니다.");

        setOutput("uuid", ValueType.STRING, Locale.ENGLISH, "UUID", "");
        setOutput("session_uuid", ValueType.STRING, Locale.ENGLISH, "Session UUID", "");
        setOutput("timestamp", ValueType.DATE, Locale.ENGLISH, "Timestamp", "");
        setOutput("country", ValueType.STRING, Locale.ENGLISH, "Country", "");
        setOutput("category", ValueType.STRING, Locale.ENGLISH, "Category", "");
        setOutput("type", ValueType.STRING, Locale.ENGLISH, "Type", "");
        setOutput("details", ValueType.LIST, Locale.ENGLISH, "Details", "");
        setOutput("target_user", ValueType.MAP, Locale.ENGLISH, "Target user", "");
        setOutput("client", ValueType.MAP, Locale.ENGLISH, "Client", "");
        setOutput("location", ValueType.MAP, Locale.ENGLISH, "Location", "");

        setOutput("uuid", ValueType.STRING, Locale.KOREAN, "UUID", "");
        setOutput("session_uuid", ValueType.STRING, Locale.KOREAN, "Session UUID", "");
        setOutput("timestamp", ValueType.DATE, Locale.KOREAN, "Timestamp", "");
        setOutput("country", ValueType.STRING, Locale.KOREAN, "Country", "");
        setOutput("category", ValueType.STRING, Locale.KOREAN, "Category", "");
        setOutput("type", ValueType.STRING, Locale.KOREAN, "Type", "");
        setOutput("details", ValueType.LIST, Locale.KOREAN, "Details", "");
        setOutput("target_user", ValueType.MAP, Locale.KOREAN, "Target user", "");
        setOutput("client", ValueType.MAP, Locale.KOREAN, "Client", "");
        setOutput("location", ValueType.MAP, Locale.KOREAN, "Location", "");


    }
    private static final String commandName = "one-password-signin-attempts";
    @Override
    protected QueryCommand parse( QueryContext context, OnePasswordParams params ) {
        return new OnePasswordSignInAttemptsCommand(params);
    }

    @Override
    public String getCommandName() {
        return commandName;
    }
}

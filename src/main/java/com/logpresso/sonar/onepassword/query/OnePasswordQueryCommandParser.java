package com.logpresso.sonar.onepassword.query;

import org.araqne.logdb.QueryCommand;
import org.araqne.logdb.QueryContext;
import org.araqne.logdb.QueryErrorMessage;
import org.logpresso.api.profile.query.ConnectProfileParams;
import org.logpresso.api.profile.query.ConnectProfileQueryCommandParser;

import java.util.HashMap;
import java.util.Map;

public abstract class OnePasswordQueryCommandParser extends ConnectProfileQueryCommandParser {
    protected static final String ERR_SERVICE_UNAVAILABLE = "204100";
    protected static final String ERR_PROFILE_REQUIRED = "204101";
    protected static final String ERR_QUERY_LIMIT_REQUIRED = "204102";
    protected static final String ERR_QUERY_FROM_REQUIRED = "204103";
    protected static final String ERR_QUERY_TO_REQUIRED = "204104";
    private static final String profileType = "one-password";

    public OnePasswordQueryCommandParser() {
        super(profileType, ERR_SERVICE_UNAVAILABLE, ERR_PROFILE_REQUIRED);
    }

    protected abstract QueryCommand parse( QueryContext context, OnePasswordParams params );

    @Override
    protected ConnectProfileParams parseParams( QueryContext context, Map<String, String> opts ) {
        OnePasswordParams params = new OnePasswordParams();
        params.setLimit(Integer.parseInt(opts.get("limit")));
        params.setQueryFrom(opts.get("from"));
        params.setQueryTo(opts.get("to"));
        return params;
    }

    @Override
    protected QueryCommand parse( QueryContext context, ConnectProfileParams params, String commandString ) {
        return parse(context, (OnePasswordParams) params);
    }

    @Override
    public Map<String, QueryErrorMessage> getErrorMessages() {
        Map<String, QueryErrorMessage> errors = new HashMap<>();
        errors.put(ERR_SERVICE_UNAVAILABLE, newMsg("No available one password profile found.", "사용 가능한 샘플 프로파일이 없습니다."));
        errors.put(ERR_PROFILE_REQUIRED, newMsg("Specify valid one password profile.", "샘플 프로파일 이름을 입력해주세요."));
        errors.put(ERR_QUERY_LIMIT_REQUIRED, newMsg("Specify limit option in the one-password-signin-attempts command.", "one-password-signin-attempts 명령에 limit 옵션을 지정해주세요."));
        errors.put(ERR_QUERY_FROM_REQUIRED, newMsg("Specify from option in the one-password-signin-attempts command.", "one-password-signin-attempts 명령에 from 옵션을 지정해주세요."));
        errors.put(ERR_QUERY_TO_REQUIRED, newMsg("Specify to option in the one-password-signin-attempts command.", "one-password-signin-attempts 명령에 to 옵션을 지정해주세요."));
        return errors;
    }

    private QueryErrorMessage newMsg( String en, String ko ) {
        return new QueryErrorMessage(en, ko);
    }


}

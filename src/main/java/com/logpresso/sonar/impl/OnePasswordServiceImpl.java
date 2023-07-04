package com.logpresso.sonar.impl;

import com.logpresso.sonar.query.OnePasswordQueryCommandParser;
import com.logpresso.sonar.query.OnePasswordSignInAttemptsCommandParser;
import org.apache.felix.ipojo.annotations.*;
import org.araqne.logdb.QueryParserService;
import org.logpresso.api.profile.ConnectProfileService;

import java.util.Arrays;
import java.util.List;

@Component(name = "one-password-service")
@Instantiate
public class OnePasswordServiceImpl {
    @Requires
    private QueryParserService queryParserService;

    @Requires
    private ConnectProfileService connectProfileService;

    private OnePasswordQueryCommandParser signinAttemptParser = new OnePasswordSignInAttemptsCommandParser();

    @Validate
    public void start() {
        for (OnePasswordQueryCommandParser parser : getParsers()) {
            parser.setConnectProfileService(connectProfileService);
            queryParserService.addCommandParser(parser);
        }
    }

    @Invalidate
    public void stop() {
        if (queryParserService == null)
            return;

        for (OnePasswordQueryCommandParser parser : getParsers()) {
            parser.setConnectProfileService(null);
            queryParserService.removeCommandParser(parser);
        }
    }

    private List<OnePasswordQueryCommandParser> getParsers() {
        return Arrays.asList(signinAttemptParser);
    }

}

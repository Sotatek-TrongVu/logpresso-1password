package com.logpresso.sonar.onepassword.query;

import org.araqne.log.api.RestApiClient;
import org.araqne.log.api.RestApiResponse;
import org.araqne.logdb.Row;
import org.logpresso.api.profile.ConnectProfile;

import java.io.IOException;
import java.net.URL;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OnePasswordSignInAttemptsCommand extends OnePasswordDriverQueryCommand {
    final private String path;
    public OnePasswordSignInAttemptsCommand( OnePasswordParams params) {
        super(params);
        path = "/api/v1/signinattempts";
    }


    @Override
    public String getName() {
        return "one-password-signin-attempts";
    }

    @Override
    public void run(ConnectProfile profile) throws IOException {
        RestApiClient client = newClient(profile);
        URL url = toUrl(profile, path);

        Map<String,Object> body = new HashMap<String,Object>();

        if (params.getLimit() != null){
            body.put("limit", params.getLimit());
        }

        if (params.getQueryFrom() != null){
            body.put("start_time", params.getQueryFrom());
        }

        if (params.getQueryTo() != null){
            body.put("end_time", params.getQueryTo());
        }

        RestApiResponse resp = client.postJson(url, body);
        List<Object> events = (List<Object>) resp.getJson().get("items");
        for (Object event : events) {
            Map<String, Object> m = (Map<String, Object>) event;
            m.put("timestamp", parseDate((String) m.get("timestamp")));
            pushPipe(new Row(m));
        }
    }

    @Override
    public List<String> getFieldOrder() {
        return null;
    }
    private Date parseDate( String s) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        return df.parse(s, new ParsePosition(0));
    }

}

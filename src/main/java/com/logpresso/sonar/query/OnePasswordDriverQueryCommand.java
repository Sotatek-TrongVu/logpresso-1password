package com.logpresso.sonar.query;

import org.araqne.log.api.RestApiClient;
import org.araqne.logdb.DriverQueryCommand;
import org.araqne.logdb.FieldOrdering;
import org.araqne.logdb.Strings;
import org.logpresso.api.profile.ConnectProfile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class OnePasswordDriverQueryCommand extends DriverQueryCommand implements FieldOrdering {
    protected OnePasswordParams params;

    OnePasswordDriverQueryCommand(OnePasswordParams params) {
        this.params = params;
    }
    protected abstract void run(ConnectProfile profile) throws IOException;

    @Override
    public void run() {
        for (ConnectProfile profile : params.getProfiles()) {
            if (isCancelRequested())
                return;

            try {
                run(profile);
            } catch (Throwable t) {
                String msg = String.format("%s (profile %s) error - %s", getName(), profile.getName(), t.getStackTrace());
                throw new IllegalStateException(msg, t);
            }
        }
    }

    protected RestApiClient newClient(ConnectProfile profile) {
        String apiKey = profile.getString("api_key");
        RestApiClient client = new RestApiClient();
        client.setHeader("Authorization", "Bearer " + apiKey);
        return client;
    }

    protected URL toUrl(ConnectProfile profile, String path) {
        String endpoint = profile.getString("endpoint");
        String url = endpoint + path;
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            throw new IllegalStateException("malformed sample endpoint: " + url);
        }
    }

}

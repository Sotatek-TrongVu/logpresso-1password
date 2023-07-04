package com.logpresso.sonar.onepassword;

import com.logpresso.sonar.onepassword.query.OnePasswordParams;
import com.logpresso.sonar.onepassword.query.OnePasswordSignInAttemptsCommand;
import org.logpresso.api.profile.ConnectProfile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        OnePasswordParams params = new OnePasswordParams();
        params.setLimit(10);
        params.setQueryFrom("20230315163250");


        ConnectProfile profile = new ConnectProfile();
        Map<String,Object> config = new HashMap<String,Object>();
        config.put("endpoint", "https://events.1password.com");
        config.put("api_key", "eyJhbGciOiJFUzI1NiIsImtpZCI6Imx1cGN0aWZvanFxdndnN2lscnJwcWRjZzJpIiwidHlwIjoiSldUIn0.eyIxcGFzc3dvcmQuY29tL2F1dWlkIjoiQjdXUERLUTZHRkdNSlA2WDVMWFpYT1FPWEEiLCIxcGFzc3dvcmQuY29tL2Z0cyI6WyJzaWduaW5hdHRlbXB0cyIsIml0ZW11c2FnZXMiLCJhdWRpdGV2ZW50cyJdLCJhdWQiOlsiZXZlbnRzLjFwYXNzd29yZC5jb20iXSwic3ViIjoiRUpNTko1Q09MSkIzWENaSDI0VzZZR1NCUVEiLCJpYXQiOjE2ODg0NjIyMTYsImlzcyI6ImNvbS4xcGFzc3dvcmQuYjUiLCJqdGkiOiJpbnd0dnM2cjZmZmJmdDVhaWNzbTd4dnhnZSJ9.6sGEPgd7Ejg3rmmdxDxFl0yfhfJvphOzPzFfuYvmvGs8IGXp-5bUI_cVwfKxPN9bk6pFipDgtSYTRTDml0wwFw");
        profile.setName("test");
        profile.setConfigs(config);
        profile.setType("onepassword");
        profile.setDescription("test");

        params.setProfiles(List.of(profile));
        OnePasswordSignInAttemptsCommand query = new OnePasswordSignInAttemptsCommand(params);

        try {
            query.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
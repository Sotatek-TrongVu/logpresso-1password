package com.logpresso.sonar.query;

import org.logpresso.api.profile.query.ConnectProfileParams;

public class OnePasswordParams extends ConnectProfileParams {

    private Number limit;
    private String queryFrom;
    private String queryTo;


    public Number getLimit() {
        return limit;
    }

    public void setLimit( Number limit ) {
        this.limit = limit;
    }

    public String getQueryFrom() {
        return queryFrom;
    }

    public void setQueryFrom( String queryFrom ) {
        this.queryFrom = queryFrom;
    }

    public String getQueryTo() {
        return queryTo;
    }

    public void setQueryTo( String queryTo ) {
        this.queryTo = queryTo;
    }

}

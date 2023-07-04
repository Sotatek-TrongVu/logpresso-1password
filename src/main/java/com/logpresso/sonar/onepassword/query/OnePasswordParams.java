package com.logpresso.sonar.onepassword.query;

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
        this.queryFrom = queryFrom.substring(0, 4) + "-" + queryFrom.substring(4, 6) + "-" + queryFrom.substring(6, 8) + "T" + queryFrom.substring(8, 10) + ":" + queryFrom.substring(10, 12) + ":" + queryFrom.substring(12, 14) + "-00:00";
    }

    public String getQueryTo() {
        return queryTo;
    }

    public void setQueryTo( String queryTo ) {
        this.queryTo = queryTo.substring(0, 4) + "-" + queryTo.substring(4, 6) + "-" + queryTo.substring(6, 8) + "T" + queryTo.substring(8, 10) + ":" + queryTo.substring(10, 12) + ":" + queryTo.substring(12, 14) + "-00:00";

    }

}

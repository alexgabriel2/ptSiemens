package com.siemens.backend.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserSearchRequest {
    private double latitude;
    private double longitude;
    private double distance;

    @Override
    public String toString() {
        return "UserSearchRequest{" +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", distance=" + distance +
                '}';
    }
}

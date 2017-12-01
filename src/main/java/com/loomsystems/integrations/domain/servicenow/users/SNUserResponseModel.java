package com.loomsystems.integrations.domain.servicenow.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SNUserResponseModel {
    @JsonProperty("sys_id")
    private String id;
    private String state;
    private String zip;
    @JsonProperty("home_phone")
    private String homePhone;
    private String phone;
    @JsonProperty("time_format")
    private String timeFormat;
    @JsonProperty("date_format")
    private String dateFormat;
    private String name;
    private String city;
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("mobile_phone")
    private String mobilePhone;
    private String street;
    @JsonProperty("first_name")
    private String firstName;
    private String email;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("middle_name")
    private String middleName;
    @JsonProperty("time_zone")
    private String timeZone;
}

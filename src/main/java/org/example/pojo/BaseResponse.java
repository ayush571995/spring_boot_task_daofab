package org.example.pojo;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class BaseResponse {
    @JsonProperty("statusCode")
    private int statusCode;
    @JsonProperty("message")
    private String message;

    public BaseResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}

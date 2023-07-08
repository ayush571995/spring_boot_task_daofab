package org.example.pojo;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
public class SuccessResponse extends BaseResponse {
    @JsonProperty("data")
    private Object data;

    public SuccessResponse(Object data){
        super(0, "");
        this.data = data;
    }
    public SuccessResponse(int statusCode, String message, Object data) {
        super(0, message);
        this.data = data;
    }
}

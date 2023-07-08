package org.example.pojo;

public class ErrorResponse  extends BaseResponse{
    private int errorCode;
    public ErrorResponse(String message, int errorCode) {
        super(1, message);
        this.errorCode = errorCode;
    }
}

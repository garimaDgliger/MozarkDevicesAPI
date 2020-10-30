package ai.mozark.devices.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseObject {

    private long statusCode;

  private String statusMessage;

    private Object payload;

    private boolean status;

    public ResponseObject( Object payload) {
        this.payload=payload;
    }


    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public ResponseObject(long statusCode, String statusMessage, Object payload, Boolean status) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.status=status;
        this.payload=payload;
    }
    public ResponseObject(long statusCode, String statusMessage, Boolean status) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.status=status;
    }

    public void setStatusCode(long statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public long getStatusCode() {
        return statusCode;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }
}

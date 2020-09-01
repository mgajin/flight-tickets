package app.utils;

import com.google.gson.Gson;

public class ErrorResponse {

    private static final Gson gson = new Gson();

    private boolean success;
    private String message;

    public ErrorResponse() {
        this.success = false;
        this.message = "";
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toJson() {
        return gson.toJson(this);
    }
}

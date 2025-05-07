package models.responses;

public class SessionResponse {
    private String token;

    public SessionResponse() {}

    public SessionResponse(String token) {
        this.token = token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}

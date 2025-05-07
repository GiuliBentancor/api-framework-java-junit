package base;

import java.util.Date;

public class Token {
    private String value;
    private Long timestamp;

    public Token(String value) {
        this.value = value;
        this.timestamp = new Date().getTime();
    }

    public String getValue() {
        return value;
    }

    public Long getTimestamp() {
        return timestamp;
    }
}

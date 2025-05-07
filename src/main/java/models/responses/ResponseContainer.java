package models.responses;

public class ResponseContainer<T> {
    private T data;
    private Integer status;
    private String headers;
    private Long responseTime;

    public ResponseContainer(
        T data,
        Integer status,
        String headers,
        Long responseTime
    ) {
        this.data = data;
        this.status = status;
        this.headers = headers;
        this.responseTime = responseTime;
    }

    public T getData() {
        return data;
    }

    public Integer getStatus() {
        return status;
    }

    public String getHeaders() {
        return headers;
    }

    public Long getResponseTime() {
        return responseTime;
    }
}

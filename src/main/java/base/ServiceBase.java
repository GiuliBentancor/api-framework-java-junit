package base;

import io.restassured.response.Response;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.requests.CredentialModel;
import models.responses.ResponseContainer;
import models.responses.SessionResponse;
import utils.EnvironmentUtils;
import utils.ErrorMessages;
import utils.MapUtils;
import utils.StringUtils;

public class ServiceBase {
    protected ApiClient apiClient;
    protected String url;
    protected Map<String, String> defaultHeaders;

    public ServiceBase(String endpointPath) {
        this.apiClient = ApiClient.getInstace();
        this.url = baseUrl() + endpointPath;
        this.defaultHeaders = new HashMap<>();
    }

    private static String baseUrl() {
        String url = EnvironmentUtils.getInstance().get("BASEURL");
        if (url == null) url = "";

        return url;
    }

    public final void authenticate() {
        String username = EnvironmentUtils.getInstance().get("USER");
        String password = EnvironmentUtils.getInstance().get("PASSWORD");

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new RuntimeException(ErrorMessages.MISSING_USERNAME_PASSWORD);
        }

        String token = SessionManager.getCachedToken(username, password);
        if (!StringUtils.isEmpty(token)) {
            saveCookieInHeaders(token);
            return;
        }

        CredentialModel credentialRequest = new CredentialModel(username, password);

        ResponseContainer<SessionResponse> loginResponse = this.post(baseUrl() + "/auth", credentialRequest,
            null, SessionResponse.class);

        token = loginResponse.getData().getToken();

        SessionManager.storeToken(username, password, token);
        saveCookieInHeaders(token);
    }

    private void saveCookieInHeaders(String token) {
        this.defaultHeaders.put("Cookie", buildCookie(token));
    }

    private String buildCookie(String token) {
        return "token=" + token;
    }

    private <T> ResponseContainer<T> buildResponse(
        Long endTime,
        Long startTime,
        Response response,
        Class<T> responseClass
    ) {
        Long responseTime = endTime - startTime;

        int status = response.statusCode();
        String headers = response.headers().toString();

        if (status < 200 || status >= 300) {
            return new ResponseContainer<>(null, status, headers, responseTime);
        }

        T data = response.body().as(responseClass);
        return new ResponseContainer<>(data, status, headers, responseTime);
    }

    protected <T> ResponseContainer<T> getSingleObject(
        String url,
        Map<String, String> headers,
        Class<T> responseClass
    ) {
        Map<String, String> finalHeaders = MapUtils.combineMaps(
            defaultHeaders,
            headers
        );

        Long startTime = new Date().getTime();
        Response response = apiClient.get(url, finalHeaders);
        Long endTime = new Date().getTime();

        return buildResponse(endTime, startTime, response, responseClass);
    }

    protected ResponseContainer getListOfObjects(
        String url,
        Map<String, String> headers
    ) {
        Map<String, String> finalHeaders = MapUtils.combineMaps(
            defaultHeaders,
            headers
        );

        Long startTime = new Date().getTime();
        Response response = apiClient.get(url, finalHeaders);
        Long endTime = new Date().getTime();

        return buildResponse(endTime, startTime, response, List.class);
    }

    protected <T> ResponseContainer<T> post(
        String url,
        Object payload,
        Map<String, String> headers,
        Class<T> responseClass
    ) {
        Map<String, String> finalHeaders = MapUtils.combineMaps(
            defaultHeaders,
            headers
        );

        Long startTime = new Date().getTime();
        Response response = apiClient.post(url, payload, finalHeaders);
        Long endTime = new Date().getTime();

        return buildResponse(endTime, startTime, response, responseClass);
    }

    protected <T> ResponseContainer<T> put(
        String url,
        Object payload,
        Map<String, String> headers,
        Class<T> responseClass
    ) {
        Map<String, String> finalHeaders = MapUtils.combineMaps(
            defaultHeaders,
            headers
        );

        Long startTime = new Date().getTime();
        Response response = apiClient.put(url, payload, finalHeaders);
        Long endTime = new Date().getTime();

        return buildResponse(endTime, startTime, response, responseClass);
    }

    protected <T> ResponseContainer<T> patch(
        String url,
        Object payload,
        Map<String, String> headers,
        Class<T> responseClass
    ) {
        Map<String, String> finalHeaders = MapUtils.combineMaps(
            defaultHeaders,
            headers
        );

        Long startTime = new Date().getTime();
        Response response = apiClient.patch(url, payload, finalHeaders);
        Long endTime = new Date().getTime();

        return buildResponse(endTime, startTime, response, responseClass);
    }

    protected <T> ResponseContainer<T> delete(
        String url,
        Object payload,
        Map<String, String> headers,
        Class<T> responseClass
    ) {
        Map<String, String> finalHeaders = MapUtils.combineMaps(
            defaultHeaders,
            headers
        );

        Long startTime = new Date().getTime();
        Response response = apiClient.delete(url, payload, finalHeaders);
        Long endTime = new Date().getTime();

        return buildResponse(endTime, startTime, response, responseClass);
    }

    protected <T> ResponseContainer<T> head(
        String url,
        Object payload,
        Map<String, String> headers,
        Class<T> responseClass
    ) {
        Map<String, String> finalHeaders = MapUtils.combineMaps(
            defaultHeaders,
            headers
        );

        Long startTime = new Date().getTime();
        Response response = apiClient.head(url, payload, finalHeaders);
        Long endTime = new Date().getTime();

        return buildResponse(endTime, startTime, response, responseClass);
    }

    protected <T> ResponseContainer<T> options(
        String url,
        Object payload,
        Map<String, String> headers,
        Class<T> responseClass
    ) {
        Map<String, String> finalHeaders = MapUtils.combineMaps(
            defaultHeaders,
            headers
        );

        Long startTime = new Date().getTime();
        Response response = apiClient.options(url, payload, finalHeaders);
        Long endTime = new Date().getTime();

        return buildResponse(endTime, startTime, response, responseClass);
    }
}

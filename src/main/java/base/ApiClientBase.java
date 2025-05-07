package base;

import io.restassured.RestAssured;
import java.util.HashMap;
import java.util.Map;
import utils.MapUtils;

public abstract class ApiClientBase {
    private Map<String, String> baseHeaders;

    protected ApiClientBase() {
        baseHeaders = new HashMap<>();
        baseHeaders.put("Content-Type", "application/json");
        baseHeaders.put("Accept", "application/json");
    }

    public io.restassured.response.Response get(
        String url,
        Map<String, String> headers
    ) {
        Map<String, String> finalHeaders = MapUtils.combineMaps(
            baseHeaders,
            headers
        );

        return RestAssured.with().headers(finalHeaders).get(url);
    }

    public io.restassured.response.Response put(
        String url,
        Object payload,
        Map<String, String> headers
    ) {
        Map<String, String> finalHeaders = MapUtils.combineMaps(
            baseHeaders,
            headers
        );

        return RestAssured.with().body(payload).headers(finalHeaders).put(url);
    }

    public io.restassured.response.Response post(
        String url,
        Object payload,
        Map<String, String> headers
    ) {
        Map<String, String> finalHeaders = MapUtils.combineMaps(
            baseHeaders,
            headers
        );

        return RestAssured.with().body(payload).headers(finalHeaders).post(url);
    }

    public io.restassured.response.Response patch(
        String url,
        Object payload,
        Map<String, String> headers
    ) {
        Map<String, String> finalHeaders = MapUtils.combineMaps(
            baseHeaders,
            headers
        );

        return RestAssured
            .with()
            .body(payload)
            .headers(finalHeaders)
            .patch(url);
    }

    public io.restassured.response.Response delete(
        String url,
        Object payload,
        Map<String, String> headers
    ) {
        Map<String, String> finalHeaders = MapUtils.combineMaps(
            baseHeaders,
            headers
        );

        return RestAssured
            .with()
            .body(payload)
            .headers(finalHeaders)
            .delete(url);
    }

    public io.restassured.response.Response head(
        String url,
        Object payload,
        Map<String, String> headers
    ) {
        Map<String, String> finalHeaders = MapUtils.combineMaps(
            baseHeaders,
            headers
        );

        return RestAssured.with().body(payload).headers(finalHeaders).head(url);
    }

    public io.restassured.response.Response options(
        String url,
        Object payload,
        Map<String, String> headers
    ) {
        Map<String, String> finalHeaders = MapUtils.combineMaps(
            baseHeaders,
            headers
        );

        return RestAssured
            .with()
            .body(payload)
            .headers(finalHeaders)
            .options(url);
    }
}

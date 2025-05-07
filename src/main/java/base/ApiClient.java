package base;

public class ApiClient extends ApiClientBase {
    private static ApiClient instance;

    private ApiClient() {
        super();
    }

    public static ApiClient getInstace() {
        if (instance == null) {
            instance = new ApiClient();
        }

        return instance;
    }
}

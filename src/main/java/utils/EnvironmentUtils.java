package utils;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvironmentUtils {
    public static EnvironmentUtils instance;

    public static EnvironmentUtils getInstance() {
        if (instance == null) {
            instance = new EnvironmentUtils();
        }

        return instance;
    }

    private Dotenv envVars;

    private EnvironmentUtils() {
        try {
            envVars = Dotenv.configure().load();
        } catch (Exception e) {
            System.out.println(
                "No .env file found. Variables available only from the ones configured at the systems environment"
            );
        }
    }

    public String get(String name) {
        if (envVars != null) {
            return envVars.get(name);
        }

        return System.getenv(name);
    }
}

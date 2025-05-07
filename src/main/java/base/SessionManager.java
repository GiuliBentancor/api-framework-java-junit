package base;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SessionManager {
    private static final Integer TOKEN_EXPIRY_DURATION = 15 * 60 * 1000;
    private static final Map<String, Token> authTokenCache = new HashMap<>();

    public static String getCachedToken(String username, String password) {
        String cacheKey = createCacheKey(username, password);
        Token cachedData = SessionManager.authTokenCache.get(cacheKey);

        if (cachedData != null) {
            Date currentTime = new Date();
            Long tokenAge = currentTime.getTime() - cachedData.getTimestamp();

            if (tokenAge < TOKEN_EXPIRY_DURATION) {
                return cachedData.getValue();
            }
        }

        return null;
    }

    public static void storeToken(
        String username,
        String password,
        String token
    ) {
        String cacheKey = createCacheKey(username, password);
        Token newToken = new Token(token);
        authTokenCache.put(cacheKey, newToken);
    }

    private static String createCacheKey(String username, String password) {
        return username + ":" + password;
    }
}

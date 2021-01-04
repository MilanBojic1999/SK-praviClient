package sk.micorservise.kilejnt;

public class Tokens {
    public static final String userUrl = "http://localhost:8999/registrator";
    public static final String flightUrl = "http://localhost:8999/letovi";
    public static final String ticketUrl = "http://localhost:8999/karte";

    public static final String HEADER_STRING = "Authorization";

    private static String userToken = "";

    public static String getUserToken() {
        return userToken;
    }

    public static void setUserToken(String userToken) {
        Tokens.userToken = userToken;
    }
}

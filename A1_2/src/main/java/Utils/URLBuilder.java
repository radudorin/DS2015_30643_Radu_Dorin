package Utils;

import Cnstants.Assignment1Constants;

/**
 * Created by radud on 05/11/2015.
 */
public class URLBuilder {

    private static final String FORMAT = "json";
    public static final String GOOGLE_API_URL = "https://maps.googleapis.com/maps/api/geocode/";

    public static String buildURL(String lat, String lng) {
        String url = GOOGLE_API_URL + FORMAT + "?latlng=" + lat + "," + lng + "&key=" + Assignment1Constants.GOOGLE_API_KEY;
        System.out.println(url);
        return url;
    }

    public static String buildURL(String city) {
        String url = GOOGLE_API_URL + FORMAT + "?address=" + city + "&key=" + Assignment1Constants.GOOGLE_API_KEY;
        System.out.println(url);
        return url;
    }

}

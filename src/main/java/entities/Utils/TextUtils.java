package entities.Utils;

/**
 * Created by radud on 28/10/2015.
 */
public class TextUtils {

    public static boolean isEmpty(String string) {
        if (string == null || string.length() == 0 || string.equals("")) {
            return true;
        }

        return false;
    }

}

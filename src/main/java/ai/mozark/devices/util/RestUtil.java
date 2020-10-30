package ai.mozark.devices.util;

public class RestUtil {

    public boolean isEmpty(String test) {
        if ((test == null) || (test.trim().isEmpty() == true)
                || (test.equalsIgnoreCase("[]")) || (test == "")) {
            return true;
        }
        return false;
    }

}

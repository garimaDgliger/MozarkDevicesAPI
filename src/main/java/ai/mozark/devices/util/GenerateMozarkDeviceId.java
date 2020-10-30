package ai.mozark.devices.util;

import java.util.Random;

public class GenerateMozarkDeviceId {
    static int deviceIdLength=16;

    public static String GetDeviceId() {
        System.out.print("You DeviceId is : ");

        String numbers = "0123456789ABCDEFGHIJKLMNOPQRSTWXYZ";

        // Using random method
        Random rndm_method = new Random();

        String otp = "";

        for (int i = 0; i < deviceIdLength; i++){
            otp += numbers.charAt(rndm_method.nextInt(numbers.length()));
        }
        return "MOZARK-"+otp;
    }
}

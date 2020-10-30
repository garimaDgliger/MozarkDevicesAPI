package ai.mozark.devices.utils.aws;

import com.amazonaws.services.devicefarm.AWSDeviceFarm;
import com.amazonaws.services.devicefarm.AWSDeviceFarmClientBuilder;

public class MyAwsClient {
    private static AWSDeviceFarm client;
    private MyAwsClient() {
        client = AWSDeviceFarmClientBuilder.standard().build();
    }

    public static AWSDeviceFarm getClient() {
        if(client == null) {
            new MyAwsClient();
        }
        return client;
    }
}

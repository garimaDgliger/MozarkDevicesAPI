package ai.mozark.devices.utils.device;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface DeviceListUtils {
    JSONObject getDeviceLists(JSONObject filters);
    JSONArray getDeviceList(JSONArray devices);
}

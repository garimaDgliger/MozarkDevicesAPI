package ai.mozark.devices.utils.device;

import ai.mozark.devices.utils.aws.DeviceUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AwsDeviceListUtils  implements  DeviceListUtils{
    @Override
    public JSONObject getDeviceLists(JSONObject filters) {
        String platform = null;
        String os = null;
        String name = null;
        try {
            DeviceUtils deviceUtils = new DeviceUtils();
            JSONArray deviceArray = deviceUtils.listDevices();
            JSONObject obj = new JSONObject();
            obj.put("COUNT", deviceArray.size());
            obj.put("LIST", deviceArray);
            return obj;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public JSONArray getDeviceList( JSONArray devices) {

        try {
            DeviceUtils deviceUtils = new DeviceUtils();
            JSONArray deviceArray = deviceUtils.listDevices();
            devices = getDeviceListModifiedArray(deviceArray,devices);
            return devices;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private JSONArray getDeviceListModifiedArray(JSONArray array,JSONArray devices) {
        try {
            if(devices == null) {
                devices = new JSONArray();
            }
            if(array != null && array.size()>0) {
                for(int i=0; i<array.size();i++) {
                    JSONObject obj = array.getJSONObject(i);
                    JSONObject dObj = new JSONObject();
                    dObj.put("ID", obj.get("arn"));
                    dObj.put("MANUFACTURER", obj.get("manufacturer"));
                    dObj.put("MODEL", obj.get("model"));//+" - "+obj.getString("modelId"));
                    dObj.put("OS", obj.get("platform"));
                    dObj.put("VERSION", obj.get("os"));
                    dObj.put("CITY", "on-cloud");
                    dObj.put("OPERATOR", "WIFI");
                   // dObj.put("STATUS",  obj.get("availability"));
                    dObj.put("STATUS", getDeviceStatus(obj.get("availability").toString()));
                    JSONObject resolution = obj.getJSONObject("resolution");
                    if(resolution.containsKey("height") && resolution.containsKey("width")) {
                        dObj.put("RESOLUTION", resolution.getInt("width")+" X "+resolution.getInt("height"));
                    }
                    dObj.put("AUTOMATION", true);
                    dObj.put("MANUAL", obj.getBoolean("remoteAccessEnabled"));
//					System.out.println(dObj.toString());
                    devices.add(dObj);
                }
            }
            return devices;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getDeviceStatus(String status) {

        String deviceStatus=null;
        if(status.equalsIgnoreCase("HIGHLY_AVAILABLE")||status.equalsIgnoreCase("AVAILABLE")) {
            deviceStatus="AVAILABLE";
        }else if(status.equalsIgnoreCase("BUSY")) {
            deviceStatus="BUSY";
        }else if(status.equalsIgnoreCase("NOT_AVAILABLE")||status.equalsIgnoreCase("TEMPORARY_NOT_AVAILABLE")) {
            deviceStatus="UNAVAILABLE";
        }
        return deviceStatus;
    }
}

package ai.mozark.devices.utils.aws;

import ai.mozark.devices.util.RestUtil;
import com.amazonaws.services.devicefarm.AWSDeviceFarm;
import com.amazonaws.services.devicefarm.model.DeviceFilter;
import com.amazonaws.services.devicefarm.model.ListDevicesRequest;
import com.amazonaws.services.devicefarm.model.ListDevicesResult;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DeviceUtils {
    private static AWSDeviceFarm client = MyAwsClient.getClient();
    RestUtil restUtil = new RestUtil();

    public JSONArray listDevices1(String platform) {
        try {
            List<DeviceFilter> filters = new ArrayList<DeviceFilter>();
            DeviceFilter filter = new DeviceFilter();
            filter.setAttribute("AVAILABILITY");
            filter.setOperator("EQUALS");
            List<String> avaialbilityList = new ArrayList<String>();
            avaialbilityList.add("AVAILABLE");
            avaialbilityList.add("HIGHLY_AVAILABLE");
            filter.setValues(avaialbilityList);
            filters.add(filter);

            filter = new DeviceFilter();
            filter.setAttribute("PLATFORM");
            filter.setOperator("EQUALS");
            avaialbilityList = new ArrayList<String>();
            avaialbilityList.add(platform);
            filter.setValues(avaialbilityList);
            filters.add(filter);

            ListDevicesRequest request = new ListDevicesRequest().withFilters(filters);
            ListDevicesResult result = client.listDevices(request);
            JSONArray array = new JSONArray();
            array.add(result);
            JSONObject projectListObj = array.getJSONObject(0);
            JSONArray deviceArray = projectListObj.getJSONArray("devices");
//			System.out.println(deviceArray);
            return deviceArray;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public JSONArray listDevices() {
        //String platform,String os,String name
        try {
            List<DeviceFilter> filters = new ArrayList<DeviceFilter>();
            DeviceFilter filter = new DeviceFilter();
            List<String> avaialbilityList = new ArrayList<String>();

           /* if(!restUtil.isEmpty(platform)) {
                filter = new DeviceFilter();
                filter.setAttribute("PLATFORM");
                filter.setOperator("EQUALS");
                avaialbilityList = new ArrayList<String>();
                avaialbilityList.add(platform);
                filter.setValues(avaialbilityList);
                filters.add(filter);
            }
            if(!restUtil.isEmpty(os)) {
                filter = new DeviceFilter();
                filter.setAttribute("OS_VERSION");
                filter.setOperator("GREATER_THAN_OR_EQUALS");
                avaialbilityList = new ArrayList<String>();
                avaialbilityList.add(os);
                filter.setValues(avaialbilityList);
                filters.add(filter);
            }
            if(!restUtil.isEmpty(name)) {
                filter = new DeviceFilter();
                filter.setAttribute("MODEL");
                filter.setOperator("CONTAINS");
                avaialbilityList = new ArrayList<String>();
                avaialbilityList.add(name);
                filter.setValues(avaialbilityList);
                filters.add(filter);
            }
*/
            ListDevicesRequest request = new ListDevicesRequest() ;
                  //  .withFilters(filters);
            ListDevicesResult result = client.listDevices(request);
            JSONArray array = new JSONArray();
            array.add(result);
            JSONObject projectListObj = array.getJSONObject(0);
            JSONArray deviceArray = projectListObj.getJSONArray("devices");
//			System.out.println(deviceArray);
            return deviceArray;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

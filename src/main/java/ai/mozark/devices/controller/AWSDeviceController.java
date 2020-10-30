package ai.mozark.devices.controller;

import ai.mozark.devices.model.DeviceGroup;
import ai.mozark.devices.repository.AWSDeviceRepository;
import ai.mozark.devices.util.GenerateMozarkDeviceId;
import ai.mozark.devices.util.ResponseObject;
import ai.mozark.devices.util.RestUtil;
import ai.mozark.devices.utils.device.AwsDeviceListUtils;
import ai.mozark.devices.utils.device.DeviceListUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/awsDevices")
public class AWSDeviceController {
    @Autowired
    AWSDeviceRepository awsDeviceRepository;


    @RequestMapping(value = "/devicegroup",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    ResponseEntity<Object> testingApp(@RequestBody DeviceGroup deviceGroup){
        RestUtil restUtil=new RestUtil();
        DeviceListUtils list = new AwsDeviceListUtils();
        JSONArray finalDeviceArray = list.getDeviceList(null);

        if(finalDeviceArray != null && finalDeviceArray.size()>0) {
            for (int i=0; i< finalDeviceArray.size(); i++) {
                JSONObject obj = finalDeviceArray.getJSONObject(i);
                if(obj != null) {
                    String id = obj.getString("ID");
                    String manufacturer = obj.getString("MANUFACTURER");
                    String model = obj.getString("MODEL").replaceAll("\"", "");
                    String os = obj.getString("OS");
                    String version = obj.getString("VERSION");
                    String city = obj.getString("CITY");
                    String operator = obj.getString("OPERATOR");
                    String status = obj.getString("STATUS");
                    String resolution = obj.getString("RESOLUTION");
                    boolean automation = obj.getBoolean("AUTOMATION");
                    boolean manual = obj.getBoolean("MANUAL");
                    try{
                        DeviceGroup devices=awsDeviceRepository.getDeviceGroup(Long.getLong(id),
                                manufacturer,model,os,version,"AWS");
                        if(devices !=null){
                            long devID= devices.getId();
                            String oldStatus=devices.getStatus();
                            if( restUtil.isEmpty(oldStatus) ||!oldStatus.equalsIgnoreCase(deviceGroup.getStatus())) {
                                awsDeviceRepository.updateDeviceGroup(deviceGroup.getId(),deviceGroup.getStatus(),
                                        deviceGroup.getUpdatedtime(),devID);
                            }
                        }else{
                            String groupId= GenerateMozarkDeviceId.GetDeviceId();
                            awsDeviceRepository.save(deviceGroup);
                        }
                    }catch (Exception ex){
                        return new ResponseEntity<Object>(new ResponseObject(500,
                                "Internal Server Error, add test case " + ex.getMessage(),
                                null,true), HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                }
            }
        }

        return new ResponseEntity<Object>( new ResponseObject(200, "Success",true), HttpStatus.OK);
    }
}

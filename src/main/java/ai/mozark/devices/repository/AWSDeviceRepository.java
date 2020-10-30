package ai.mozark.devices.repository;

import ai.mozark.devices.model.DeviceGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface AWSDeviceRepository extends JpaRepository<DeviceGroup,Long>{

   // void insertOrUpdateAwsDevicegroups();

    @Query(value = "select id,status from devicegroup where arn=?1 or( manufacturer=?2 and model=?3 and os=?4 and version=?5 and cloud=?6)",nativeQuery = true)
    DeviceGroup getDeviceGroup(Long id,String manufacturer,String model,String os,String version,String cloud);

    @Query(value = "update devicegroup set arn =?1, status=?2,updatedtime=?3 where id =?4",nativeQuery = true)
    void updateDeviceGroup(Long id, String status, Date updatedtime,Long devID);

}

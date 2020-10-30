package ai.mozark.devices.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="devicegroup")
@Setter
@Getter
public class DeviceGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "userid")
    private Integer userid;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "model")
    private String model;

    @Column(name = "os")
    private String os;

    @Column(name = "version")
    private String version;

    @Column(name = "city")
    private String city;

    @Column(name = "total")
    private Integer total;

    @Column(name = "disconnected")
    private Integer disconnected;

    @Column(name = "available")
    private Integer available;

    @Column(name = "busy")
    private Integer busy;

    @Column(name = "status")
    private String status;

    @Column(name = "createdtime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdtime;

    @Column(name = "updatedtime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedtime;

    @Column(name = "disabled")
    private byte disabled;

    @Column(name = "resolution")
    private String resolution;

    @Column(name = "groupid")
    private String groupid;

    @Column(name = "arn")
    private String arn;

    @Column(name = "automation")
    private byte automation;

    @Column(name = "manual")
    private byte manual;

    @Column(name = "cloud")
    private String cloud;

    @Column(name = "location")
    private String location;

    @Column(name = "carrier")
    private String carrier;

    @Column(name = "network")
    private String network;

}

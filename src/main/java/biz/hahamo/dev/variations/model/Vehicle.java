package biz.hahamo.dev.variations.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(name="LICENsE_PLATE", nullable=false, length=30)
    private String licensePlate;
    
    @Column(name="VIN", nullable=false, length=30)
    private String vin;
    
    @Column(name="LICENSE_EXPIRES", nullable=false)
    @Temporal(TemporalType.DATE)
    private Date licenseExpires;
    
    @Column(name="CAPACITY", nullable=false)
    private Long capacity;
    
    @Column(name="CURB_WEIGHT", nullable=false)
    private Long curbWeight;
    
    @Column(name="GROSS_WEIGHT", nullable=false)
    private Long grossWeight;
    
    @Column(name="MANUFACTURER_NAME", nullable=false, length=50)
    private String manufacturerName;
    
    @Column(name="MODEL", length=255)
    private String model;
    
    @Column(name="COLOR", length=255)
    private String color;
}
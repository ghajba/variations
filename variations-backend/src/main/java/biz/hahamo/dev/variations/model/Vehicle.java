package biz.hahamo.dev.variations.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Vehicle {
    protected Vehicle() {
    }

    public Vehicle(String licensePlate, String vin, Date licenseExpires, Long capacity, Long curbWeight, Long grossWeight, String manufacturerName,
            String model, String color) {
        this.licensePlate = licensePlate;
        this.vin = vin;
        this.licenseExpires = licenseExpires;
        this.capacity = capacity;
        this.curbWeight = curbWeight;
        this.grossWeight = grossWeight;
        this.manufacturerName = manufacturerName;
        this.model = model;
        this.color = color;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicle_sequence")
    @SequenceGenerator(name = "vehicle_sequence", sequenceName = "vehicle_seq", allocationSize = 1)
    private Long id;

    @Column(name = "LICENsE_PLATE", nullable = false, length = 30)
    private String licensePlate;

    @Column(name = "VIN", nullable = false, length = 30)
    private String vin;

    @Column(name = "LICENSE_EXPIRES", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date licenseExpires;

    @Column(name = "CAPACITY", nullable = false)
    private Long capacity;

    @Column(name = "CURB_WEIGHT", nullable = false)
    private Long curbWeight;

    @Column(name = "GROSS_WEIGHT", nullable = false)
    private Long grossWeight;

    @Column(name = "MANUFACTURER_NAME", nullable = false, length = 50)
    private String manufacturerName;

    @Column(name = "MODEL", length = 255)
    private String model;

    @Column(name = "COLOR", length = 255)
    private String color;
}
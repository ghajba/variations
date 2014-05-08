package biz.hahamo.dev.variations.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ShippingNote {
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(name="VEHICLE_ID", nullable=false)
    private Long vehicleId; //TODO: add a normal vehicle as it should be
    
    @Column(name="DRIVER_ID", nullable=false)
    private Long driverId;
    
    @Column(name="ROUTE_ID", nullable=false)
    private Long routeId;
    
    @Column(name="SHIPPING_START", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date shippingStart;
    
    @Column(name="SHIPPING_END", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date shippingEnd;
    
    @Column(name="CARGO_WEIGHT", nullable=false)
    private Long cargoWeight;
    
    @Column(name="CARGO_DESCRIPTION", nullable=false, length=255)
    private String cargoDescription;
}
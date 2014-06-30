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
public class ShippingNote {

    protected ShippingNote() {
    }

    public ShippingNote(Long vehicleId, Long driverId, Long routeId, Date shippingStart, Date shippingEnd, Long cargoWeight, String cargoDescription) {
        this.vehicleId = vehicleId;
        this.driverId = driverId;
        this.routeId = routeId;
        this.shippingStart = shippingStart;
        this.shippingEnd = shippingEnd;
        this.cargoWeight = cargoWeight;
        this.cargoDescription = cargoDescription;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shipping_note_sequence")
    @SequenceGenerator(name = "shipping_note_sequence", sequenceName = "shipping_note_seq", allocationSize = 1)
    private Long id;

    @Column(name = "VEHICLE_ID", nullable = false)
    private Long vehicleId; // TODO: add a normal vehicle as it should be

    @Column(name = "DRIVER_ID", nullable = false)
    private Long driverId;

    @Column(name = "ROUTE_ID", nullable = false)
    private Long routeId;

    @Column(name = "SHIPPING_START", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date shippingStart;

    @Column(name = "SHIPPING_END", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date shippingEnd;

    @Column(name = "CARGO_WEIGHT", nullable = false)
    private Long cargoWeight;

    @Column(name = "CARGO_DESCRIPTION", nullable = false, length = 255)
    private String cargoDescription;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public Date getShippingStart() {
        return shippingStart;
    }

    public void setShippingStart(Date shippingStart) {
        this.shippingStart = shippingStart;
    }

    public Date getShippingEnd() {
        return shippingEnd;
    }

    public void setShippingEnd(Date shippingEnd) {
        this.shippingEnd = shippingEnd;
    }

    public Long getCargoWeight() {
        return cargoWeight;
    }

    public void setCargoWeight(Long cargoWeight) {
        this.cargoWeight = cargoWeight;
    }

    public String getCargoDescription() {
        return cargoDescription;
    }

    public void setCargoDescription(String cargoDescription) {
        this.cargoDescription = cargoDescription;
    }
}
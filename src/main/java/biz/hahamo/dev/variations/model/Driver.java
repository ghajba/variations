package biz.hahamo.dev.variations.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Driver
{
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="NAME", nullable=false, length=255)
    private String driverName;

    @Column(name="DRIVING_LICENCE_NUMBER", nullable=false, length=30)
    private String driversLicenceNumber;

    @Column(name="DRIVING_LICENCE_CATEGORY", nullable=false, length=30)
    private String driversLicenceCategory;

    @Column(name="DRIVING_LICENCE_EXPIRES", nullable=false)
    @Temporal(TemporalType.DATE)
    private Date driversLicenceExpiration;
}

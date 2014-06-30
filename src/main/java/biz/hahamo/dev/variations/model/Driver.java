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
public class Driver {
	protected Driver() {
	}

	public Driver(String driverName, String driversLicenceNumber,
			String driversLicenceCategory, Date driversLicenceExpiration) {
		this.driverName = driverName;
		this.driversLicenceNumber = driversLicenceNumber;
		this.driversLicenceCategory = driversLicenceCategory;
		this.driversLicenceExpiration = driversLicenceExpiration;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "driver_sequence")
	@SequenceGenerator(name = "driver_sequence", sequenceName = "driver_seq", allocationSize = 1)
	private Long id;

	@Column(name = "NAME", nullable = false, length = 255)
	private String driverName;

	@Column(name = "DRIVING_LICENCE_NUMBER", nullable = false, length = 30)
	private String driversLicenceNumber;

	@Column(name = "DRIVING_LICENCE_CATEGORY", nullable = false, length = 30)
	private String driversLicenceCategory;

	@Column(name = "DRIVING_LICENCE_EXPIRES", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date driversLicenceExpiration;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriversLicenceNumber() {
		return driversLicenceNumber;
	}

	public void setDriversLicenceNumber(String driversLicenceNumber) {
		this.driversLicenceNumber = driversLicenceNumber;
	}

	public String getDriversLicenceCategory() {
		return driversLicenceCategory;
	}

	public void setDriversLicenceCategory(String driversLicenceCategory) {
		this.driversLicenceCategory = driversLicenceCategory;
	}

	public Date getDriversLicenceExpiration() {
		return driversLicenceExpiration;
	}

	public void setDriversLicenceExpiration(Date driversLicenceExpiration) {
		this.driversLicenceExpiration = driversLicenceExpiration;
	}

}

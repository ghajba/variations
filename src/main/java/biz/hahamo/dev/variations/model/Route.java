package biz.hahamo.dev.variations.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Route {
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(name="CITY_FROM", nullable=false, length=255)
    private String cityFrom;
    
    @Column(name="CITY_TO", nullable=false, length=255)
    private String cityTo;
    
    @Column(name="DISTANCE", nullable=false)
    private Long distance;
}
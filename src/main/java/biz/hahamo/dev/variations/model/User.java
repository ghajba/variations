package biz.hahamo.dev.variations.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(name="NAME", nullable=false, length=255)
    private String name;
    
    @Column(name="LOGIN", nullable=false, length=30)
    private String login;
    
    //FIXME enable hashed password -- currently te PW is stored as plain text
    @Column(name="PASSWORD", nullable=false, length=30)
    private String password;
    
    @Column(name="E_MAIL_ADDRESS", nullable=false, length=255)
    private String emailAddress;
}
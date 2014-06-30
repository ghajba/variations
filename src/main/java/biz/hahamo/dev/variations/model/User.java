package biz.hahamo.dev.variations.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class User {
    protected User() {
    }

    public User(String name, String login, String password, String emailAddress) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.emailAddress = emailAddress;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_seq", allocationSize = 1)
    private Long id;

    @Column(name = "NAME", nullable = false, length = 255)
    private String name;

    @Column(name = "LOGIN", nullable = false, length = 30)
    private String login;

    // FIXME enable hashed password -- currently te PW is stored as plain text
    @Column(name = "PASSWORD", nullable = false, length = 30)
    private String password;

    @Column(name = "E_MAIL_ADDRESS", nullable = false, length = 255)
    private String emailAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
package rest.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "content")
public class ContentJson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "C_ID")
    private long id;

    @Column(name = "type")
    private  String type;


    @Column(name = "value")
    private  String value;

    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "emailvalidationsign")
    /*private Set<EmailValidation> emailValidationSet = new HashSet<EmailValidation>(0);

    public Set<EmailValidation> getEmailValidationSet() {
        return emailValidationSet;
    }

    public void setEmailValidationSet(Set<EmailValidation> emailValidationSet) {
        this.emailValidationSet = emailValidationSet;
    }
*/
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

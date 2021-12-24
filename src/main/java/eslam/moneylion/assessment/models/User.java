package eslam.moneylion.assessment.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(name = "UC_user_email", columnNames = "email") })
public class User {

    @Id
    @Column(name = "email", columnDefinition = "text", nullable = false)
    @Email
    private String email;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
        joinColumns = {@JoinColumn(referencedColumnName = "email")}, 
        inverseJoinColumns = {@JoinColumn(referencedColumnName = "name")}
    )
    private Set<Feature> features = new HashSet<>();

    public User(String email, Set<Feature> features) {
        this.email = email;
        this.features = features;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(Set<Feature> features) {
        this.features = features;
    }

    @Override
    public String toString() {
        return "User [email=" + email + ", features=" + features + "]";
    }
}

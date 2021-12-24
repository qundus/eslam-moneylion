package eslam.moneylion.assessment.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "features", uniqueConstraints = {@UniqueConstraint(name = "UC_feature_name", columnNames = "name")})
public class Feature {
    
    @Id
    @Column(name = "name", columnDefinition = "text", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "features", cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<>();

    public Feature() {
    }

    public Feature(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Feature [name=" + name + ", users=" + users + "]";
    }
}

package eslam.moneylion.assessment.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eslam.moneylion.assessment.models.Feature;
import eslam.moneylion.assessment.models.User;
import eslam.moneylion.assessment.repositories.UserRepo;

@Service
public class UserService {
    
    @Autowired
    private UserRepo repo;

    /**
     * check if user exists in database
     * @param email email (id) of user
     * @return true if it exists
     */
    public boolean exists(String name) {
        return repo.findById(name).isPresent();
    }

    /**
     * checks if user has feature enabled
     * @param email user email id
     * @param feature feature name
     * @return true if user has feature enabled
     */
    public boolean hasFeature(String email, String feature) {
        Optional<User> result = repo.findById(email);
        if (result.isPresent()) {
            User user = result.get();
            if (user.getFeatures() == null) {
                return false;
            }
            return user.getFeatures().stream().filter(x -> x.getName().equals(feature)).findFirst().isPresent();
        } 
        return false;
    }

    public void addFeatureToUser(String email, String featureName) {
        Optional<User> result = repo.findById(email);
        if (result.isPresent()) {
            User user = result.get();
            user.getFeatures().add(new Feature(featureName));
            repo.save(user);
        }
    }
}

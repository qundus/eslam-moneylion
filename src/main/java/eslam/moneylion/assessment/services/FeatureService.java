package eslam.moneylion.assessment.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eslam.moneylion.assessment.repositories.FeatureRepo;

@Service
public class FeatureService {

    @Autowired
    private FeatureRepo repo;

    /**
     * check if feature exists in database
     * @param name name (id) of the feature
     * @return true if it exists
     */
    public boolean exists(String name) {
        return repo.findById(name).isPresent();
    }
}

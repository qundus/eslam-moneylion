package eslam.moneylion.assessment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import eslam.moneylion.assessment.models.Feature;

public interface FeatureRepo extends JpaRepository<Feature, String> {}

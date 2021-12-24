package eslam.moneylion.assessment.configs;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import eslam.moneylion.assessment.models.Feature;
import eslam.moneylion.assessment.models.User;
import eslam.moneylion.assessment.repositories.FeatureRepo;
import eslam.moneylion.assessment.repositories.UserRepo;

@Configuration
public class UserConfig {
    
    @Bean
	CommandLineRunner commandLineRunner(UserRepo userRepo, FeatureRepo featureRepo) {
		return args -> {
			Feature feat1 = new Feature("premium air");
			Feature feat2 = new Feature("steal locks");

			featureRepo.save(feat1);
			featureRepo.save(feat2);

			User user1 = new User("a@users.com", new HashSet<>(Arrays.asList(feat1)));
			User user2 = new User("b@users.com", new HashSet<>(Arrays.asList(feat1, feat2)));

			userRepo.save(user1);
			userRepo.save(user2);
		};
	}
}

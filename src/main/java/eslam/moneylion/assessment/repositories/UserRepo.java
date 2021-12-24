package eslam.moneylion.assessment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import eslam.moneylion.assessment.models.User;

public interface UserRepo extends JpaRepository<User, String> {}

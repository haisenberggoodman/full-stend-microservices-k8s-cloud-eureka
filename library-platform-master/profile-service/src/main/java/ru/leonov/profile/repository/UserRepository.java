package ru.leonov.profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.leonov.profile.model.entity.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

}

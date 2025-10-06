package ru.fitness.gym_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fitness.gym_management.entity.Membership;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {
}

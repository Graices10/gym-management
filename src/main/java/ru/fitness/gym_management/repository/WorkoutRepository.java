package ru.fitness.gym_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fitness.gym_management.entity.Workout;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {

}

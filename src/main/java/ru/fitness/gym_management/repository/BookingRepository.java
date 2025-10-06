package ru.fitness.gym_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fitness.gym_management.entity.Booking;
import ru.fitness.gym_management.entity.Workout;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    boolean existsByClientIdAndWorkoutId(Long clientId, Long workoutId);
}

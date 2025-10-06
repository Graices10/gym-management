package ru.fitness.gym_management.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fitness.gym_management.dto.WorkoutRequestDto;
import ru.fitness.gym_management.dto.WorkoutResponseDto;
import ru.fitness.gym_management.service.WorkoutService;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService workoutService;

    @GetMapping
    public ResponseEntity<List<WorkoutResponseDto>> getAllWorkouts() {
        return ResponseEntity.ok(workoutService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutResponseDto> getWorkoutById(@PathVariable Long id) {
        return ResponseEntity.ok(workoutService.findById(id));
    }

    @PostMapping
    public ResponseEntity<WorkoutResponseDto> createWorkout(@Valid @RequestBody WorkoutRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(workoutService.create(requestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkoutResponseDto> updateWorkout(
            @PathVariable Long id,
            @Valid @RequestBody WorkoutRequestDto requestDto) {
        return ResponseEntity.ok(workoutService.update(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable Long id) {
        workoutService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

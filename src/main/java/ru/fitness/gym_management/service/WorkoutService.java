package ru.fitness.gym_management.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fitness.gym_management.dto.WorkoutRequestDto;
import ru.fitness.gym_management.dto.WorkoutResponseDto;
import ru.fitness.gym_management.entity.Trainer;
import ru.fitness.gym_management.entity.Workout;
import ru.fitness.gym_management.mapper.WorkoutMapper;
import ru.fitness.gym_management.repository.TrainerRepository;
import ru.fitness.gym_management.repository.WorkoutRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final TrainerRepository trainerRepository;
    private final WorkoutMapper workoutMapper;

    public List<WorkoutResponseDto> findAll() {
        return workoutRepository.findAll().stream()
                .map(workoutMapper::toDto)
                .collect(Collectors.toList());
    }

    public WorkoutResponseDto findById(Long id) {
        Workout workout = workoutRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Тренировка с id " + id + " не найдена"));
        return workoutMapper.toDto(workout);
    }

    public WorkoutResponseDto create(WorkoutRequestDto requestDto) {
        Trainer trainer = trainerRepository.findById(requestDto.getTrainerId())
                .orElseThrow(() -> new RuntimeException("Тренер с id " + requestDto.getTrainerId() + " не найден"));

        Workout workout = workoutMapper.toEntity(requestDto);
        workout.setTrainer(trainer);

        Workout saved = workoutRepository.save(workout);
        return workoutMapper.toDto(saved);
    }

    public WorkoutResponseDto update(Long id, WorkoutRequestDto requestDto) {
        Workout existing = workoutRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Тренировка с id " + id + " не найдена"));

        Trainer trainer = trainerRepository.findById(requestDto.getTrainerId())
                .orElseThrow(() -> new RuntimeException("Тренер с id " + requestDto.getTrainerId() + " не найден"));

        existing.setName(requestDto.getName());
        existing.setDescription(requestDto.getDescription());
        existing.setStartTime(requestDto.getStartTime());
        existing.setEndTime(requestDto.getEndTime());
        existing.setMaxParticipants(requestDto.getMaxParticipants());
        existing.setTrainer(trainer);

        Workout updated = workoutRepository.save(existing);
        return workoutMapper.toDto(updated);
    }

    public void deleteById(Long id) {
        if (!workoutRepository.existsById(id)) {
            throw new RuntimeException("Тренировка с id " + id + " не найдена");
        }
        workoutRepository.deleteById(id);
    }
}

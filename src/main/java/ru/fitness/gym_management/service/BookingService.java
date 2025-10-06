package ru.fitness.gym_management.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fitness.gym_management.dto.BookingRequestDto;
import ru.fitness.gym_management.dto.BookingResponseDto;
import ru.fitness.gym_management.entity.Booking;
import ru.fitness.gym_management.entity.Client;
import ru.fitness.gym_management.entity.Workout;
import ru.fitness.gym_management.mapper.BookingMapper;
import ru.fitness.gym_management.repository.BookingRepository;
import ru.fitness.gym_management.repository.ClientRepository;
import ru.fitness.gym_management.repository.WorkoutRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ClientRepository clientRepository;
    private final WorkoutRepository workoutRepository;
    private final BookingMapper bookingMapper;

    public List<BookingResponseDto> findAll() {
        return bookingRepository.findAll().stream()
                .map(bookingMapper::toDto)
                .collect(Collectors.toList());
    }

    public BookingResponseDto findById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Бронирование с id " + id + " не найдено"));
        return bookingMapper.toDto(booking);
    }

    public BookingResponseDto create(BookingRequestDto requestDto) {
        Client client = clientRepository.findById(requestDto.getClientId())
                .orElseThrow(() -> new RuntimeException("Клиент с id " + requestDto.getClientId() + " не найден"));

        Workout workout = workoutRepository.findById(requestDto.getWorkoutId())
                .orElseThrow(() -> new RuntimeException("Тренировка с id " + requestDto.getWorkoutId() + " не найдена"));

        if (workout.getStartTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Нельзя записаться на уже прошедшую тренировку");
        }

        if (bookingRepository.existsByClientIdAndWorkoutId(client.getId(), workout.getId())) {
            throw new RuntimeException("Клиент уже записан на эту тренировку");
        }

        Booking booking = new Booking();
        booking.setClient(client);
        booking.setWorkout(workout);
        booking.setBookedAt(LocalDateTime.now());

        Booking saved = bookingRepository.save(booking);
        return bookingMapper.toDto(saved);
    }

    public void deleteById(Long id) {
        if (!bookingRepository.existsById(id)) {
            throw new RuntimeException("Бронирование с id " + id + " не найдено");
        }
        bookingRepository.deleteById(id);
    }

    public BookingResponseDto markAsAttended(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Бронирование с id " + id + " не найдено"));
        booking.setAttended(true);
        Booking updated = bookingRepository.save(booking);
        return bookingMapper.toDto(updated);
    }
}

package med.voll.api.domain.dto.schedule;

import java.time.LocalDateTime;

import med.voll.api.domain.entities.ScheduleEntity;

public record ScheduleDetailDto(
        Long id,
        Long idDoctor,
        Long idPatient,
        LocalDateTime date) {

    public ScheduleDetailDto(ScheduleEntity schedule) {
        this(schedule.getId(), schedule.getDoctor().getId(), schedule.getPatient().getId(), schedule.getDate());
    }
}

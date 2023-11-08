package med.voll.api.domain.dto.schedule;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.util.Specialty;

public record ScheduleDto(
                Long doctor,
                @NotNull Long patient,
                @NotNull @Future LocalDateTime date,
                Specialty specialty) {
}

package med.voll.api.domain.dto.schedule;

import jakarta.validation.constraints.NotNull;
import med.voll.api.util.ReasonCancel;

public record ScheduleCancel(
        @NotNull Long idSchedule,
        @NotNull ReasonCancel reasonCancel) {

}

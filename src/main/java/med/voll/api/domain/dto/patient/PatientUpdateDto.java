package med.voll.api.domain.dto.patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.dto.AddressDto;

public record PatientUpdateDto(
        @NotNull Long id,
        String named,
        String phone,
        @Valid AddressDto address) {
}
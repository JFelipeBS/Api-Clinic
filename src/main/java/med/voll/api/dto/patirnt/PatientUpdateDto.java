package med.voll.api.dto.patirnt;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.dto.AddressDto;

public record PatientUpdateDto(
        @NotNull Long id,
        String named,
        String phone,
        @Valid AddressDto address) {
}
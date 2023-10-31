package med.voll.api.domain.dto.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.dto.AddressDto;

public record DoctorUpdateDto(

        @NotNull Long id,
        String named,
        String phone,
        @Valid AddressDto address) {
}
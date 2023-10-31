package med.voll.api.dto.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.dto.AddressDto;
import med.voll.api.util.Specialty;

public record DoctorDto(

                @NotBlank String named,

                @NotBlank @Email String email,

                @NotBlank String phone,

                @NotBlank @Pattern(regexp = "\\d{4,6}") String crm,

                @NotNull Specialty specialty,

                @NotNull @Valid AddressDto address) {

}

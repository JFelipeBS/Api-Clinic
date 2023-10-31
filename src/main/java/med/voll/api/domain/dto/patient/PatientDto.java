package med.voll.api.domain.dto.patient;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.dto.AddressDto;

public record PatientDto(

        @NotBlank String named,
        @NotBlank @Email String email,
        @NotBlank String phone,
        @NotBlank @Pattern(regexp = "\\d{11}") String cpf,
        AddressDto address) {

}

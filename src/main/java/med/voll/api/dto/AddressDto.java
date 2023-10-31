package med.voll.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressDto(

        @NotBlank String logradouro,

        @NotBlank String neighborhood,

        @NotBlank @Pattern(regexp = "\\d{8}") String cep,

        @NotBlank String city,

        @NotBlank String uf,

        String num,
        String complement) {

}

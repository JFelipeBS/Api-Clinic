package med.voll.api.dto.patirnt;

import med.voll.api.entities.AddressEntity;
import med.voll.api.entities.PatientEntity;

public record PatientDetailDto(
        Long id,
        String named,
        String email,
        String phone,
        String cpf,
        AddressEntity address

) {
    public PatientDetailDto(PatientEntity patient) {
        this(patient.getId(), patient.getNamed(), patient.getEmail(), patient.getPhone(), patient.getCpf(),
                patient.getAddress());
    }
}
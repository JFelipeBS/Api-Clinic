package med.voll.api.domain.dto.patient;

import med.voll.api.domain.entities.AddressEntity;
import med.voll.api.domain.entities.PatientEntity;

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
package med.voll.api.domain.dto.patient;

import med.voll.api.domain.entities.PatientEntity;

public record PatientListDto(
                Long id,
                String named,
                String email,
                String cpf

) {

        public PatientListDto(PatientEntity patient) {
                this(patient.getId(), patient.getNamed(), patient.getEmail(), patient.getCpf());
        }

}

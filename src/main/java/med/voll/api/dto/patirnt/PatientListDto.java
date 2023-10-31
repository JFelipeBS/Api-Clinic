package med.voll.api.dto.patirnt;

import med.voll.api.entities.PatientEntity;

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

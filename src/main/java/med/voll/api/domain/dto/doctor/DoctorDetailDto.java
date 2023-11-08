package med.voll.api.domain.dto.doctor;

import med.voll.api.domain.entities.AddressEntity;
import med.voll.api.domain.entities.DoctorEntity;
import med.voll.api.util.Specialty;

public record DoctorDetailDto(
        Long id,
        String named,
        String email,
        String phone,
        String crm,
        Specialty specialty,
        AddressEntity address,
        boolean active) {

    public DoctorDetailDto(DoctorEntity doctor) {
        this(doctor.getId(), doctor.getNamed(), doctor.getEmail(), doctor.getPhone(), doctor.getCrm(),
                doctor.getSpecialty(), doctor.getAddress(), doctor.isActive());
    }

}

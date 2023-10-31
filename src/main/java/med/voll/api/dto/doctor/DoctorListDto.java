package med.voll.api.dto.doctor;

import med.voll.api.entities.DoctorEntity;
import med.voll.api.util.Specialty;

public record DoctorListDto(

        Long id,
        String named,
        String email,
        String crm,
        Specialty specialty

) {

   public DoctorListDto(DoctorEntity doc){

    this(doc.getId(), doc.getNamed(), doc.getEmail(), doc.getCrm(), doc.getSpecialty());

   }

}

package med.voll.api.domain.service.schedule.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.dto.schedule.ScheduleDto;
import med.voll.api.domain.repository.PatientRepository;
import med.voll.api.infra.exceptions.ValidationExeption;

@Component
public class validatorInactivePatient implements ValidatorScheduleInterface{

    @Autowired
    private PatientRepository repository;

    public void validator(ScheduleDto data){
        var patientActive = repository.findActiveById(data.patient());
        if (!patientActive) {
            throw new ValidationExeption("Patient does not active");
        }
    }
    
}

package med.voll.api.domain.service.schedule.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.dto.schedule.ScheduleDto;
import med.voll.api.domain.repository.DoctorRepository;
import med.voll.api.infra.exceptions.ValidationExeption;

@Component
public class ValidatorInactiveDoctor implements ValidatorScheduleInterface{

    @Autowired
    private DoctorRepository repository;

    public void validator(ScheduleDto data){
        if (data.doctor() == null) {
            return;
        }

        var doctorEntity = repository.findActiveById(data.doctor());

        if (!doctorEntity) {
            throw new ValidationExeption("Doctor does not active");
        }
    }
    
}

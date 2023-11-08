package med.voll.api.domain.service.schedule.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.dto.schedule.ScheduleDto;
import med.voll.api.domain.repository.ScheduleRepository;
import med.voll.api.infra.exceptions.ValidationExeption;

@Component
public class ValidatorDoctorWithAnotherAppointmentTheSameTime implements ValidatorScheduleInterface{

    @Autowired
    private ScheduleRepository repository;

    public void validator(ScheduleDto data) {

        var doctorAppoitmentTheSameTime = repository.existsByDoctorIdAndDate(data.doctor(),data.date());
        if (doctorAppoitmentTheSameTime) {
            throw new ValidationExeption("time conflict");
        }
    }
}

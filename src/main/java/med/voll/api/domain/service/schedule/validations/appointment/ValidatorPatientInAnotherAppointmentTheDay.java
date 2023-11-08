package med.voll.api.domain.service.schedule.validations.appointment;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.dto.schedule.ScheduleDto;
import med.voll.api.domain.repository.ScheduleRepository;
import med.voll.api.infra.exceptions.ValidationExeption;

@Component
public class ValidatorPatientInAnotherAppointmentTheDay implements ValidatorScheduleInterface {

    @Autowired
    private ScheduleRepository repository;

    public void validator(ScheduleDto data) {
        LocalDateTime firstTime = data.date().withHour(7);
        LocalDateTime lasttTime = data.date().withHour(18);
        var patientAppointmentTheDay = repository.existsByPatientIdAndDateBetween(data.patient(),firstTime,lasttTime);
        if (patientAppointmentTheDay) {
            throw new ValidationExeption("time conflict");
        }
    }

}

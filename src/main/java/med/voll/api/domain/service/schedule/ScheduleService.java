package med.voll.api.domain.service.schedule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import med.voll.api.domain.dto.schedule.ScheduleCancel;
import med.voll.api.domain.dto.schedule.ScheduleDetailDto;
import med.voll.api.domain.dto.schedule.ScheduleDto;
import med.voll.api.domain.entities.DoctorEntity;
import med.voll.api.domain.entities.PatientEntity;
import med.voll.api.domain.entities.ScheduleEntity;
import med.voll.api.domain.repository.DoctorRepository;
import med.voll.api.domain.repository.PatientRepository;
import med.voll.api.domain.repository.ScheduleRepository;
import med.voll.api.domain.service.schedule.validations.ValidatorScheduleInterface;
import med.voll.api.infra.exceptions.ValidationExeption;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private PatientRepository patirntRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    List<ValidatorScheduleInterface> validator;

    public ScheduleDetailDto scheduleAppointment(ScheduleDto data) {

        if (!patirntRepository.existsById(data.patient())) {
            throw new ValidationExeption("id does not exist");
        }

        if (data.doctor() != null && !doctorRepository.existsById(data.doctor())) {
            throw new ValidationExeption("id does not exist");
        }

        validator.forEach(v -> v.validator(data));
        PatientEntity patient = patirntRepository.getReferenceById(data.patient());
        DoctorEntity doctor = chooseDoctor(data);

        if (doctor == null) {
            throw new ValidationExeption("No doctor unavailable");
        }

        ScheduleEntity schedule = new ScheduleEntity(null, doctor, patient, data.date(), null);
        scheduleRepository.save(schedule);

        return new ScheduleDetailDto(schedule);

    }

    private DoctorEntity chooseDoctor(ScheduleDto data) {

        if (data.doctor() != null) {
            return doctorRepository.getReferenceById(data.doctor());
        }

        if (data.specialty() == null) {
            throw new ValidationExeption("specialty is mandatory when doctor is not chosen");
        }

        return doctorRepository.chooseDoctorRandomly(data.specialty(), data.date());
    }

    public void cancel(@Valid ScheduleCancel data) {

        if (!scheduleRepository.existsById(data.idSchedule())) {
            throw new ValidationExeption("id does not exist");
        }

        ScheduleEntity schedule = scheduleRepository.getReferenceById(data.idSchedule());
        schedule.cancel(data.reasonCancel());
    }

}

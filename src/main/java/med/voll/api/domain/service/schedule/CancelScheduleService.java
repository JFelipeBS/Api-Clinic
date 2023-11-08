package med.voll.api.domain.service.schedule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import med.voll.api.domain.dto.schedule.ScheduleCancel;
import med.voll.api.domain.entities.ScheduleEntity;
import med.voll.api.domain.repository.ScheduleRepository;
import med.voll.api.domain.service.schedule.validations.cancel.ValidatorCancelScheduleInterface;
import med.voll.api.infra.exceptions.ValidationExeption;

@Service
public class CancelScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private List<ValidatorCancelScheduleInterface> validator;

    public void cancel(@Valid ScheduleCancel data) {

        if (!scheduleRepository.existsById(data.idSchedule())) {
            throw new ValidationExeption("id does not exist");
        }

        validator.forEach(v -> v.validator(data));

        ScheduleEntity schedule = scheduleRepository.getReferenceById(data.idSchedule());
        schedule.cancel(data.reasonCancel());
    }

}

package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.dto.schedule.ScheduleCancel;
import med.voll.api.domain.dto.schedule.ScheduleDto;
import med.voll.api.domain.service.schedule.ScheduleService;

@RestController
@RequestMapping("schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService service;

    @PostMapping
    @Transactional
    public ResponseEntity scheduller(@RequestBody @Valid ScheduleDto data) {
        var dto = service.scheduleAppointment(data);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity delete(@RequestBody @Valid ScheduleCancel data) {
        service.cancel(data);
        return ResponseEntity.noContent().build();
    }

}

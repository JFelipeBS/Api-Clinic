package med.voll.api.domain.entities;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.dto.doctor.DoctorDto;
import med.voll.api.domain.dto.doctor.DoctorUpdateDto;
import med.voll.api.util.Specialty;

@Table(name = "tb_doctor")
@Entity(name = "DoctorEntity")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String named;
    private String email;
    private String phone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @Embedded
    private AddressEntity address;

    private boolean active;

    public DoctorEntity(DoctorDto dto) {

        this.active = true;
        this.named = dto.named();
        this.email = dto.email();
        this.phone = dto.phone();
        this.crm = dto.crm();
        this.specialty = dto.specialty();
        this.address = new AddressEntity(dto.address());

    }

    public void updateData(DoctorUpdateDto dto) {
        if (dto.named() != null) {
            this.named = dto.named();
        }
        if (dto.phone() != null) {
            this.phone = dto.phone();
        }
        if (dto.address() != null) {
            this.address.updateData(dto.address());
        }

    }

    public void disable() {
        this.active = false;
    }

}

package med.voll.api.domain.entities;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.dto.patient.PatientDto;
import med.voll.api.domain.dto.patient.PatientUpdateDto;

@Table(name = "tb_patient")
@Entity(name = "PatientEntity")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String named;
    private String email;
    private String phone;
    private String cpf;
    @Embedded
    private AddressEntity address;
    private boolean active;

    public PatientEntity(PatientDto data) {

        this.active = true;
        this.named = data.named();
        this.cpf = data.cpf();
        this.phone = data.phone();
        this.email = data.email();
        this.address = new AddressEntity(data.address());

    }

    public void disable() {
        this.active = false;
    }

    public void update(PatientUpdateDto dto) {

        if (dto.named() != null) {
            this.named = dto.named();
        }
        if (dto.phone() != null) {
            this.phone = dto.phone();

        }
        if (dto.address() != null) {
            this.address.updateData(dto
                    .address());

        }
    }

}

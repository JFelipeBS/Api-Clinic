package med.voll.api.domain.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.dto.AddressDto;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class AddressEntity {

    private String logradouro;
    private String neighborhood;
    private String cep;
    private String city;
    private String uf;
    private String num;
    private String complement;


    public AddressEntity(AddressDto dto){

        this.logradouro = dto.logradouro();
        this.cep = dto.cep();
        this.neighborhood = dto.neighborhood();
        this.city = dto.city();
        this.uf = dto.uf();
        this.num = dto.num();
        this.complement = dto.complement();

    }


    public void updateData(AddressDto address) {
        if (address.logradouro() != null) {
            this.logradouro = address.logradouro();
        }
        if (address.cep() != null) {
            this.cep = address.cep();
        }
        if (address.neighborhood() != null) {
            this.neighborhood = address.neighborhood();
        }
        if (address.city() != null) {
            this.city = address.city();
        }
        if (address.uf() != null) {
            this.uf = address.uf();
        }
        if (address.num() != null) {
            this.num = address.num();
        }
        if (address.complement() != null) {
            this.complement = address.complement();
        }
    }

}

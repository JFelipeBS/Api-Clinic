package med.voll.api.infra.exceptions;

public class ValidationExeption extends RuntimeException {
    
    public ValidationExeption(String msg) {
        super(msg);
    }
}

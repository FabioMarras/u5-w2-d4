package fabiomarras.u5w2d2.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(int id) {
        super("Elemento: " + id + " non trovato!");
    }
}

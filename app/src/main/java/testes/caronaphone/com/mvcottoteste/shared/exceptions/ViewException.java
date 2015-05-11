package testes.caronaphone.com.mvcottoteste.shared.exceptions;

/**
 * Created by -Bernardo on 2015-05-11.
 * Excecao lancada para ser tratada em uma View
 */
public class ViewException extends RuntimeException {

    private Severity severity;

    public ViewException(String detailMessage, Severity severity) {
        super(detailMessage);
        this.severity = severity;
    }

    public static enum Severity {
        WARNING, ERROR, ABORT
    }
}

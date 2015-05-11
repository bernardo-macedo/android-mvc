package testes.caronaphone.com.mvcottoteste.exceptions;

/**
 * Created by -Bernardo on 2015-05-11.
 */
public class ViewException extends RuntimeException {

    public static enum Severity {
        WARNING, ERROR, ABORT
    }

    private Severity severity;

    public ViewException(String detailMessage, Severity severity) {
        super(detailMessage);
        this.severity = severity;
    }
}

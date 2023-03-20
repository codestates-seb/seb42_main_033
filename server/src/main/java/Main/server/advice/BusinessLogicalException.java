package Main.server.advice;

import lombok.Getter;
@Getter
public class BusinessLogicalException extends RuntimeException {
    @Getter
    private ExceptionCode exceptionCode;

    public BusinessLogicalException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;


    }
}
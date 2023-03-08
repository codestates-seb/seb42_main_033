package Main.server.advice;

import lombok.Getter;

@Getter
public enum ExceptionCode {

    MEMBER_ALREADY_EXIST(402, "member already exist"),
    EMAIL_ALREADY_EXIST(402, "email already exist"),
    NICKNAME_ALREADY_EXIST(402,"nickname already exist"),
    HANDLE_ACCESS_DENIED(403, "Access is denied"),
    MEMBER_NOT_FOUND(404, "member not found"),
    EMAIL_NOT_FOUND(404, "email not found"),
    NULL_POINT_ERROR(404,"field not found"),
    METHOD_NOT_ALLOWED(405, "method not allowed"),
    VALUES_NOT_MATCH(405,"values not match");


    @Getter
    int status;

    @Getter
    String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}

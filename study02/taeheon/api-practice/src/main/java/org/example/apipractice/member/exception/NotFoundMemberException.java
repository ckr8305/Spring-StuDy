package org.example.apipractice.member.exception;

public class NotFoundMemberException extends RuntimeException {
    public NotFoundMemberException() {
        super("해당하는 이메일이 존재하지 않습니다.");
    }
}

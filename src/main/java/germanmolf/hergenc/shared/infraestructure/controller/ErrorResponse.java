package germanmolf.hergenc.shared.infraestructure.controller;

import germanmolf.hergenc.shared.domain.DomainError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public final class ErrorResponse {

    private String code;
    private String message;

    public static ErrorResponse fromDomainError(DomainError domainError) {
        return new ErrorResponse(domainError.errorCode(), domainError.errorMessage());
    }
}

package letsdev.core.password.exception;

import letsdev.common.exception.support.ErrorCode;
import org.springframework.http.HttpStatus;

public enum PasswordEncoderOptionErrorCode implements ErrorCode {
    // bcrypt
    BCRYPT_STRENGTH_OUT_OF_RANGE(
            "The strength(cost factor) must be between 4 and 31 inclusive.",
            HttpStatus.INTERNAL_SERVER_ERROR
    ),

    // argon2
    ARGON2_SALT_LENGTH_NON_POSITIVE(
            "Salt length must be positive.",
            HttpStatus.INTERNAL_SERVER_ERROR
    ),
    ARGON2_HASH_LENGTH_NON_POSITIVE(
            "Hash length must be positive.",
            HttpStatus.INTERNAL_SERVER_ERROR
    ),
    ARGON2_PARALLELISM_NON_POSITIVE(
            "Parallelism must be positive.",
            HttpStatus.INTERNAL_SERVER_ERROR
    ),
    ARGON2_ITERATIONS_NON_POSITIVE(
            "Iterations must be positive.",
            HttpStatus.INTERNAL_SERVER_ERROR
    ),
    ARGON2_ALPHA_NON_POSITIVE(
            "Alpha must be positive and is recommended to 0.95 where m ≲ 64 MiB." +
                    " Alpha can drop when memory cost is high enough.",
            HttpStatus.INTERNAL_SERVER_ERROR
    ),
    ARGON2_MEMORY_GAIN_NON_POSITIVE(
            "Memory gain must be positive.",
            HttpStatus.INTERNAL_SERVER_ERROR
    ),
    ARGON2_MEMORY_COST_NON_POSITIVE(
            "Memory cost must be positive.",
            HttpStatus.INTERNAL_SERVER_ERROR
    ),

    // default
    INVALID_PASSWORD_ENCODER_OPTION(
            "Please enter the correct options.",
            HttpStatus.INTERNAL_SERVER_ERROR
    );

    PasswordEncoderOptionErrorCode(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    private final String message;
    private final HttpStatus status;

    @Override
    public HttpStatus defaultHttpStatus() {
        return status;
    }

    @Override
    public String defaultMessage() {
        return message;
    }

    @Override
    public PasswordEncoderOptionException defaultException() {
        return new PasswordEncoderOptionException(this);
    }

    @Override
    public PasswordEncoderOptionException defaultException(Throwable cause) {
        return new PasswordEncoderOptionException(this, cause);
    }
}

package letsdev.core.password.property.argon2;

import letsdev.core.password.encoder.GeneralPasswordEncoderType.Argon2Variant;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;

@ConfigurationPropertiesBinding
public record PasswordEncoderArgon2Properties(
        Argon2Variant mode,
        Integer saltLength,
        Integer hashLength,
        Integer parallelism,
        Integer memoryInput,
        Integer iterations,
        Float alpha,
        Float memoryGain
) {
    public PasswordEncoderArgon2Properties {
        if (mode == null) {
            mode = Argon2Variant.ARGON2ID;
        }

        if (saltLength == null) {
            saltLength = 16;
        } else if (saltLength <= 0) {
            throw new Error("salt length는 양수여야 합니다.");
        }

        if (hashLength == null) {
            hashLength = 32;
        } else if (hashLength <= 0) {
            throw new Error("hash length는 양수여야 합니다.");
        }

        if (parallelism == null) {
            parallelism = 1;
        } else if (parallelism <= 0) {
            throw new Error("parallelism은 양수여야 합니다.");
        }

        if (iterations == null) {
            iterations = 1;
        } else if (iterations <= 0) {
            throw new Error("iterations는 양수여야 합니다.");
        }

        if (alpha == null) {
            alpha = 0.95f;
        } else if (alpha <= 0 || alpha > 1F) {
            throw new Error("iterations는 양수여야 합니다.");
        }

        if (memoryInput == null) {
            float num = 93750f;
            float den = (3 * iterations - 1) * alpha;
            memoryInput = (int) Math.ceil(num / den);
        } else if (memoryInput <= 0) {
            throw new Error("memory cost는 양수여야 합니다.");
        }

        if (memoryGain == null) {
            memoryGain = 1f;
        } else if (memoryGain <= 0) {
            throw new Error("memory coefficient는 양수여야 합니다.");
        }
    }

    public int memory() {
        return (int) Math.ceil(memoryInput * memoryGain);
    }

    public static PasswordEncoderArgon2Properties defaultInstance() {
        return new PasswordEncoderArgon2Properties(
                Argon2Variant.ARGON2ID,
                16,
                32,
                1,
                null,
                1,
                0.95f,
                1f
        );
    }
}

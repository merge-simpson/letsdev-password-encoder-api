package letsdev.core.password.property;

import letsdev.core.password.encoder.GeneralPasswordEncoderType;
import letsdev.core.password.encoder.GeneralPasswordEncoderType.Argon2Variant;
import letsdev.core.password.encoder.PasswordEncoderType;
import letsdev.core.password.property.argon2.PasswordEncoderArgon2Properties;
import letsdev.core.password.property.bcrypt.PasswordEncoderBcryptProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@ConfigurationProperties("letsdev.password")
@ConfigurationPropertiesBinding
public record PasswordEncoderProperties(
        SupportedPasswordEncoderType defaultEncoder,
        @NestedConfigurationProperty
        PasswordEncoderBcryptProperties bcrypt,
        @NestedConfigurationProperty
        PasswordEncoderArgon2Properties argon2
) {
    public PasswordEncoderProperties {
        if (defaultEncoder == null) {
            defaultEncoder = SupportedPasswordEncoderType.BCRYPT;
        }
        if (bcrypt == null) {
            bcrypt = PasswordEncoderBcryptProperties.defaultInstance();
        }
        if (argon2 == null) {
            argon2 = PasswordEncoderArgon2Properties.defaultInstance();
        }
    }

    public enum SupportedPasswordEncoderType implements PasswordEncoderType {
        BCRYPT {
            @Override
            public PasswordEncoderType encoderType() {
                return GeneralPasswordEncoderType.BCRYPT;
            }
        },
        ARGON2 {
            @Override
            public PasswordEncoderType encoderType() {
                return Argon2Variant.ARGON2ID;
            }
        },
        ARGON2ID {
            @Override
            public PasswordEncoderType encoderType() {
                return Argon2Variant.ARGON2ID;
            }
        },
        ARGON2D {
            @Override
            public PasswordEncoderType encoderType() {
                return Argon2Variant.ARGON2D;
            }
        };

        public abstract PasswordEncoderType encoderType();
    }
}

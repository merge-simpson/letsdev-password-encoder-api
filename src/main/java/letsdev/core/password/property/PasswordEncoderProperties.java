package letsdev.core.password.property;

import letsdev.core.password.property.argon2.PasswordEncoderArgon2Properties;
import letsdev.core.password.property.bcrypt.PasswordEncoderBcryptProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@ConfigurationProperties("letsdev.password")
@ConfigurationPropertiesBinding
public record PasswordEncoderProperties(
        @NestedConfigurationProperty
        PasswordEncoderBcryptProperties bcrypt,
        @NestedConfigurationProperty
        PasswordEncoderArgon2Properties argon2
) {
    public PasswordEncoderProperties {
        if (bcrypt == null) {
            bcrypt = PasswordEncoderBcryptProperties.defaultInstance();
        }
        if (argon2 == null) {
            argon2 = PasswordEncoderArgon2Properties.defaultInstance();
        }
    }
}

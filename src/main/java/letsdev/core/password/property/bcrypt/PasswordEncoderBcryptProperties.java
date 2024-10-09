package letsdev.core.password.property.bcrypt;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;

@ConfigurationPropertiesBinding
public record PasswordEncoderBcryptProperties(
        Integer strength
) {
    public PasswordEncoderBcryptProperties {
        if (strength == null) {
            strength = 10;
        }
    }

    public static PasswordEncoderBcryptProperties defaultInstance() {
        return new PasswordEncoderBcryptProperties(10);
    }
}

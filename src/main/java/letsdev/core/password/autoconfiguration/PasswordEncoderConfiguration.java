package letsdev.core.password.autoconfiguration;

import letsdev.core.password.property.PasswordEncoderProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationPropertiesScan(basePackageClasses = PasswordEncoderProperties.class)
public class PasswordEncoderConfiguration {

}

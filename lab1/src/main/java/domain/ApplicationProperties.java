package domain;

import java.io.IOException;
import java.util.Properties;

public class ApplicationProperties {

  private static final String configFile = "out/production/resources/config.properties";
  private static Properties properties;

  public String getAppProperties(String propertyName) {
    if (properties != null) {
      return properties.getProperty(propertyName);
    }
    properties = new Properties();
    try {
      properties.load(ApplicationProperties.class.getClassLoader().getResourceAsStream(configFile));
    } catch (IOException ignore) {
    }
    return properties.getProperty(propertyName);
  }

}

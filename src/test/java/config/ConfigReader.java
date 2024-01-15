package config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigReader {
    public static final BrowserStackConfig browserStackConfig = ConfigFactory.create(BrowserStackConfig.class, System.getProperties());

    public static final LocalConfig localConfig = ConfigFactory.create(LocalConfig.class, System.getProperties());
}

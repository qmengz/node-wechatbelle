package com.shuyun.wechatbelle.node;

import com.shuyun.motor.boot.configuration.BootConfiguration;
import com.shuyun.motor.dropwizard.MotorApplication;
import io.dropwizard.setup.Environment;

/**
 * Created by qian on 2015-01-29.
 */
public class Application extends MotorApplication<BootConfiguration> {
    public static void main(String[] args) throws Exception {
        if (null == args || args.length == 0) {
            args = new String[]{"server", "application.yml"};
        }
        new Application().run(args);
    }

    @Override
    public void runApp(BootConfiguration configuration, Environment environment) {
        environment.jersey().packages("com.shuyun.wechatbelle.node");
    }

}

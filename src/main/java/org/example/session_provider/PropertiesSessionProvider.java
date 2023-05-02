package org.example.session_provider;

import org.example.model.Device;
import org.example.model.Port;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PropertiesSessionProvider implements SessionProvider {
    @Override
    public SessionFactory getSessionFactory() {
        return new Configuration()
                .addAnnotatedClass(Device.class)
                .addAnnotatedClass(Port.class)
                .buildSessionFactory();
    }
}


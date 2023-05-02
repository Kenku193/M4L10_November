package org.example.model;

import jakarta.persistence.*;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "devices")

@ToString

@NamedQueries(
        {@NamedQuery(name = "DeviceWithInstallationDateMoreThen1Year",
                query = "from Device where installationDate < current_date() - 365")
        }
)

public class Device {
    @Id
    @GeneratedValue
    Long id;

    @Column
    String name;

    @Column(name = "installation_date")
    LocalDate installationDate;

    @Column(name = "max_connections")
    Integer maxConnections;

}

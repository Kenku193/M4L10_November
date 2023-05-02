package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "ports")

@Getter
@Setter
@ToString
public class Port {

    @Id
    @GeneratedValue
    Long id;

//    @Column(name = "device_id")
//    Long deviceId;

    @ManyToOne
    @JoinColumn(name = "device_id")
    Device device;

    @Column
    String name;

    @Column
    Integer bandwidth;

}
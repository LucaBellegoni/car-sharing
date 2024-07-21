package org.acme.model;

import java.io.Serializable;
import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "vehicle")
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "type", length = 100, nullable = false)
    private String type;

    @Column(name = "manufacturer", length = 100, nullable = false)
    private String manufacturer;

    @Column(name = "model", length = 100, nullable = false)
    private String model;

    @Column(name = "registration_year", nullable = false)
    private int registrationYear;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "image_url", length = 255)
    private String imageUrl;

    @Column(name = "last_modified_date")
    private Instant lastModifiedDate;

    @Column(name = "last_modified_user_id")
    private Long lastModifiedUserId;

}
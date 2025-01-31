package com.hetero.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "settings")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Settings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @JsonProperty("taxRate")
    double taxRate;

    @Column(nullable = false)
    @JsonProperty("shippingCost")
    double shippingCost;

    @Column(nullable = true)
    @JsonProperty("freeShippingThreshold")
    double freeShippingThreshold;

    @Column(nullable = false)
    @JsonProperty("appName")
    String appName;

    @Column(nullable = false)
    @JsonProperty("appLogo")
    String appLogo;
}

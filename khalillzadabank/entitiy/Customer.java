package com.example.khalillzadabank.entitiy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DialectOverride;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.Date;

@Entity
@Table(name = "customer")
@DynamicInsert
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String address;
    private Date dob;
    private String phone;
    private String pin;
    private String seria;
    private String cif;
    @CreationTimestamp
    private Date dataDate;
    @ColumnDefault(value = "1")
    @Column(insertable = true)
    private Integer active;

}


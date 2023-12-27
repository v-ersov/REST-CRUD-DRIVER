package com.rest.crud.restcrudexample.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DRIVER")
public class Driver {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(1)
    private Long id;

    @Column(name = "NAME")
    @Size(min = 1, max = 25)
    private String name;

    @Column(name = "AGE")
    @Min(1)
    @Max(120)
    private Integer age;
}

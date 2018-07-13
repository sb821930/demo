package com.example.demo.rest.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class MadKingDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String kingdom;

    private Instant swornOn;
}

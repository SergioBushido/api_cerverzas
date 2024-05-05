package com.example.practica_api_verveza.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.rmi.server.UID;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private UUID id;
    private String customerName;
    private String version;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;

}

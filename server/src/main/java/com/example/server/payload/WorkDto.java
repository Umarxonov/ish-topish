package com.example.server.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkDto {

    private UUID id;

    private Integer categoryWorkId;

    private String name;
    private String phoneNumber;
    private String description;
    private boolean active = false


            ;
    private Double lat;
    private Double lan;
    private Date date;

}

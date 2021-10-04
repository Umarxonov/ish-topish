package com.example.server.entity;

import com.example.server.entity.enums.WorkEnum;
import com.example.server.entity.template.AbsEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Work extends AbsEntity {
    @ManyToOne
    private Category categoryWork;

    @ManyToOne
    private User worker;

    @ManyToOne
    private User boss;

    private String name;
    @Enumerated(EnumType.STRING)
    private WorkEnum workEnu;
    private String phoneNumber;
    private String description;

    private boolean active = false;
    private Double lat;
    private Double lan;
    private Double money;
    private Double zIndex;
    private Date date;

    @OneToMany()
    private List<Attachment> photos;
}

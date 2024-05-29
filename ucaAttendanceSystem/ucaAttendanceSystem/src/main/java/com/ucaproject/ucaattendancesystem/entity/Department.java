package com.ucaproject.ucaattendancesystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String department;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<PersonInfo> personInfos = new ArrayList<PersonInfo>();
}

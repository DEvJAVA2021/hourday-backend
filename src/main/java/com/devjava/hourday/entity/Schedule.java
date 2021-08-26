package com.devjava.hourday.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "writer_id")
    private User writer;

    @OneToOne
    @JoinColumn(name = "memo_id")
    private Memo memo;

    @Column(name = "write_date")
    private LocalDate writeDate;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<>();

}

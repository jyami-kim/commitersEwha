package com.jyami.commitersewha.domain.tag;

import lombok.*;

import javax.persistence.*;

/**
 * Created by jyami on 2020/09/30
 */
@Entity
@Getter
@NoArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DevStack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "devStackId")
    private long devStackId;

    private String title;
    private String color;
    private String backgroundColor;

}

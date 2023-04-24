package com.example.companymanagement_backend.domain;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 위치 정보
 * writer : 이호진
 * init : 2023.04.24
 * updated by writer :
 * update :
 * description : 위치 정보를 정의한다.
 *               - 상세주소
 *
 * comment :
 *
 * update :
 */
@Entity
@Getter
@Table(name = "locations")
public class Location {

    @Id @GeneratedValue
    @Column(name = "location_id")
    private Long id;// 위치 아이디
    @Column(length = 40)
    private String streetAddress;// 거리/동명
    @Column(length = 12)
    private String postalCode;// 우편번호
    @Column(length = 30)
    @NotNull
    private String city;// 도시명
    @Column(length = 25)
    private String stateProvince;// 주명

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "country_id")
    private Country country;// 국가
}

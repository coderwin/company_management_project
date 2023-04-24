package com.example.companymanagement_backend.domain;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 지역 정보
 * writer : 이호진
 * init : 2023.04.24
 * updated by writer :
 * update :
 * description : 지역 정보를 정의한다.
 *
 * comment :
 *
 * update :
 */
@Entity
@Getter
@Table(name = "regions")
public class Region {

    @Id
    @NotNull
    @Column(name = "region_id")
    private Long id;// 지역 순서
    @Column(name = "region_name", length = 25)
    private String name; // 이름

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "region")
    private Country country;// 국가 정보

    // ** setter ** //
    public void addId(Long id) {
        this.id = id;
    }
    public void addName(String name) {
        this.name = name;
    }
    public void addCountry(Country country) {
        this.country = country;
    }

    // ** 생성 메서드 ** //

    public static Region createRegion(String name) {

        Region region = new Region();

        region.addName(name);

        return region;
    }
}

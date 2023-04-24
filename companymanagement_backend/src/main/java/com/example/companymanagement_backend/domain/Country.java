package com.example.companymanagement_backend.domain;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 국가 정보
 * writer : 이호진
 * init : 2023.04.24
 * updated by writer :
 * update :
 * description : 국가 정보를 정의한다.
 *
 * comment :
 *
 * update :
 */
@Entity
@Getter
@Table(name = "countries")
public class Country {

    @Id
    @NotNull
    @Column(name = "country_id", length = 2)
    private String id;// 국가 아이디
    @Column(name = "country_name", length = 40)
    private String name;// 국가명

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "region_id")
    private Region region;// 지역

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "country")
    private Location location;// 위치 정보

    // ** setter ** //

    public void addName(String name) {
        this.name = name;
    }
    public void addLocation(Location location) {
        this.location = location;
    }

    // ** 연관관계 메서드 ** //
    public void addRegion(Region region) {
        region.addCountry(this);
        this.region = region;
    }

    // ** 생성 메서드 ** //

    public static Country createCountry(String name, Region region) {

        Country country = new Country();

        country.addName(name);
        country.addRegion(region);

        return country;
    }
}

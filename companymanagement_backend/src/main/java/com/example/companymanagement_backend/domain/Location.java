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

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "location")
    private Department department;

    // ** setter ** //

    public void addStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
    public void addPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public void addCity(String city) {
        this.city = city;
    }
    public void addStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }
    public void addDepartment(Department department) {
        this.department = department;
    }

    // ** 연관관계 메서드 ** //
    public void addCountry(Country country) {
        country.addLocation(this);
        this.country = country;
    }

    // ** 생성 메서드 ** //
    public static Location createLocation(String streetAddress, String postalCode, String city, String stateProvince, Country country) {

        Location location = new Location();

        location.addStreetAddress(streetAddress);
        location.addPostalCode(postalCode);
        location.addCity(city);
        location.addStateProvince(stateProvince);
        location.addCountry(country);

        return location;
    }
}

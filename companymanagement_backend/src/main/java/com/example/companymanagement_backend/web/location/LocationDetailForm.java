package com.example.companymanagement_backend.web.location;

import com.example.companymanagement_backend.domain.Location;
import com.example.companymanagement_backend.web.country.CountryDetailForm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 위치 상세보기 dto
 * writer : 이호진
 * init : 2023.04.25
 * updated by writer :
 * update :
 * description : 부서의 위치 상세정보 담아둔다.
 */
@Getter
@Setter
@AllArgsConstructor
public class LocationDetailForm {

    private Long id;// 위치 아이디
    private String streetAddress;// 거리/동명
    private String postalCode;// 우편번호
    private String city;// 도시명
    private String stateProvince;// 주명
    private CountryDetailForm countryDetailForm;// 나라 상세정보 dto

    // ** 생성 메서드 ** //
    public static LocationDetailForm create(Location location) {

        return new LocationDetailForm(
                location.getId(),
                location.getStreetAddress(),
                location.getPostalCode(),
                location.getCity(),
                location.getStateProvince(),
                CountryDetailForm.create(location.getCountry())
        );
    }
}

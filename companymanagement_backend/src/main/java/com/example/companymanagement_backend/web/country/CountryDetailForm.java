package com.example.companymanagement_backend.web.country;

import com.example.companymanagement_backend.domain.Country;
import com.example.companymanagement_backend.domain.Region;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 나라 상세보기 dto
 * writer : 이호진
 * init : 2023.04.25
 * updated by writer :
 * update :
 * description : 부서의 나라 상세정보 담아둔다.
 */
@Getter
@Setter
@AllArgsConstructor
public class CountryDetailForm {

    private String id;// 국가 아이디
    private String name;// 국가명

    // ** 생성 메서드 ** //
    public static CountryDetailForm create(Country country) {

        return new CountryDetailForm(
                country.getId(),
                country.getName()
        );
    }
}

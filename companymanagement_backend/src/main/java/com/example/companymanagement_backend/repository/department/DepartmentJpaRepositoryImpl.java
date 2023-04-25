package com.example.companymanagement_backend.repository.department;

import com.example.companymanagement_backend.domain.Department;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import static com.example.companymanagement_backend.domain.QCountry.country;
import static com.example.companymanagement_backend.domain.QDepartment.department;
import static com.example.companymanagement_backend.domain.QLocation.location;

/**
 * DepartmentJpaRepositoryCustom 구현
 * writer : 이호진
 * init : 2023.04.25
 * updated by writer :
 * update :
 * description : DepartmentJpaRepository 구현 모음 + 최적화 사용(fetch)
 */
@RequiredArgsConstructor
@Slf4j
public class DepartmentJpaRepositoryImpl implements DepartmentJpaRepositoryCustom {

    private final JPAQueryFactory query;// queryDsl 사용 위한 필드

    /**
     * writer : 이호진
     * init : 2023.04.24
     * updated by writer :
     * update :
     * description : 부서 및 위치 정보 불러오기
     *               id : 직원 아이디
     */
    @Override
    public Optional<Department> findDepartmentAndLocationById(Long id) {

        // Department 불러오기
        Department findDepartment = query
                .selectFrom(department)
                .join(department.location, location).fetchJoin()
                .join(location.country, country).fetchJoin()
                .where(department.id.eq(id))
                .fetchOne();
        // Optional return
        return Optional.ofNullable(findDepartment);
    }
}

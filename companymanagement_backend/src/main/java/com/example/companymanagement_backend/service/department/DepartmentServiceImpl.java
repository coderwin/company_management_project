package com.example.companymanagement_backend.service.department;

import com.example.companymanagement_backend.domain.Department;
import com.example.companymanagement_backend.repository.department.DepartmentJpaRepository;
import com.example.companymanagement_backend.web.department.DepartmentDetailForm;
import com.example.companymanagement_backend.web.department.DepartmentListForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentJpaRepository departmentJpaRepository;

    /**
     * writer : 이호진
     * init : 2023.04.25
     * updated by writer :
     * update :
     * description : 부서 및 위치 정보 불러오기
     *               id : 부서 아이디
     */
    @Override
    public DepartmentDetailForm watchDetail(Long id) {
        // Department 불러오기
        Department findDepartment = departmentJpaRepository.findDepartmentAndLocationById(id).orElseThrow();
        // DepartmentDetailForm으로 변경하기
        return DepartmentDetailForm.create(findDepartment);
    }

    /**
     * writer : 이호진
     * init : 2023.04.25
     * updated by writer :
     * update :
     * description : 모든 부서 불러오기
     */
    @Override
    public List<DepartmentListForm> selectList() {
        // 모든 부서 불러오기
        List<Department> departments = departmentJpaRepository.findAll();
        // DepartmentListForm로 변경하기
        List<DepartmentListForm> departmentListFormList = departments.stream()
                .map((d) -> DepartmentListForm.create(d))
                .collect(Collectors.toList());

        return departmentListFormList;
    }

    /**
     * writer : 이호진
     * init : 2023.04.26
     * updated by writer :
     * update :
     * description : 특정 부서의 급여를 특정 비율로 인상하기
     *               id : 부서 아이디
     *               increacedPct : 인상 비율
     */
    @Override
    @Transactional(readOnly = false)
    public void increaseSalary(Long id, Integer increasedPct) {
        // department 불러오기
        Department findDepartment = departmentJpaRepository.findById(id).orElseThrow();
        // 특정 부서 급여 인상하기
        findDepartment.increaseSalary(increasedPct);

    }
}

import React from 'react'
import { Button } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';

/**
 * Employee of Employee box component
 * writer : 이호진
 * init : 2023.04.27
 * updated by writer :
 * update :
 * description : 직원 목록의 직원 component
 *               props
 *                  > data : 직원 데이터 prop
 */
const EmployeeForm = (data) => {
  /// 변수 모음
  const navigation = useNavigate();// navigation

  /// 상태 모음

  /// 메서드 모음
  // 부서명을 클릭했을 때
  function handleEmployeeNameClick(e) {
    // id 불러오기
    const id = e.target.id;// 부서 번호 불러오기
    // 부서 상세보기 이동
    navigation(`/employee/${id}`);
  }
  // 이력정보보기 클릭햇을 때
  function handleShowClick() {
    // 이력정보보기로 이동
    navigation(`/employee/${data.data.id}/jobhistory`);
  }

  /// 처음 시작

  /// view 모음

  return (
    <tr>
      {/* 성명 */}
      <th 
        id={data.data.id}
        onClick={handleEmployeeNameClick} 
        className='mousePointer' 
      >
        {`${data.data.firstName} ${data.data.lastName}`}
      </th>
      {/* 이메일 */}
      <th>
        {data.data.email}
      </th>
      {/* 부서명 */}
      <th>
        {data.data.departmentName}
      </th>
      {/* 이력정보보기 btn */}
      <th>
        <Button onClick={handleShowClick}>보기</Button>
      </th>
    </tr>
  )
}

export default EmployeeForm
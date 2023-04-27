import React from 'react'
import { useNavigate } from 'react-router-dom'


/**
 * Department of Department box component
 * writer : 이호진
 * init : 2023.04.27
 * updated by writer :
 * update :
 * description : 부서 목록의 부서 component
 *               props
 *                  > data : 부서 데이터 prop
 */
const DepartmentForm = ({data}) => {

  /// 변수 모음
  const navigation = useNavigate();// navigation

  /// 상태 모음

  /// 메서드 모음
  // 부서명을 클릭했을 때
  function handleDepartmentNameClick(e) {
    // id 불러오기
    const id = e.target.id;// 부서 번호 불러오기
    // 부서 상세보기 이동
    navigation(`/department/${id}`);
  }

  /// 처음 시작

  /// view 모음

  return (
    <tr>
      {/* 부서명 */}
      <th 
        id={data.id}
        onClick={handleDepartmentNameClick} 
        className='mousePointer' 
      >
        {data.name}
      </th>
      {/* 관리번호 */}
      <th>
        {data.managerId !== null ? data.managerId : ""}
      </th>
    </tr>
  )
}

export default DepartmentForm
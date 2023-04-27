import React, { useContext } from 'react'
import { DepartmentListContext } from '../DepartmentListForm';
import { Col, Row, Table } from 'react-bootstrap';
import DepartmentForm from './DepartmentForm';

/**
 * Department list box component
 * writer : 이호진
 * init : 2023.04.27
 * updated by writer :
 * update :
 * description : 부서 목록 box component
 */
const DepartmentBoxForm = () => {

  /// 변수 모음
  // 외부 변수 불러오기
  const {formDatas} = useContext(DepartmentListContext);

  /// 상태 모음

  /// 메서드 모음

  /// 처음 시작

  /// view 모음
  let view = null; // view 변수
  // 데이터가 있으면 생성한다
  if(formDatas.length !== 0) {
    // 데이터 만들기
    view = formDatas.map((data, i) => {
      return (
        <DepartmentForm
          key={i}
          data={data}
        />
      );
    });
  } else {
    view = (
      <tr>
        <th colSpan={10}>
          부서가 없습니다.
        </th>
      </tr>
    );
  }

  return (
    <Table hover>
      <thead className="table-primary">
        <tr>
          <th>부서명</th>
          <th>관리번호</th>
        </tr>
      </thead>
      <tbody>
        {view}
      </tbody>
    </Table>
  )
}

export default DepartmentBoxForm
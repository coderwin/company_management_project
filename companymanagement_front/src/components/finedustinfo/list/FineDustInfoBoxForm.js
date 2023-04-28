import React, { useContext } from 'react'
import { FineDustInfoListContext } from '../FineDustInfoListForm';
import { Table } from 'react-bootstrap';
import FineDustInfoForm from './FineDustInfoForm';

/**
 * FineDustInfo list box component
 * writer : 이호진
 * init : 2023.04.28
 * updated by writer :
 * update :
 * description : 미세먼지 정보 목록 box component
 */
const FineDustInfoBoxForm = () => {
  /// 변수 모음
  // 외부 변수 불러오기
  const {formDatas} = useContext(FineDustInfoListContext);

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
        <FineDustInfoForm
          key={i}
          data={data}
        />
      );
    });
  } else {
    view = (
      <tr>
        <th colSpan={10}>
          정보가 없습니다.
        </th>
      </tr>
    );
  }

  return (
    <Table hover>
      <thead className="table-primary">
        <tr>
          <th>발령일</th>
          <th>지역명</th>
          <th>권역명</th>
          <th>항목명</th>
          <th>경보단계</th>
          <th>발령시간</th>
          <th>{`발령농도[ug/m3]`}</th>
          <th>해제일</th>
          <th>해제시간</th>
          <th>{`해제농도[ug/m3]`}</th>
        </tr>
      </thead>
      <tbody>
        {view}
      </tbody>
    </Table>
  )
}

export default FineDustInfoBoxForm
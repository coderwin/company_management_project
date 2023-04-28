import React from 'react'

/**
 * FineDustInfo of FineDustInfo box component
 * writer : 이호진
 * init : 2023.04.28
 * updated by writer :
 * update :
 * description : 미세먼지 정보 목록의 미세먼지 정보 component
 *               props
 *                  > data : 미세먼지 정보 데이터 prop
 */
const FineDustInfoForm = (data) => {
  /// 변수 모음

  /// 상태 모음

  /// 메서드 모음

  /// 처음 시작

  /// view 모음

  return (
    <tr>
      {/* 발령일 */}
      <th>
        {data.data.dataDate}
      </th>
      {/* 지역명 */}
      <th>
        {data.data.districtName}
      </th>
      {/* 권역명 */}
      <th>
        {data.data.moveName}
      </th>
      {/* 항목명 */}
      <th>
        {data.data.itemCode}
      </th>
      {/* 경보단계 */}
      <th>
        {data.data.issueGbn}
      </th>
      {/* 발령시간 */}
      <th>
        {data.data.issueTime}
      </th>
      {/* 발령농도 */}
      <th>
        {data.data.issueVal}
      </th>
      {/* 해제일 */}
      <th>
        {data.data.clearDate}
      </th>
      {/* 해제시간 */}
      <th>
        {data.data.clearTime}
      </th>
      {/* 해제농도 */}
      <th>
        {data.data.clearVal}
      </th>
    </tr>
  )
}

export default FineDustInfoForm
import React, { createContext, useContext, useState } from 'react'
import { CustomContext } from '../../App';
import axios from 'axios';
import Loding from '../common/Loding';
import { Col, Container, ListGroup, ListGroupItem, Row } from 'react-bootstrap';
import FineDustInfoSearchNavForm from './list/FineDustInfoSearchNavForm';
import FineDustInfoBoxForm from './list/FineDustInfoBoxForm';
import '../../css/form.css'

/**
 * FineDustInfo list component
 * writer : 이호진
 * init : 2023.04.28
 * updated by writer :
 * update :
 * description : 미세먼지 정보 목록 component
 */
export const FineDustInfoListContext = createContext(null);

const FineDustInfoListForm = () => {
  /// 변수 모음
  const {serverHost} = useContext(CustomContext);
  const defaultData = {
    numOfRows: "",// 한 페이지 결과 수
    pageNo: "",// 페이지번호
    year: "",// 측정 연도
    itemCode: "" // (empty), PM10, PM25
  }

  /// 상태 모음
  const [loding, setLoding] = useState(false);// 요청처리 상태
  const [data, setData] = useState(defaultData);// 검색 데이터 상태
  const [formDatas, setFormDatas] = useState([]);// 미세먼지 정보 모음 데이터 상태


  /// 메서드 모음
  // 서버에서 직원 목록 불러오기
  async function getEmployeeList() {

    return await axios.get(
      `${serverHost}/finedustinfo`,
      {
        params: data
      }
    );
  }

  // formDatas에 미세먼지 정보 목록 담기
  async function inputFormDatas() {
    // loding true
    // setLoding(true);
    try {
      // 서버에서 부서 목록 불러오기
      const {data} = await getEmployeeList();
      // 요청 성공
      // loding false
      setLoding(false);
      // console.log("요청 성공");
      // formDatas에 담기
      setFormDatas(data.data.response.body.items);

    } catch(err) {
      // loding false
      setLoding(false);
      // 요청 실패
      // console.log("요청 실패");
      // console.log(err);
      alert("정확한 값을 입력해주세요");
    }
  }

  // 검색 데이터 바뀌면 data 변경한다
  function handleDataChange(e) {
    // console.log(`${e.target.name} : ${e.target.value}`);
    setData((data) => {
      return {
      ...data,
      [e.target.name]: e.target.value
      }
    });
  }

  // 찾기(Search) 버튼 클릭 했을 때
  // 조건에 맞는 직원 정보 불러오기
  async function handleSearchClick() {
    // 상품목록 cardDatas에 담기
    await inputFormDatas();
  }
  

  /// 처음 시작
  
  /// view 모음

  if(loding) return (<Loding />);// 요청 처리 view

  return (
    <>
      <FineDustInfoListContext.Provider value={{data, formDatas, handleDataChange, handleSearchClick}}>
        <Container className="body_text_center">
          <Row className="d-flex justify-content-center">
            <Col sm="12">
              <ListGroup>
                <ListGroupItem className="border border-white">
                  <Row>
                    <Col md="12">
                      {/* 위쪽 Nav - 검색 */}
                      <FineDustInfoSearchNavForm />
                    </Col>
                  </Row>
                </ListGroupItem>
                <ListGroupItem className="border border-white">
                  <Row>
                    <Col>
                      {/* body - 미세먼지 정보 목록  */}
                      <FineDustInfoBoxForm />
                    </Col>
                  </Row>
                </ListGroupItem>
              
              </ListGroup>
            </Col>
          </Row>
        </Container>
      </FineDustInfoListContext.Provider>
    </>
  )
}

export default FineDustInfoListForm
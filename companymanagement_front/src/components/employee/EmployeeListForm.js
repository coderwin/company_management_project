import React, { createContext, useContext, useEffect, useState } from 'react'
import { CustomContext } from '../../App';
import axios from 'axios';
import Loding from '../common/Loding';
import { Col, Container, ListGroup, ListGroupItem, Row } from 'react-bootstrap';
import EmployeeBoxForm from './list/EmployeeBoxForm';
import EmployeeSearchNavForm from './list/EmployeeSearchNavForm';
import '../../css/form.css'

/**
 * Employee list component
 * writer : 이호진
 * init : 2023.04.27
 * updated by writer :
 * update :
 * description : 직원 목록 component
 */
export const EmployeeListContext = createContext(null);

const EmployeeListForm = () => {
  /// 변수 모음
  const {serverHost} = useContext(CustomContext);
  const defaultData = {
    departmentId: ""
  }

  /// 상태 모음
  const [loding, setLoding] = useState(false);// 요청처리 상태
  const [data, setData] = useState(defaultData);// 검색 데이터 상태
  const [formDatas, setFormDatas] = useState([]);// 부서 모음 데이터 상태
  const [departmentDatas, setDepartmentDatas] = useState([]);// 부서 모음 데이터 상태

  /// 메서드 모음
  // 서버에서 직원 목록 불러오기
  async function getEmployeeList() {

    return await axios.get(
      `${serverHost}/employees`,
      {
        params: data
      }
    );
  }

  // formDatas에 직원 목록 담기
  async function inputFormDatas() {
    // loding true
    setLoding(true);
    try {
      // 서버에서 부서 목록 불러오기
      const {data} = await getEmployeeList();
      // 요청 성공
      // loding false
      setLoding(false);
      // console.log("요청 성공");
      // formDatas에 담기
      setFormDatas(data.data);

    } catch(err) {
      // loding false
      setLoding(false);
      // 요청 실패
      // console.log("요청 실패");
      // console.log(err);
    }
  }

  // 서버에서 직원 목록 불러오기 -> 부서 검색을 통해
  async function inputFormDatasForSearch() {

    try {
      // 서버에서 부서 목록 불러오기
      const {data} = await getEmployeeList();
      // 요청 성공
      // loding false
      setLoding(false);
      // console.log("요청 성공");
      // formDatas에 담기
      setFormDatas(data.data);

    } catch(err) {
      // loding false
      setLoding(false);
      // 요청 실패
      // console.log("요청 실패");
      // console.log(err);
    }
  }

  // 서버에서 부서 목록 불러오기
  async function getDepartmentList() {

    return await axios.get(
      `${serverHost}/departments`
    );
  }

  // departmentDatas에 부서 목록 담기
  async function inputDepartmentDatas() {
    // loding true
    setLoding(true);
    try {
      // 서버에서 부서 목록 불러오기
      const {data} = await getDepartmentList();
      // 요청 성공
      // loding false
      setLoding(false);
      // console.log("요청 성공");
      // formDatas에 담기
      setDepartmentDatas(data.data);

    } catch(err) {
      // loding false
      setLoding(false);
      // 요청 실패
      // console.log("요청 실패");
      // console.log(err);
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
  // 직원 목록 formDatas에 담기
  useEffect(() => {
    inputFormDatas();
  }, []);
  // 부서 목록 departmentDatas에 담기
  useEffect(() => {
    inputDepartmentDatas();
  }, []);
  // 찾기에 사용
  useEffect(() => {
    inputFormDatasForSearch();
  }, [data]);

  /// view 모음

  if(loding) return (<Loding />);// 요청 처리 view

  return (
    <>
      <EmployeeListContext.Provider value={{data, formDatas, departmentDatas, handleDataChange, handleSearchClick}}>
        <Container className="body_text_center">
          <Row className="d-flex justify-content-center">
            <Col sm="12">
              <ListGroup>
                <ListGroupItem className="border border-white">
                  <Row>
                    <Col md="12">
                      {/* 위쪽 Nav - 검색 */}
                      <EmployeeSearchNavForm />
                    </Col>
                  </Row>
                </ListGroupItem>
                <ListGroupItem className="border border-white">
                  <Row>
                    <Col>
                      {/* body - 직원 목록  */}
                      <EmployeeBoxForm />
                    </Col>
                  </Row>
                </ListGroupItem>
              
              </ListGroup>
            </Col>
          </Row>
        </Container>
      </EmployeeListContext.Provider>
    </>
  )
}

export default EmployeeListForm
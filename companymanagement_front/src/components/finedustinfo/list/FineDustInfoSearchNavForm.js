import React, { useContext } from 'react'
import { FineDustInfoListContext } from '../FineDustInfoListForm';
import { Button, Col, Container, Form, Nav, Navbar } from 'react-bootstrap';

const FineDustInfoSearchNavForm = () => {
  /// 변수 모음
  // 외부의 변수 불러오기
  const {data, handleDataChange, handleSearchClick} = useContext(FineDustInfoListContext);
  // 미세먼지 종류 모음
  const fineDustTypeValues = ["", "PM10", "PM25"];
  const fineDustTypeNames = ["미세먼지 타입 선택", "PM10", "PM25"];
  /// 상태 모음

  /// 메서드 모음

  /// 처음 시작

  /// view 모음

  return (
    <Navbar expand="lg" className="search_navbar">
      <Container fluid>
        <Navbar.Brand href="#">미세먼지정보찾기</Navbar.Brand>
        <Navbar.Toggle aria-controls="navbarScroll" />
        <Navbar.Collapse id="navbarScroll">
          <Nav>
          </Nav>
          {/* 한 페이지 결과 수 search */}
          <Form className="d-flex">
            <Form.Control
              type="search"
              placeholder="요청 결과수"
              className="me-2"
              aria-label="Search"
              name="numOfRows"
              value={data.numOfRows}
              onChange={handleDataChange}
            />
          </Form>
          {/* 페이지번호 search */}
          <Form className="d-flex">
            <Form.Control
              type="search"
              placeholder="요청 페이지번호"
              className="me-2"
              aria-label="Search"
              name="pageNo"
              value={data.pageNo}
              onChange={handleDataChange}
            />
          </Form>
          {/* 측정 연도 search */}
          <Form className="d-flex">
            <Form.Control
              type="search"
              placeholder="측정연도 ex) 2020"
              className="me-2"
              aria-label="Search"
              name="year"
              value={data.year}
              onChange={handleDataChange}
            />
          </Form>
          {/* 미세먼지 종류 search */}
          <Form className="d-flex">
            <Form.Select
              as={Col}
              sm="5" 
              name="itemCode"
              value={data.itemCode}
              onChange={handleDataChange}
            >
              {
                fineDustTypeValues.map((value, i) => {
                  return (
                    <option
                      key={i}
                      value={value}
                    >{fineDustTypeNames[i]}</option>
                  );
                })
              }
            </Form.Select>
            <Button type="button" onClick={handleSearchClick}>Search</Button>
          </Form>  
        </Navbar.Collapse>
      </Container>
    </Navbar>
  )
}

export default FineDustInfoSearchNavForm
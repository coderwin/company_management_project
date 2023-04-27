import React, { useContext } from 'react'
import { EmployeeListContext } from '../EmployeeListForm';
import { Button, Col, Container, Form, Nav, Navbar } from 'react-bootstrap';

const EmployeeSearchNavForm = () => {

  /// 변수 모음
  // 외부의 변수 불러오기
  const {data, departmentDatas, handleDataChange, handleSearchClick} = useContext(EmployeeListContext);
  /// 상태 모음

  /// 메서드 모음

  /// 처음 시작

  /// view 모음

  return (
    <Navbar expand="lg" className="search_navbar">
      <Container fluid>
        <Navbar.Brand href="#">부서직원찾기</Navbar.Brand>
        <Navbar.Toggle aria-controls="navbarScroll" />
        <Navbar.Collapse id="navbarScroll">
          <Nav>
          </Nav>
          
          {/* 부서 search */}
          <Form className="d-flex">
            <Form.Select
              as={Col}
              sm="5" 
              name="departmentId"
              value={data.departmentId}
              onChange={handleDataChange}
            >
              <option value="">부서명</option>
              {
                departmentDatas.map((data, i) => {
                  return (
                    <option
                      key={i}
                      value={data.id}
                    >{data.name}</option>
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

export default EmployeeSearchNavForm
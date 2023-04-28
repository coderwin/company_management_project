import React from 'react'
import { Container, Nav, NavDropdown, Navbar } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';

const HeaderNav = () => {
  /// 변수 모음
  const navigation = useNavigate();// 이동하기 만들기

  /// 상태 모음

  /// 메서드 모음
  // 홈으로(직원 모음)
  const handleHomeClick = () => {
    navigation("/");
  }
  // 부서 모음
  const handleDepartmetClick = () => {
    navigation("/department/list");
  }
  // 부서 급여 변경
  const handleChangeDepartmentSalaryClick = () => {
    navigation("/department/update/salary");
  }
  // 미세먼지 정보 찾기
  const handleFineDustInfoClick = () => {
    navigation("/finedust/info");
  }

  return (
    <>
      <Navbar bg="light" expand="lg">
        <Container>
          <Navbar.Brand className="googlefont mitr mousePointer" onClick={handleHomeClick} >CompanyManagement</Navbar.Brand>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="me-auto">
              {/* 직원 목록 */}
              <Nav.Link onClick={handleHomeClick}>직원목록</Nav.Link>
              {/* 부서 */}
              <NavDropdown title="부서" id="basic-nav-dropdown">
                <NavDropdown.Item onClick={handleDepartmetClick}>
                  부서목록
                </NavDropdown.Item>
                <NavDropdown.Item onClick={handleChangeDepartmentSalaryClick}>
                  부서급여변경
                </NavDropdown.Item> 
              </NavDropdown>
              {/* 미세먼지정보 */}
              <Nav.Link onClick={handleFineDustInfoClick}>미세먼지정보</Nav.Link>
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>
    </>
  )
}

export default HeaderNav
import React from 'react'
import { Button, Col, Container, Image, Row } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';

/**
 * NotFound component
 * writer : 이호진
 * init : 2023.04.27
 * updated by writer : 
 * update :
 * description : NotFound component
 */
const NotFoundForm = () => {
  /// 변수 모음
  const navigation = useNavigate();

  /// 메서드 모음
  // "/"으로 이동
  function handleHomeClick() {
    navigation("/employee/list");
  }

  return (
    <Container className="body_text_center">
      <Row className="d-flex justify-content-center">
        <Col sm={12}>
          <Image className="img-fluid"
            // src={}
            alt="not_found"
          />
        </Col>
      </Row>
      <Row className="d-flex justify-content-center">
        <Col sm={12}>
          <h3>잘못된 요청입니다.</h3>
        </Col>
      </Row>
      <Row className="d-flex justify-content-center">
        <Col sm={8}>
          <h4>다시 요청해주세요</h4>
        </Col>
      </Row>
      <Row className="d-flex justify-content-center">
        <Col sm={4} className="d-grid gap-2">
          <Button onClick={handleHomeClick}>홈으로</Button>
        </Col>
      </Row>
    </Container>
  )
}

export default NotFoundForm
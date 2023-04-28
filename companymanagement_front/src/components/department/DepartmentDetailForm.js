import React, { useContext, useEffect, useState } from 'react'
import { CustomContext } from '../../App';
import { useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import Loding from '../common/Loding';
import { Col, Container, Form, Row } from 'react-bootstrap';
import '../../css/form.css'

/**
 * Department detail component
 * writer : 이호진
 * init : 2023.04.27
 * updated by writer :
 * update :
 * description : 부서 상세보기 component
 *               + 위치 정보
 */
const DepartmentDetailForm = () => {

  /// 변수 모음
  // defaultData
  const defaultData = {
    id: "", // 부서 아이디
    name: "", // 부서명
    managerId: "",// 관리 아이디
    locationDetailForm: {
      id: "",// 위치 아이디
      streetAddress: "",// 거리/동명
      postalCode: "",// 우편번호
      city: "",// 도시명
      stateProvince: "",// 주명
      countryDetailForm: {
        id: "",// 국가 아이디
        name: ""// 국가명
      }// 나라 상세정보
    } //위치 상세정보
  }
  // navigation
  const navigation = useNavigate();
  // department id 불러오기
  const {id} = useParams();
  // 외부 데이터 불러오기
  const {serverHost} = useContext(CustomContext);

  /// 상태 모음
  const [loding, setLoding] = useState(false); // 작업 상태
  const [error, setError] = useState(null); // 에러 상태
  const [data, setData] = useState(defaultData);// 부서 정보 상태

  /// 메서드 모음

  // 부서 정보 불러오기
  async function getDepartmentDetailInfo() {
    // loding ture로 처리
    setLoding(true);
    // 서버에 회원정보 요청
    return await axios.get(
      `${serverHost}/departments/${id}`,
    );
  }

  // 부서 정보 data에 담기
  async function inputData() {
    setLoding(true);
    try {
      // 서버로부터 데이터 불러오기
      const result = await getDepartmentDetailInfo();
      // 데이터 다 가져왔으니 loding false로 처리
      setLoding(false);
      // data에 데이터 담기
      setData(result.data.data);

    } catch(error) {
      // 데이터 가져오기 끝
      setLoding(false);
      // console.log(error);
      // error Msg 담기
      alert(error.response.data.errMsg);
      setError(error.response.data.errMsg);
    }
  }

  /// 처음 시작
  useEffect(() => {
    // 부서 정보 서버에서 불러오기
    inputData();
  }, []);

  /// view 모음

  // loding true일 때
  if(loding) return (<Loding />);
  // 서버 요청 처리 중 에러 발생 때
  if(error) return (<div>요청 작업 중 에러 발생</div>);

  return (
    <Container className="body_margin body_text_center">
      <Row>
        {/* department Info box */}
        <Col sm="12">
          <Form>
            <Row className="mb-3">
              <Form.Group
                as={Col}
                md="3"
              >
                <Form.Label>부서명</Form.Label>
                <Form.Control
                  type="text"
                  value={data.name}
                  readOnly
                />
              </Form.Group>
              <Form.Group
                as={Col}
                md="3"
                controlId="validationFormik102"
                className="position-relative"
              >
                <Form.Label>관리아이디</Form.Label>
                <Form.Control
                  type="text"
                  value={data.managerId}
                  readOnly
                />
              </Form.Group>
              
            </Row>

            <Row>
              <Form.Group as={Col} md="3">
                <Form.Label>국가명</Form.Label>
                <Form.Control
                  type="text"
                  value={data.locationDetailForm.countryDetailForm.name}
                  readOnly
                />
              </Form.Group>
              <Form.Group as={Col} md="3" >
                <Form.Label>주명</Form.Label>
                <Form.Control
                  type="text"
                  value={data.locationDetailForm.stateProvince}
                  readOnly
                />
              </Form.Group>
              <Form.Group as={Col} md="2" >
                <Form.Label>도시명</Form.Label>
                  <Form.Control
                    type="text"
                    value={data.locationDetailForm.city}
                    readOnly
                  />
              </Form.Group>
              <Form.Group as={Col} md="3" >
                <Form.Label>거리명</Form.Label>
                  <Form.Control
                    type="text"
                    value={data.locationDetailForm.streetAddress}
                    readOnly
                  />
              </Form.Group>
              <Form.Group as={Col} md="1" >
                <Form.Label>우편번호</Form.Label>
                <Form.Control
                  type="text"
                  value={data.locationDetailForm.postalCode}
                  readOnly
                />
              </Form.Group>
            </Row>
                  
          </Form>
        </Col>
      </Row>
    </Container>
  )
}

export default DepartmentDetailForm
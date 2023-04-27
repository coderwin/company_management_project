import React, { useContext, useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom';
import { CustomContext } from '../../App';
import axios from 'axios';
import Loding from '../common/Loding';
import { Col, Container, Form, Row } from 'react-bootstrap';
import '../../css/form.css'

/**
 * Employee detail component
 * writer : 이호진
 * init : 2023.04.27
 * updated by writer :
 * update :
 * description : 직원 상세보기 component
 */
const EmployeeDetailFrom = () => {
  /// 변수 모음
  // defaultData
  const defaultData = {
    employeeId: "", // 직원 아이디
    jobId: "", // 직급 아이디
    managerId: "",// 관리 아이디
    departmentId: "",// 부서 아이디
    locationId: "",// 위치 아이디
    countryId: "",// 나라 아이디
    firstName: "",// 성
    laastName: "",// 이름
    salary: "",// 급여
    commissionPct: "",// 수수료?
    departmentName: "",// 부서명
    jobTitle: "",// 직급명
    city: "",// 도시명
    stateProvince: "",// 주명
    countryName: "",// 나라명
    regionName: ""// 지역명
  }
  // navigation
  const navigation = useNavigate();
  // employee id 불러오기
  const {id} = useParams();
  // 외부 데이터 불러오기
  const {serverHost} = useContext(CustomContext);

  /// 상태 모음
  const [loding, setLoding] = useState(false); // 작업 상태
  const [error, setError] = useState(null); // 에러 상태
  const [data, setData] = useState(defaultData);// 부서 정보 상태

  /// 메서드 모음

  // 직원 정보 불러오기
  async function getEmployeeDetailInfo() {
    // loding ture로 처리
    setLoding(true);
    // 서버에 회원정보 요청
    return await axios.get(
      `${serverHost}/employees/${id}`,
    );
  }

  // 직원 정보 data에 담기
  async function inputData() {
    setLoding(true);
    try {
      // 서버로부터 데이터 불러오기
      const result = await getEmployeeDetailInfo();
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
    <Container>
      <Row>
        {/* employee Info box */}
        <Col sm="9">
          <Form>
            <Row className="mb-3">
              <Form.Group
                as={Col}
                md="4"
              >
                <Form.Label>성명</Form.Label>
                <Form.Control
                  type="text"
                  value={`${data.firstName} ${data.lastName}`}
                  readOnly
                />
              </Form.Group>
              <Form.Group
                as={Col}
                md="4"
                controlId="validationFormik102"
                className="position-relative"
              >
                <Form.Label>급여</Form.Label>
                <Form.Control
                  type="text"
                  value={data.salary}
                  readOnly
                />
                <span>만원</span>
              </Form.Group>
              <Form.Group as={Col} md="4" >
                <Form.Label>수수료</Form.Label>
                  <Form.Control
                    type="text"
                    value={data.commissionPct}
                    readOnly
                  />
              </Form.Group>
              <Form.Group as={Col} md="4" >
                <Form.Label>부서명</Form.Label>
                  <Form.Control
                    type="text"
                    value={data.departmentName}
                    readOnly
                  />
              </Form.Group>
            </Row>

            <Row>
              <Form.Group as={Col} md="6" >
                <Form.Label>직급명</Form.Label>
                <Form.Control
                  type="text"
                  value={data.jobTitle}
                  readOnly
                />
              </Form.Group>
              <Form.Group as={Col} md="6" >
                <Form.Label>도시명</Form.Label>
                <Form.Control
                  type="text"
                  value={data.city}
                  readOnly
                />
              </Form.Group>
            </Row>

            <Row className="mb-3">
              <Form.Group as={Col} md="2">
                <Form.Label>주명</Form.Label>
                <Form.Control
                  type="text"
                  value={data.stateProvince}
                  readOnly
                />
              </Form.Group>
              <Form.Group as={Col} md="2">
                <Form.Label>나라명</Form.Label>
                <Form.Control
                  type="text"
                  value={data.countryName}
                  readOnly
                />
              </Form.Group>
              <Form.Group as={Col} md="2">
                <Form.Label>지역명</Form.Label>
                <Form.Control
                  type="text"
                  value={data.regionName}
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

export default EmployeeDetailFrom
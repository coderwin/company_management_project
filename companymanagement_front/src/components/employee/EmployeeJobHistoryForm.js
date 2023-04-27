import React, { useContext, useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom';
import { CustomContext } from '../../App';
import axios from 'axios';
import Loding from '../common/Loding';
import { Col, Container, Form, Row, Table } from 'react-bootstrap';
import '../../css/form.css'

/**
 * Employee jobHistory component
 * writer : 이호진
 * init : 2023.04.27
 * updated by writer :
 * update :
 * description : 직원 이력 정보 component
 */
const EmployeeJobHistoryForm = () => {
  /// 변수 모음
  // defaultData
  const defaultData = {
    id: "", // 직원 아이디
    firstName: "",// 성
    laastName: "",// 이름
    jobHistoryFormList: ""// 이력 모음
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
  async function getEmployeeJobHistoryInfo() {

    // 서버에 회원정보 요청
    return await axios.get(
      `${serverHost}/employees/${id}/jobhistories`,
    );
  }

  // 직원 정보 data에 담기
  async function inputData() {
    setLoding(true);
    try {
      // 서버로부터 데이터 불러오기
      const result = await getEmployeeJobHistoryInfo();
      // 데이터 다 가져왔으니 loding false로 처리
      setLoding(false);
      // data에 데이터 담기
      setData(result.data.data);
      // console.log("요청 성공");

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
        {/* employee jobHistory Info box */}
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
            </Row>

            <Table>
              <thead>
                <tr>
                  <th>직급시작날짜</th>
                  <th>직급마지막날짜</th>
                  <th>직급명</th>
                  <th>{"최소급여(만원)"}</th>
                  <th>{"최대급여(만원)"}</th>
                  <th>부서명</th>
                </tr>
              </thead>
              <tbody>
                { data.jobHistoryFormList !== "" &&
                  data.jobHistoryFormList.map((jobHistory, i) => {
                    return (
                      <tr key={i}>
                        <th>{jobHistory.startDate}</th>
                        <th>{jobHistory.endDate}</th>
                        <th>{jobHistory.jobTitle}</th>
                        <th>{jobHistory.minSalary}</th>
                        <th>{jobHistory.maxSalary}</th>
                        <th>{jobHistory.departmentName}</th>
                      </tr>
                    )
                  })
                }
              </tbody>  
            </Table>
                  
          </Form>
        </Col>
      </Row>
    </Container>
  )
}

export default EmployeeJobHistoryForm
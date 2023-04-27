import React, { useContext, useEffect, useState } from 'react'
import { CustomContext } from '../../App';
import axios from 'axios';
import Loding from '../common/Loding';
import { Button, Col, Container, Form, FormGroup, Navbar, Row } from 'react-bootstrap';
import '../../css/form.css'

/**
 * Department salary update component
 * writer : 이호진
 * init : 2023.04.27
 * updated by writer :
 * update :
 * description : 부서의 직원 급여 수정 component
 */
const DepartmentSalaryUpdateFrom = () => {

  /// 변수 모음
  // 외부 변수 불러오기
  const {serverHost} = useContext(CustomContext);
  // defalutChangeData
  const defaultData = {
    departmentId: "",// 부서 아이디
    increasedPct: "",// 인상 비율
  }
  // defaultErrMsgs
  const defaultErrMsgs = {
    departmentId: "",// 부서 아이디
    increasedPct: "",// 인상 비율
  }

  /// 상태 모음
  const [loding, setLoding] = useState(false);// 요청처리 상태
  const [formDatas, setFormDatas] = useState([]);// 부서 모음 데이터 상태
  const [data, setData] = useState(defaultData);// 서버에 보내는 데이터
  const [errMsgs, setErrMsgs] = useState(defaultErrMsgs); // 에러 메시지 상태

  /// 메서드 모음
  // 서버에서 부서 목록 불러오기
  async function getDepartmentList() {

    return await axios.get(
      `${serverHost}/departments`
    );
  }

  // formDatas에 부서 목록 담기
  async function inputFormDatas() {
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
      setFormDatas(data.data);

    } catch(err) {
      // loding false
      setLoding(false);
      // 요청 실패
      // console.log("요청 실패");
      // console.log(err);
    }
  }

  // 부서별 급여 인상 데이터 서버로 보내기
  async function update(data) {

    return await axios.patch(
      `${serverHost}/departments`,
      data
    );
  }

  // 변경 버튼 클릭했을 때
  async function handleChangeBtnClick(e) {
    // 이벤트 막기
    e.preventDefault();
    // 정말로 변경할 건지 물어보기
    const answer = window.confirm("정말로 변경하시겠습니까?");
    
    if(answer === true) {
      // loding true
      setLoding(true);
      // 서버로 보낼 데이터 만들기
      const updateData = {
        id: data.departmentId,
        increasedPct: data.increasedPct
      }
      // 요청 데이터 서버로 보내기
      try {
        // 데이터 수정
        const response = await update(updateData);
        // 수정 성공
        // console.log("인상 성공");
        // loding false
        setLoding(false);
        // 수정 성공 alert창 띄우기
        alert(response.data.data);

      } catch(err) {
        // 요청 실패
        // console.log("인상 실패");
        // loding false
        setLoding(false);
        // field Error 일 때
        if(err.response.data.errors) {
          // newErrMsgs 객체 생성하기
          let newErrMsgs = {};
          // field 값에 따라서 데이터 넣기
          for(let key in err.response.data.errors) {
            // newErrMsg에 각가의 field 에러 메시지 객체 담기
            const newErrMsg = err.response.data.errors[key];
            // newErrMsg의 field와 errMsgs의 key를 비교한다.
            for(let errMsgKey in errMsgs) {
              // errMsgKey와 newErrMsg의 field가 같으면 
              // newErrMsgs에 새로운 데이터를 추가한다.
              if(newErrMsg.field === errMsgKey) {
                newErrMsgs = {
                  ...newErrMsgs,
                  [newErrMsg.field]: newErrMsg.errMsg
                }
              } 
            }
          }
          // errMsgs에 newErrMsgs 담기
          // defaultMsgs는 항상 이벤트가 일어나면 초기화 시켜준다 
          setErrMsgs({
            ...defaultErrMsgs,
            ...newErrMsgs
          });
        }
      }
    }
  }

  // 입력 데이터가 바뀌면 defaultChageData 바꾸기
  function handleDataChange(e) {

    setData((data) => {
      return {
        ...data,
        [e.target.name]: e.target.value
      }
    });
  }

  /// 처음 시작
  // 부서 목록 formDatas에 담기
  useEffect(() => {
    inputFormDatas();
  }, []);
  /// view 모음

  if(loding) return (<Loding />);// 요청 처리 view

  return (
    <>
      <Container>
        <Row>
          <Form onSubmit={handleChangeBtnClick}>
            {/* 부서명 */}
            <Row className="d-flex justify-content-center">
              <Col sm={3}>
                <FormGroup
                  className="mb-3"
                >
                  <Form.Label>
                    부서명
                  </Form.Label>
                  <Form.Select
                    as={Col}
                    sm="5" 
                    name="departmentId"
                    value={data.departmentId}
                    onChange={handleDataChange}
                  >
                    <option value="">선택</option>
                    {
                      formDatas.map((data, i) => {
                        return (
                          <option
                            key={i}
                            value={data.id}
                          >{data.name}</option>
                        );
                      })
                    }
                  </Form.Select>
                </FormGroup>
              </Col>
            </Row>
            {/* 인상비율 */}
            <Row className="d-flex justify-content-center">
              <Col sm={3}>
                <Form.Group
                  className="mb-3"
                >
                  <Form.Label >
                    인상비율
                  </Form.Label>

                  <Form.Control
                    type="number"
                    name="increasedPct"
                    min="0"
                    value={data.increasedPct}
                    onChange={handleDataChange}
                  />
                </Form.Group>
              </Col>
            </Row>
            {/* 에러 메시지 */}
            <Row className="d-flex justify-content-center">
              <Col sm={3}>
                <Form.Group className="mb-3 error">
                  {errMsgs.increasedPct}
                </Form.Group>
              </Col>
            </Row>
            {/* 버튼 box */}
            <Row className="d-flex justify-content-center">
              <Col sm={3} className="d-grid gap-2">
                <Button variant="primary" type="submit" size="lg">변경</Button>
              </Col>
            </Row>    
          </Form>
        </Row>  
      </Container>
    </>
  )
}

export default DepartmentSalaryUpdateFrom
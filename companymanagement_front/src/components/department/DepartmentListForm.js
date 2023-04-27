import React, { createContext, useContext, useEffect, useState } from 'react'
import Loding from '../common/Loding';
import { Col, Container, ListGroup, ListGroupItem, Row } from 'react-bootstrap';
import axios from 'axios';
import { CustomContext } from '../../App';
import '../../../css/form.css'
import DepartmentBoxForm from './list/DepartmentBoxForm';

/**
 * Department list component
 * writer : 이호진
 * init : 2023.04.27
 * updated by writer :
 * update :
 * description : 부서 목록 component
 */

// context 만들기
export const DepartmentListContext = createContext(null); 

const DepartmentListForm = () => {

  /// 변수 모음
  const {serverHost} = useContext(CustomContext);

  /// 상태 모음
  const [loding, setLoding] = useState(false);// 요청처리 상태
  const [formDatas, setFormDatas] = useState([]);// 부서 모음 데이터 상태

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
      console.log("요청 성공");
      // formDatas에 담기
      setFormDatas(data.data);

    } catch(err) {
      // loding false
      setLoding(false);
      // 요청 실패
      console.log("요청 실패");
      console.log(err);
    }
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
      <DepartmentListContext.Provider value={{formDatas}}>
        <Container className="body_text_center">
          <Row className="d-flex justify-content-center">
            <Col sm="12">
              <ListGroup>
                <ListGroupItem className="border border-white">
                  <Row>
                    <Col>
                      {/* body - 부서 목록  */}
                      <DepartmentBoxForm />
                    </Col>
                  </Row>
                </ListGroupItem>
              
              </ListGroup>
            </Col>
          </Row>
        </Container>
      </DepartmentListContext.Provider>
    </>
  )
}

export default DepartmentListForm
import logo from './logo.svg';
import './App.css';
import Header from './pages/Header';
import Footer from './pages/Footer';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import NotFound from './pages/error/NotFound';
import EmployeeList from './pages/employee/EmployeeList';
import EmployeeDetail from './pages/employee/EmployeeDetail';
import EmployeeJobHistory from './pages/employee/EmployeeJobHistory';
import DepartmentList from './pages/department/DepartmentList';
import DepartmentDetail from './pages/department/DepartmentDetail';
import DepartmentSalaryUpdate from './pages/department/DepartmentSalaryUpdate';
import { createContext, useState } from 'react';

// context 만들기
export const CustomContext = createContext(null);

function App() {

  /// 변수 모음
  const serverHost = "http://localhost:8080";// 서버 host

  /// 상태 모음
  const [loding, setLoding] = useState(false);// 요청 처리

  return (
    <>
      <CustomContext.Provider value={{serverHost, loding, setLoding}}>
        <BrowserRouter>
          {/* header */}
          <Header />

          {/* body */}
          <Routes>

            {/* employee */}
            <Route path="/" element={<EmployeeList />} /> 
            <Route path="/employee/:id" element={<EmployeeDetail />} />
            <Route path="/employee/:id/jobhistory" element={<EmployeeJobHistory />} />

            {/* department */}
            <Route path="/department/list" element={<DepartmentList />} /> 
            <Route path="/department/:id" element={<DepartmentDetail />} />
            <Route path="/department/update/salary" element={<DepartmentSalaryUpdate />} />

            {/* 404 page */}
            <Route path="/*" element={<NotFound />} />
            {/* 클라이언트의 잘못된 요청 */}
            <Route path="/errors/notfound" element={<NotFound />} />
          </Routes>
            
          {/* footer */}
          <Footer />
        </BrowserRouter>
      </CustomContext.Provider>
    </>
  );
}

export default App;

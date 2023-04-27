import React from 'react'
import { BeatLoader } from 'react-spinners'

/**
 * Loding component
 * writer : 이호진
 * init : 2023.04.27
 * updated by writer :
 * update :
 * description : 클라이언트 요청 처리 상태를 보여주는 view component
 */
const Loding = () => {
  return (
    <div className="contentWrap">
      <div
        style={{
          position: "fixed",
          top: "50%",
          left: "50%",
          transform: "translate(-50%, -50%)",
        }}
      >
        <BeatLoader
          color="rgba(67, 226, 111, 1)"
          margin={2}
          size={10}
        />
      </div>
    </div>
  )
}

export default Loding
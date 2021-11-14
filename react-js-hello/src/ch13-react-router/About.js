import qs from "qs";

export default function About({ location }) {
  const query = qs.parse(location.search, {
    ignoreQueryPrefix: true // 이 설정을 통해 문자열 맨 앞의 ? 를 생략함
  })

  // query 는 Integer, Boolean 값을 넣어도 무조건 String 형태로 받아짐
  const showDetail = query.detail === "true"

  return (
    <div>
      <h1>소개</h1>
      <p>이 프로젝트는 리액트 라우터 기초를 실습해보는 예제 프로젝트입니다.</p>
      {showDetail && <p>detail 값을 true 로 설정하셨군요!</p>}
    </div>
  )
}
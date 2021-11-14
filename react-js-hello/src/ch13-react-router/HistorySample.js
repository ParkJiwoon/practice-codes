export default function HistorySample ({ history }) {
  return (
    <div>
      <button onClick={() => history.goBack()}>뒤로</button>
      <button onClick={() => history.push('/')}>홈으로</button>
    </div>
  )
}
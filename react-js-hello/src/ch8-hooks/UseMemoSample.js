import { useMemo, useState } from "react";

export default function UseMemoSample() {
  const [count, setCount] = useState(0);
  const [text, setText] = useState('');

  const calculateSquare = (n) => {
    // useMemo 로 감싸지 않으면 text 값이 변할 때도 매번 콘솔에 출력됨
    console.log("제곱 계산");
    return n * n;
  }

  const onChange = (e) => {
    setText(e.target.value);
  }

  const square = useMemo(() => calculateSquare(count), [count])

  return (
    <div>
      <input value={text} onChange={onChange} />
      <p>Count: {count}</p>
      <button onClick={() => setCount(count + 1)}>+1</button>
      <p>Count 10 제곱 : {square}</p>
    </div>
  )
}
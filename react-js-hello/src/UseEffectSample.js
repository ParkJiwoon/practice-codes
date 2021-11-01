import { useEffect, useState } from "react";

export default function UseEffectSample() {
  const [plus, setPlus] = useState(0);
  const [minus, setMinus] = useState(0);

  useEffect(() => {
    console.log('컴포넌트가 처음 생성되었습니다.');
  }, []);


  useEffect(() => {
    console.log('plus 변경');
  }, [plus]);


  useEffect(() => {
    console.log('minus 변경');
  }, [minus]);


  useEffect(() => {
    console.log('plus 또는 minus 변경');
  }, [plus, minus]);

  
  useEffect(() => {
    console.log('컴포넌트가 리렌더링 되었습니다.')
  })

  return (
    <div>
      <p>plus: {plus}, minus: {minus}</p>
      <button onClick={() => setPlus(plus + 1)}>Plus</button>
      <button onClick={() => setMinus(minus + 1)}>Minus</button>
    </div>
  )
}
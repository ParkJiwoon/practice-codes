import { useEffect, useState } from "react"

export default function UseEffectCleanupSample() {
  const [count, setCount] = useState(0);

  useEffect(() => {
    console.log('effect: 컴포넌트가 처음 생성됩니다.')
    
    return () => {
      console.log('cleanup: 컴포넌트가 이제 사라집니다.')
    }
  }, []);


  useEffect(() => {
    console.log('렌더링 직후 count: ' + count);

    return () => {
      console.log('렌더링 직전 count: ' + count);
    }
  });

  
  return (
    <div>
      <p>count: {count}</p>
      <button onClick={() => setCount(count + 1)}>+1</button>
    </div>
  )
}
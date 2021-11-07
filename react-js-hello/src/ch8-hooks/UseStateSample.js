import { useState } from "react";

export default function UseStateSample() {
  const [value, setValue] = useState(0);
  const [name, setName] = useState('');

  return (
    <div>
      <p>현재 카운터 값은 <b>{value}</b>입니다.</p>
      <button onClick={() => setValue(value + 1)}>+1</button>
      <button onClick={() => setValue(value - 1)}>-1</button>

      <p>--------------------------------</p>

      <input value={name} onChange={(e) => setName(e.target.value)} />
      <p>이름: <b>{name}</b></p>
    </div>
  )
}

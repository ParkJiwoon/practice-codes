import { useRef, useState } from "react"

function RefCounter() {
  const [state, setState] = useState(0);
  const ref = useRef(0);

  console.log("rendering")

  return (
    <div>
      <h1>state: {state}, ref: {ref.current}</h1>
      <button onClick={() => setState(state + 1)}>Plus State</button>
      <button onClick={() => ref.current += 1}>Plus Ref</button>
    </div>
  )
}

export default RefCounter;
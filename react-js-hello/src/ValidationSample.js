import './ValidationSample.css'

const { useState, useRef } = require("react")

const ValidationSample = () => {
  const [state, setState] = useState({
    password: '',
    clicked: false,
    validated: false
  })

  const inputRef = useRef()

  const handleChange = (e) => {
    setState({
      ...state,
      password: e.target.value
    })
  }

  const handleButtonClick = () => {
    setState({
      ...state,
      clicked: true,
      validated: state.password === '0000'
    })
    inputRef.current.focus()
  }

  return (
    <div>
      <input 
        ref={inputRef}
        type="password" 
        value={state.password} 
        onChange={handleChange}
        className={state.clicked ? (state.validated ? 'success' : 'failure') : ''}
      />
      <button onClick={handleButtonClick}>검증하기</button>
    </div>
  )
}

export default ValidationSample
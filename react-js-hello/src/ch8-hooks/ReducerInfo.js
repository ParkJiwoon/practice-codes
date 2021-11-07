import {useReducer} from "react";

function reducer(state, action) {
  return {
    ...state,
    [action.name]: action.value
  };
}

export default function ReducerInfo() {
  const [state, dispatch] = useReducer(reducer, {
    name: '',
    nickname: '',
  });

  const { name, nickname } = state;
  const onChange = (e) => {
    dispatch(e.target)
  };

  return (
    <div>
      <div>
        <input name="name" value={name} onChange={onChange} />
        <input name="nickname" value={nickname} onChange={onChange} />
      </div>
      <div>
        <p>이름: {name} </p>
        <p>닉네임: {nickname} </p>
      </div>
    </div>
  )
}
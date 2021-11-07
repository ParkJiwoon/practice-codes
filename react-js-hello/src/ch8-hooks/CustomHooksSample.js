import useInputs from "./useInputs";

export default function CustomHooksSample() {
  const [state, onChange] = useInputs({
    name: '',
    nickname: ''
  });

  const { name, nickname } = state;

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
import { MdAdd } from "react-icons/md";
import "./TodoInsert.scss";
import {useCallback, useState} from "react";

export default function TodoInsert({ onInsert }) {
  const [value, setValue] = useState('');

  const onChange = useCallback(e => {
    setValue(e.target.value);
  }, []);

  const onSubmit = useCallback(
    e => {
      onInsert(value);
      setValue('');

      // submit 이벤트는 브라우저에서 새로고침을 발생시킴
      // 이를 방지하기 위해 호출
      e.preventDefault();
    },
    [onInsert, value],
  );

  /**
   * button onClick 대신 form onSubmit 이벤트를 사용한 이유
   * onSubmit 이벤트는 인풋에서 엔터를 눌렀을 때도 발생하기 때문에 더 좋음
   */
  return (
    <form className="TodoInsert" onSubmit={onSubmit}>
      <input
        placeholder="할 일을 입력하세요"
        value={value}
        onChange={onChange}
      />
      <button type="submit">
        <MdAdd />
      </button>
    </form>
  )
}
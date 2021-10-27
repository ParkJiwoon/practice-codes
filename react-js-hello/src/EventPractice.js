import React, { useState } from "react";

const EventPractice = () => {
  const [form, setForm] = useState({
    username: '',
    message: ''
  });
  const { username, message } = form;

  // input text 값이 변경된 곳만 세팅
  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value
    });
  };

  // 버튼을 누르면 alert 을 띄우고 값을 비워줌
  const handleClick = () => {
    alert(username + ': ' + message);
    setForm({
      username: '',
      message: ''
    });
  };

  // input 값을 입력하고 엔터를 누르면 버튼 클릭 효과와 같은 이벤트 발생
  const handleKeyPress = (e) => {
    if (e.key === 'Enter') {
      handleClick();
    }
  }

  return (
    <div>
      <h1>이벤트 연습</h1>
      <input
        type="text"
        name="username"
        placeholder="사용자명"
        value={username}
        onChange={handleChange}
      />
      <input 
        type="text" 
        name="message" 
        placeholder="아무거나 입력해 보세요" 
        value={message} 
        onChange={handleChange}
        onKeyPress={handleKeyPress}
      />
      <button onClick={handleClick}>확인</button>
    </div>
  )
}

export default EventPractice;
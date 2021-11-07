import React from 'react';
import './TodoTemplate.scss';

export default function TodoTemplate({ children }) {
  return (
    <div className="TodoTemplate">
      <div className="app-title">일정 관리</div>
      <div className="content">{children}</div>
    </div>
  )
}
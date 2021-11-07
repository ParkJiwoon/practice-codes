import React from "react";
import {
  MdCheckBoxOutlineBlank,
  MdCheckBox,
  MdRemoveCircleOutline
} from "react-icons/md";
import cn from "classnames";
import "./TodoListItem.scss";

function TodoListItem({ todo, onRemove, onToggle, style }) {
  const { id, text, checked } = todo;

  return (
    <div className="TodoListItem-virtualized" style={style}>
      <div className="TodoListItem">
        <div className={cn('checkbox', { checked })} onClick={() => onToggle(id)}>
          {checked ? <MdCheckBox /> : <MdCheckBoxOutlineBlank />}
          <div className="text">{text}</div>
        </div>
        <div className="remove" onClick={() => onRemove(id)}>
          <MdRemoveCircleOutline />
        </div>
      </div>
    </div>
  );
}

// React.memo 로 감쌌기 때문에 todo, onRemove, onToggle 값이 바뀌지 않으면 렌더링 되지 않음
export default React.memo(TodoListItem);
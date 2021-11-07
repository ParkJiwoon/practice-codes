import React, {useCallback} from "react";
import { List } from "react-virtualized";
import TodoListItem from "./TodoListItem";
import "./TodoList.scss"

function TodoList({ todos, onRemove, onToggle }) {
  const rowRenderer = useCallback(({ index, key, style }) => {
    const todo = todos[index];

    return (
      <TodoListItem
        todo={todo}
        key={key}
        onRemove={onRemove}
        onToggle={onToggle}
        style={style}
      />
    );
  }, [todos, onRemove, onToggle]);

  return (
    <List
      className="TodoList"
      width={512} // 전체 크기
      height={513} // 전체 높이
      rowCount={todos.length} // 리스트 아이템 갯수
      rowHeight={57} // 리스트 아이템 높이
      rowRenderer={rowRenderer} // 리스트 아이템 렌더링 할 때 쓰는 함수
      list={todos}  // 배열
      style={{ outline: 'none' }} // List 에 기본 적용되는 outline 스타일 제거
    />
  );
}

export default React.memo(TodoList);
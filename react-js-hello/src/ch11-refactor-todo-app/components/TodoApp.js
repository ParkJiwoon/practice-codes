import TodoTemplate from "./TodoTemplate";
import TodoInsert from "./TodoInsert";
import TodoList from "./TodoList";
import {useCallback, useReducer, useRef, useState} from "react";

function createBulkTodos() {
  const array = [];
  for (let i = 1; i <= 2500; i++) {
    array.push({
      id: i,
      text: `할 일 ${i}`,
      checked: false,
    });
  }
  return array;
}

function todoReducer(todos, action) {
  switch (action.type) {
    case 'INSERT':
      // action = { type: 'INSERT', todo: { id: 1, text: 'todo', checked: false } }
      return todos.concat(action.todo);
    case 'REMOVE':
      // action = { type: 'REMOVE', id: 1 }
      return todos.filter(todo => todo.id !== action.id);
    case 'TOGGLE':
      // action = { type: 'TOGGLE', id: 1 }
      return todos.map(todo =>
        todo.id === action.id ? { ...todo, checked: !todo.checked } : todo
      );
    default:
      return todos;
  }
}

function TodoReducerApp() {
  // 두번째 파라미터에 undefined 를 넣고 세번째에 초기값 만드는 메소드를 넣음
  const [todos, dispatch] = useReducer(todoReducer, undefined, createBulkTodos);

  const nextId = useRef(2501);

  const onInsert = useCallback(text => {
    const todo = {
      id: nextId.current,
      text,
      checked: false,
    };
    dispatch({ type: 'INSERT', todo });
    nextId.current += 1;
  }, []);

  const onRemove = useCallback(id => {
    dispatch({ type: 'REMOVE', id });
  }, []);

  const onToggle = useCallback(id => {
    dispatch({ type: 'TOGGLE', id });
  }, []);

  return (
    <TodoTemplate>
      <TodoInsert onInsert={onInsert} />
      <TodoList todos={todos} onRemove={onRemove} onToggle={onToggle} />
    </TodoTemplate>
  )
}

export default function TodoApp() {
  // useState 기본값으로 useState(createBulkTodos()) 처럼 이벤트를 넘기면 리렌더링 될때마다 호출됨
  // 함수 이름만 넘기면 청므 렌더링 될때만 호출
  const [todos, setTodos] = useState(createBulkTodos);

  // 고유값으로 사용될 id
  const nextId = useRef(2501);

  // props 로 전달할 함수를 만들 때는 useCallback 으로 감싸는게 좋음
  const onInsert = useCallback(text => {
    const todo = {
      id: nextId.current,
      text,
      checked: false,
    };
    setTodos(todos => todos.concat(todo));
    nextId.current += 1;
  }, []);

  const onRemove = useCallback(id => {
    setTodos(todos => todos.filter(todo => todo.id !== id))
  }, []);

  const onToggle = useCallback(id => {
    setTodos(todos =>
      todos.map(todo =>
          todo.id === id ? { ...todo, checked: !todo.checked } : todo
      )
    );
  }, []);

  return (
    <TodoTemplate>
      <TodoInsert onInsert={onInsert} />
      <TodoList todos={todos} onRemove={onRemove} onToggle={onToggle} />
    </TodoTemplate>
  )
}
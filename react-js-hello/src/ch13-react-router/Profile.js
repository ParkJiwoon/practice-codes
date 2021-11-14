const data = {
  vue: {
    name: "뷰",
    description: "SPA 를 위한 뷰 프레임워크"
  },
  react: {
    name: "리액트",
    description: "SPA 를 위한 뷰 라이브러리"
  }
}

export default function Profile ({ match }) {
  const { username } = match.params;
  const profile = data[username];

  if (!profile) {
    return <div>존재하지 않는 사용자입니다.</div>
  }

  return (
    <div>
      <h3>{username}({profile.name})</h3>
      <p>{profile.description}</p>
    </div>
  )
}
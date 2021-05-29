# @Transactional(rollbackFor = ?)

- 기본값: RuntimeException, Error
- 사용법: `@Transactional(rollbackFor = {IOException.class, ClassNotFoundException.class})`

사용할 때 `@Transactional(rollbackFor = IOException.class)` 처럼 Exception 을 하나만 지정한다면 중괄호를 생략할 수 있습니다.

기본적으로 트랜잭션은 종료 시 변경된 데이터를 커밋합니다.

하지만 `@Transactional` 에서 `rollbackFor` 속성을 지정하면 특정 Exception 발생 시 데이터를 커밋하지 않고 롤백하도록 변경할 수 있습니다.

기본값은 `{}` 라고 나와있지만 사실 `RuntimeException` 과 `Error` 가 세팅되어 있습니다.

내부 로직으로 들어가 설명을 보면 둘 다 예측 불가능한 예외 상황이기 때문에 기본값으로 들어가 있다고 합니다.

중요한 점은 이 값은 그냥 기본값이 아니라 아예 지정된 값이기 때문에 `rollbackFor` 속성으로 다른 Exception 을 추가해도 `RuntimeException` 이나 `Error` 는 여전히 데이터를 롤백합니다.

만약 강제로 데이터 롤백을 막고 싶다면 `noRollbackFor` 옵션으로 지정해주면 됩니다.
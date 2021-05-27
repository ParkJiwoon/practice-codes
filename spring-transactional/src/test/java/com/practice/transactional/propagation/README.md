# @Transactional(propagation = ?)

- `REQUIRED`: 기본값이며 부모 트랜잭션이 존재할 경우 참여하고 없는 경우 새 트랜잭션을 시작
- `SUPPORTS`: 부모 트랜잭션이 존재할 경우 참여하고 없는 경우 non-transactional 상태로 실행
- `MANDATORY`: 부모 트랜잭션이 있으면 참여하고 없으면 예외 발생
- `REQUIRES_NEW`: 부모 트랜잭션을 무시하고 무조건 새로운 트랜잭션이 생성
- `NOT_SUPPORTED`: non-transactional 상태로 실행하며 부모 트랜잭션이 존재하는 경우 일시 정지시킴
- `NEVER`: non-transactional 상태로 실행하며 부모 트랜잭션이 존재하는 경우 예외 발생
- `NESTED`:
    - 부모 트랜잭션과는 별개의 중첩된 트랜잭션을 만듬
    - 부모 트랜잭션의 커밋과 롤백에는 영향을 받지만 자신의 커밋과 롤백은 부모 트랜잭션에게 영향을 주지 않음
    - 부모 트랜잭션이 없는 경우 새로운 트랜잭션을 만듬 (`REQUIRED` 와 동일)
    - DB 가 SAVEPOINT 를 지원해야 사용 가능 (Oracle)
    - `JpaTransactionManager` 에서는 지원하지 않음

<br>

트랜잭셔널 전파 옵션을 설정할 수 있습니다.

non-transactional 상태에 대해 한가지 착각을 한 점이 있었는데 `NOT_SUPPORTED` 같은 트랜잭션은 실행은 되지만 커밋, 롤백이 반영되지 않는 상태입니다.

따라서 `TransactionSynchronizationManager.getCurrentTransactionName()` 메소드로 트랜잭션 이름은 구할 수 있지만 JPA Dirty Checking 은 동작하지 않습니다.
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

# 테스트 방법

- 부모 트랜잭션과 자식 트랜잭션을 담당하는 `ParentService`, `ChildSerivde` 를 선언
- 부모 트랜잭션에 참여 여부는 `TransactionSynchronizationManager.getCurrentTransactionName()` 로 현재 트랜잭션의 이름을 확인
- 트랜잭션 동작 여부는 Dirty Checking 으로 확인
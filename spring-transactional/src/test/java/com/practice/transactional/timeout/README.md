# @Transactional(timeout = ?)

- 기본값: -1
- 사용법: `@Transactional(timeout = 2)`

지정한 시간 내에 해당 메소드 수행이 완료되이 않은 경우 `JpaSystemException` 을 발생시킵니다.

`JpaSystemException` 은 `RuntimeException` 을 상속받기 때문에 데이터 역시 롤백 처리 됩니다.

초 단위로 지정할 수 있으며 기본값인 -1 인 경우엔 timeout 을 지원하지 않습니다.

지정된 timeout 을 초과하면 다음과 같은 에러 로그를 보여줍니다.

```
org.springframework.orm.jpa.JpaSystemException: transaction timeout expired; nested exception is org.hibernate.TransactionException: transaction timeout expired
```

<br>

## 5.1. noRollbackFor 을 같이 사용한다면?

호기심에 `noRollbackFor = {RuntimeException.class, JpaSystemException.class}` 옵션을 추가하고 타임아웃 테스트를 해보았습니다.

Exception 은 발생하지만 롤백 처리가 됩니다.
# Skills

- Spring Boot
- Spring Webflux
- MongoDB

# Relations

`Cart` 1 : N `CartItem` 1 : 1 `Item` 

# Domain

`Item` (상품)
- 이름과 가격을 가짐

`CartItem`
- 상품과 수량을 갖고 있음

`Cart` (장바구니)
- `List<CartItem>` 을 갖고 있음
- 각 상품의 수량까지 포함해서 카트에 담고 있음

# 요구사항

상품 장바구니에 담기
- 현재 장바구니를 조회하고 없으면 비어 있는 새 장바구니 생성
- 장바구니에 담은 상품이 이미 장바구니에 있던 상품이라면 수량만 1 증가시킴
- 기존에 없던 상품이라면 상품 정보를 표시하고 수량을 1로 표시

# Dockerfile

Dockerfile 없이 플러그인을 활용해 도커 이미지를 빌드 가능하기 때문에 README 에 기록해둠

`./gradlew bootBuildImage` 명령어로 빌드 가능

```dockerfile
# 빌더로 사용할 컨테이너 생성
FROM adoptopenjdk/openjdk11:latest as builder
WORKDIR application
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} application.jar

# extract 명령어로 layer 추출
RUN java -Djarmode=layertools -jar application.jar extract

# 두번째 컨테이너 생성
FROM adoptopenjdk/openjdk11:latest
WORKDIR application

# 빌더 컨테이너에서 추출한 여러 레이러를 두번째 컨테이너에 복사
# COPY 명령은 도커의 계층 캐시 알고리즘이 적용돼서 최소한의 변화만 반영됨
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
#COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./

# java -jar 대신 스프링 부트의 커스텀 런처 (custom launcher) 로 애플리케이션 실행
# 이 런처는 애플리케이션 시작 시 불필요한 JAR 파일 압축 해제를 하지 않으므로 효율적
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
```
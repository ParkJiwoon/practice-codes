# OAuth 2.0 Sequence Diagram

Google 로그인을 예시로 작성

```mermaid
sequenceDiagram
  actor U as 사용자
  participant C as Client
  participant S as Server
  participant A as Google Authorization Server
  participant R as Google Resource Server

  
  U->>+C: Google 로그인 버튼 클릭
  C->>-U: Google 로그인 페이지 노출
  U->>+A: Google 로그인
  A->>-C: Google 로그인 완료 후 등록된 URI 로 Redirect<br>파라미터로 Authorization Code 전달
  C->>+S: Authorization Code 로 로그인 요청
  S->>+A: Authorization Code 로<br>Google Access Token 요청
  A->>-S: Google Access Token
  S->>+R: Google Profile 정보<br>with Access Token
  R->>-S: Google Profile 정보
  S->>S: 첫 로그인이면 가입 처리
  S->>-C: 사용자 ID 로 Access Token 발급
  C->>C: Access Token 저장
  C->>U: 로그인 성공 처리
```

<br>

# 전체적인 흐름을 Class Diagram 으로 표현

```mermaid
classDiagram
  class Member {
    <<Entity>>
    +Long id
    +String email
    +String nickname
    +OAuthProvider oAuthProvider;
  }
  class MemberRepository {
    <<interface>>
    +findByEmail(String email) Optional~Member~
  }
  MemberRepository ..> Member

  class JwtTokenProvider {
    +generate(String subject, Date expiredAt) String
    +extractSubject(String accessToken) String
  }

  class AuthTokens {
    +String accessToken
    +String refreshToken
    +String grantType
    +Long expiresIn
  }
  class AuthTokensGenerator {
    +generate(Long memberId) AuthTokens
    +extractMemberId(String accessToken) Long
  }
  AuthTokensGenerator ..> JwtTokenProvider
  AuthTokensGenerator ..> AuthTokens

  class OAuthApiClient {
    <<interface>>
    +oAuthProvider()* OAuthProvider
    +requestAccessToken(OAuthLoginParams params)* String
    +requestOauthInfo(String accessToken)* OAuthInfoResponse
  }
  class RequestOAuthInfoService {
    -Map~OAuthProvider, OAuthApiClient~ clients
    +request(OAuthLoginParams params) OAuthInfoResponse
  }
  RequestOAuthInfoService ..> OAuthApiClient

  class OAuthLoginService {
    -MemberRepository memberRepository
    -AuthTokensGenerator authTokensGenerator
    -RequestOAuthInfoService requestOAuthInfoService
    +login(OAuthLoginParams params) AuthTokens
  }
  OAuthLoginService ..> MemberRepository
  OAuthLoginService ..> AuthTokensGenerator
  OAuthLoginService ..> RequestOAuthInfoService

  class AuthController {
    -OAuthLoginService oAuthLoginService
    +loginKakao(KakaoLoginParamas params) ResponseEntity~AuthTokens~
    +loginNaver(NaverLoginParams params) ResponseEntity~AuthTokens~
  }
  AuthController ..> OAuthLoginService
```

<br>

# OAuth 도메인 Class Diagram

```mermaid
classDiagram
  class OAuthProvider {
    <<Enum>>
    KAKAO
    NAVER
  }

  class OAuthApiClient {
    <<interface>>
    +oAuthProvider()* OAuthProvider
    +requestAccessToken(OAuthLoginParams params)* String
    +requestOauthInfo(String accessToken)* OAuthInfoResponse
  }
  class KakaoApiClient {
    -RestTemplate restTemplate
  }
  class NaverApiClient {
    -RestTemplate restTemplate
  }
  OAuthApiClient <|-- KakaoApiClient : implements
  OAuthApiClient <|-- NaverApiClient : implements
  
  class OAuthLoginParams {
    <<interface>>
    +oAuthProvider()* OAuthProvider
    +makeBody()* MultiValueMap~StringString~
  }
  class KakaoLoginParams {
    -String grantType
    -String clientId
    -String authorizationCode
  }
  class NaverLoginParams {
    -String grantType
    -String clientId
    -String authorizationCode
    -String state
  }
  OAuthLoginParams <|-- KakaoLoginParams : implements
  OAuthLoginParams <|-- NaverLoginParams : implements

  class OAuthInfoResponse {
    <<interface>>
    +getEmail()* String
    +getNickname()* String
    +getOAuthProvider()* OAuthProvider
  }
  class KakaoInfoResponse {
  }
  class NaverInfoResponse {
  }
  OAuthInfoResponse <|-- KakaoInfoResponse : implements
  OAuthInfoResponse <|-- NaverInfoResponse : implements
```

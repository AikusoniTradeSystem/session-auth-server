# session-auth-server
세션 인증 방식의 서버 (스프링)

### JAR 환경변수 목록
- spring.profiles.active (예: local , dev, prod)
- DB_DRIVER_CLASS_NAME (예: org.h2.Driver, com.mysql.cj.jdbc.Driver)
- DB_URL (예: jdbc:h2:mem:testdb)
- DB_USER (예: sa)
- DB_PASSWORD (예: password)
- SERVER_PORT (예: 8000)
- LOG_HOME (예: logs)

### gradle.properties
gradle 빌드시 GitHub Package Repository 접근을 위해 github 접속 토큰이 필요합니다.

```
# gradle.properties
gpr.user=test@aikusoni.github.io # 자신의 깃허브 이메일주소
gpr.token=ghp_ABCDEF123456 # github에서 발급받은 package repository 토큰
```
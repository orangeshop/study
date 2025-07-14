package study.basespring.config;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.sql.SQLException;

@Configuration
@Profile("!test") // 테스트 환경에서는 실행하지 않음
public class H2ServerConfig {

    @Value("${h2.server.port:9092}")
    private String h2ServerPort;

    /**
     * H2 TCP 서버를 시작합니다.
     * 이를 통해 외부 툴(IntelliJ, DBeaver 등)에서도 접속 가능합니다.
     */
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2TcpServer() throws SQLException {
        return Server.createTcpServer(
            "-tcp",
            "-tcpAllowOthers",
            "-tcpPort", h2ServerPort
        );
    }

    /**
     * H2 Web Console 서버를 시작합니다.
     * Spring Boot의 기본 H2 Console과는 별개로 독립적인 웹 콘솔을 제공합니다.
     */
    @Bean(initMethod = "start", destroyMethod = "stop")
    @Profile("h2-web") // 필요시에만 활성화
    public Server h2WebServer() throws SQLException {
        return Server.createWebServer(
            "-web",
            "-webAllowOthers",
            "-webPort", "8082"
        );
    }
}

package practice.springsecurity.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth -> auth
                // 아래 경로들은 모두 접근 가능(로그인 없이도 접근)
                .requestMatchers("/", "/login", "/css/**", "/js/**").permitAll()

                // /index 경로는 로그인(인증)이 필요
                .requestMatchers("/index").authenticated()

                // 그 외 나머지 요청들은 어떻게 할지(denyAll, permitAll 등)
                .anyRequest().denyAll()
            )
            // 2) 폼 로그인 사용
            .formLogin(form -> form
                // 로그인 페이지 커스텀할 때 사용 (직접 만든 /login 페이지가 있다면)
                // .loginPage("/login")

                // 로그인 성공 시, 이동할 경로
                 .defaultSuccessUrl("/index", true)

                // 로그인 페이지 없이 스프링 시큐리티에서 제공하는 기본 로그인 페이지 쓰려면 설정 생략 가능
                .permitAll()
            )
            // 3) 로그아웃 설정(원하는 설정대로)
            .logout(logout -> logout
                // 로그아웃 후 이동할 경로 등
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails user = User.builder()
            .username("admin")
            .password(encoder.encode("admin"))
            .roles("USER")
            .build();

        return new InMemoryUserDetailsManager(user);
    }

    // (2) 비밀번호 인코더
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

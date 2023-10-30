package it.almaviva.impleme.tributi.anagrafe.config;

import it.almaviva.eai.ljsa.sdk.core.bootstrap.EnableLjsa;
import it.almaviva.eai.ljsa.sdk.core.security.LjsaFilter;
import it.almaviva.impleme.tributi.anagrafe.permission.IgnoreSecurity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.stream.Stream;

@Configuration
@EnableLjsa
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private LjsaFilter ljsaProfileManagerFilter;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().authorizeRequests().requestMatchers(PathRequest.toStaticResources().atCommonLocations())
        .authenticated()  .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler()).and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .addFilterBefore(ljsaProfileManagerFilter, BasicAuthenticationFilter.class);

    http.cors();
  }

  @Override
  public void configure(WebSecurity web) {
    web.ignoring().antMatchers("/v2/api-docs", "/actuator/**", "/swagger-resources/**", "/swagger-ui.html**",
        "/webjars/**");

        web.ignoring()
        .antMatchers(HttpMethod.GET, "/v2/enti")
        .antMatchers(HttpMethod.GET, "/v2/enti/*/tributi/*/tariffe")
        .antMatchers(HttpMethod.GET, "v2/enti/*/tributi/*/attributi")
        .antMatchers(HttpMethod.GET, "v2/enti/*/tributi");

    // ignoredUrls().forEach( matcher -> {
    // web.ignoring().antMatchers(matcher.getMethod(), matcher.getUrl());
    // });
  }

  private Stream<MethodMatcher> ignoredUrls() {
    Reflections reflections = new Reflections(new ConfigurationBuilder()
        .setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
        .setUrls(ClasspathHelper.forPackage("it.almaviva.impleme.tributi.anagrafe.controllers")).filterInputsBy(
            new FilterBuilder().include(FilterBuilder.prefix("it.almaviva.impleme.tributi.anagrafe.controllers"))));
    return reflections.getTypesAnnotatedWith(RequestMapping.class).stream()
        .flatMap(clazz -> Stream.of(clazz.getMethods()))
        .filter(method -> null != method.getAnnotation(IgnoreSecurity.class)).map(method -> {
          RequestMapping root = method.getDeclaringClass().getAnnotation(RequestMapping.class);
          MethodMatcher mm = getMatchers(method);
          mm.setUrl((root.value()[0] + "/" + mm.getUrl()).replaceAll("\\{(.*?)\\}", "*"));
          return mm;
        });
  }

  private MethodMatcher getMatchers(Method method) {
    GetMapping getMapping = method.getAnnotation(GetMapping.class);
    PostMapping postMapping = method.getAnnotation(PostMapping.class);
    DeleteMapping deleteMapping = method.getAnnotation(DeleteMapping.class);
    PutMapping putMapping = method.getAnnotation(PutMapping.class);
    PatchMapping patchMapping = method.getAnnotation(PatchMapping.class);
    RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);

    if (null != getMapping) {
      return new MethodMatcher(HttpMethod.GET, getMapping.value()[0]);
    }
    if (null != postMapping) {
      return new MethodMatcher(HttpMethod.POST, postMapping.value()[0]);
    }
    if (null != deleteMapping) {
      return new MethodMatcher(HttpMethod.DELETE, deleteMapping.value()[0]);
    }
    if (null != putMapping) {
      return new MethodMatcher(HttpMethod.PUT, putMapping.value()[0]);
    }
    if (null != patchMapping) {
      return new MethodMatcher(HttpMethod.PATCH, patchMapping.value()[0]);
    }
    if (null != requestMapping) {
      return new MethodMatcher(HttpMethod.resolve(requestMapping.method()[0].name()), requestMapping.value()[0]);
    }

    return null;

  }

  @Bean
  public AccessDeniedHandler accessDeniedHandler() {
    return new CustomAccessDeniedHandler();
  }

}




@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class MethodMatcher {

  private HttpMethod method;
  private String url;

}

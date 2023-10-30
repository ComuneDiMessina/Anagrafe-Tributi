package it.almaviva.impleme.tributi.anagrafe.config;

import lombok.SneakyThrows;
import org.json.JSONObject;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
  @SneakyThrows
  @Override
  public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
    httpServletResponse.getWriter().write(new JSONObject()
            .put("timestamp", new Date())
            .put("message", "Operazione non consentita: utente non autorizzato")
            .toString());
    httpServletResponse.setContentType("application/json;charset=UTF-8");
    httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
  }
}

package team18.team18_be.config.resolver;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import team18.team18_be.auth.dto.request.UserIdRequest;

public class LoginUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    boolean hasLoginUserAnnotation = parameter.hasParameterAnnotation(LoginUser.class);
    boolean isTypeOfUserIdRequest = UserIdRequest.class.isAssignableFrom(parameter.getParameterType());

    return hasLoginUserAnnotation && isTypeOfUserIdRequest;
  }

  @Override
  public Object resolveArgument(MethodParameter parameter,
                                ModelAndViewContainer mavContainer,
                                NativeWebRequest webRequest,
                                WebDataBinderFactory binderFactory) throws Exception {

    HttpServletRequest request = webRequest.getNativeRequest(
            HttpServletRequest.class);
    Long userId = (Long) request.getAttribute("userId");

    return new UserIdRequest(userId);
  }
}

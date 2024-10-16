package team18.team18_be.config.resolver;

import jakarta.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import team18.team18_be.auth.entity.User;
import team18.team18_be.auth.repository.AuthRepository;
import team18.team18_be.exception.ErrorMessage;

public class LoginUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

  private final AuthRepository authRepository;

  public LoginUserMethodArgumentResolver(AuthRepository authRepository) {
    this.authRepository = authRepository;
  }

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    boolean hasLoginUserAnnotation = parameter.hasParameterAnnotation(LoginUser.class);
    boolean isTypeOfUserIdRequest = User.class.isAssignableFrom(parameter.getParameterType());

    return hasLoginUserAnnotation && isTypeOfUserIdRequest;
  }

  @Override
  public Object resolveArgument(MethodParameter parameter,
      ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest,
      WebDataBinderFactory binderFactory) {

    HttpServletRequest request = webRequest.getNativeRequest(
        HttpServletRequest.class);
    Long userId = (Long) request.getAttribute("userId");

    if (userId == null) {
      throw new IllegalArgumentException(
          ErrorMessage.NOT_FOUND_USER_ID_IN_HEADER.getErrorMessage());
    }

    return getUser(userId);
  }

  private User getUser(Long userId) {
    return authRepository.findById(userId)
        .orElseThrow(
            () -> new NoSuchElementException(ErrorMessage.NOT_FOUND_USER.getErrorMessage()));
  }
}

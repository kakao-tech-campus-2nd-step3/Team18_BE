package team18.team18_be.config.GCS;

import java.io.IOException;
import java.util.Optional;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUtil {

  // MultipartFile의 getBytes()를 안전하게 호출하는 메서드
  public Optional<byte[]> safelyGetBytes(MultipartFile file) {
    try {
      return Optional.of(file.getBytes());
    } catch (IOException e) {
      return Optional.empty();
    }
  }
}
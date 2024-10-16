package team18.team18_be.config.GCS;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import java.io.IOException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Component
@RequiredArgsConstructor
public class GcsUploader {

  private final Storage storage;

  @Value("${gcp.bucket.name}")
  private String bucketName;

  // MultipartFile을 GCS에 업로드
  public Optional<String> upload(MultipartFile multipartFile, String dirName, String userId) {
    try {
      String fileName = dirName + "/" + userId +"_"+ multipartFile.getOriginalFilename();

      // BlobInfo 설정
      BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, fileName).build();

      // 파일을 GCS에 업로드
      storage.create(blobInfo, multipartFile.getBytes());

      // GCS URL 생성
      String fileUrl = String.format("https://storage.googleapis.com/%s/%s", bucketName, fileName);

      return Optional.of(fileUrl);
    } catch (IOException e) {
      log.error("파일 업로드 중 오류 발생", e);
      return Optional.empty();
    }
  }
}
package team18.team18_be.config.GCS;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GcsUploader {

  private final Storage storage;

  @Value("${gcp.bucket.name}")
  private String bucketName;

  // MultipartFile을 GCS에 업로드
  public Optional<String> upload(byte[] imageFile, String dirName, String fileName) {
    String GCSfileName = dirName +"/"+ fileName;
    // BlobInfo 설정
    BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, GCSfileName).build();

    // 파일을 GCS에 업로드
    storage.create(blobInfo, imageFile);

    // GCS URL 생성
    String fileUrl = String.format("https://storage.googleapis.com/%s/%s", bucketName, GCSfileName);

    return Optional.of(fileUrl);
  }
}

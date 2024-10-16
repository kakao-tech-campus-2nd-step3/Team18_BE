package team18.team18_be.config.GCS;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import java.io.FileInputStream;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GcsConfig {

  @Value("${gcp.credentials.file}")
  private String credentialsFile; // 서비스 계정 키 파일 경로

  @Value("${gcp.project.id}")
  private String projectId;

  @Bean
  public Storage storage() throws IOException {
    return StorageOptions.newBuilder()
        .setProjectId(projectId)
        .setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream(credentialsFile)))
        .build()
        .getService();
  }
}

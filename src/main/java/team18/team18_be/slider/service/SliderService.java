package team18.team18_be.slider.service;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import team18.team18_be.slider.dto.response.SliderImageResponse;

@Service
public class SliderService {

  public static final String IMAGE = "image/";
  private static final String DIRECTORY_IMAGES = "Slider";
  private final Storage storage;
  @Value("${gcp.bucket.name}")
  private String BUCKET_NAME;

  public SliderService(Storage storage) {
    this.storage = storage;
  }

  public List<SliderImageResponse> getSliderImageUrls() {
    List<SliderImageResponse> imageUrls = new ArrayList<>();
    Bucket bucket = storage.get(BUCKET_NAME);

    for (Blob blob : bucket.list(Storage.BlobListOption.prefix(DIRECTORY_IMAGES)).iterateAll()) {
      if (!blob.isDirectory() && blob.getContentType() != null && blob.getContentType().startsWith(
          IMAGE)) {
        String imageUrl = blob.getMediaLink();
        imageUrls.add(new SliderImageResponse(imageUrl));
      }
    }

    return imageUrls;
  }
}

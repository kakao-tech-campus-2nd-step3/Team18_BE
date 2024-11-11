package team18.team18_be.slider.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team18.team18_be.slider.dto.response.SliderImageResponse;
import team18.team18_be.slider.service.SliderService;

@Tag(name = "슬라이더", description = "슬라이더 관련 API")
@RestController
@RequestMapping("/api/slides")
public class SliderController {

  private final SliderService sliderService;

  public SliderController(SliderService sliderService) {
    this.sliderService = sliderService;
  }

  @GetMapping
  public ResponseEntity<List<SliderImageResponse>> getSliderBanner() {
    return ResponseEntity.ok().body(sliderService.getSliderImageUrls());
  }
}

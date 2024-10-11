package team18.team18_be.config.infrastructure;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import team18.team18_be.config.infrastructure.dto.Message;
import team18.team18_be.config.infrastructure.dto.RequestToAi;
import team18.team18_be.recruitment.dto.request.RecruitmentRequest;

@Component
public class OpenAiService {

  @Value("${chatgpt.api-key}")
  private String gptKey;

  private final String chatGptUrl = "https://api.openai.com/v1/chat/completions";

  private final RestTemplate restTemplate;
  private final ObjectMapper objectMapper;

  public OpenAiService(RestTemplateBuilder restTemplateBuilder, ObjectMapper objectMapper) {
    this.restTemplate = restTemplateBuilder.build();
    this.objectMapper = objectMapper;
  }

  public String summation(RecruitmentRequest recruitmentRequest) throws JsonProcessingException {
    return getResponseFromAi(recruitmentRequest.toString() + " 를 한국말로만 자연스럽게 요약해줘");
  }

  public String translateKoreanToVietnamese(String message) throws JsonProcessingException {
    return getResponseFromAi(message + " 라는 말을 베트남어로 번역해주는데, 딱 베트남어만 리턴해줘");
  }

  public String getResponseFromAi(String content) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    ResponseEntity<Object> feedBack = sandMessageToAi(content);
    String responseBody = objectMapper.writeValueAsString(feedBack.getBody());

    JsonNode rootNode = objectMapper.readTree(responseBody);
    String extractedContent = rootNode.path("choices").get(0).path("message").path("content").asText();

    return extractedContent;
  }

  private ResponseEntity<Object> sandMessageToAi(String receivedMessages) throws JsonProcessingException {
    Message message = new Message("user",receivedMessages);
    List<Message> messageList = new LinkedList<>();
    messageList.add(message);

    RequestToAi diaryRequestToGpt = new RequestToAi("gpt-4o-mini",messageList);

    String jsonBody = objectMapper.writeValueAsString(diaryRequestToGpt);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(APPLICATION_JSON);
    headers.add("Authorization","Bearer " + gptKey);

    HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, headers);
    return restTemplate.exchange(chatGptUrl, HttpMethod.POST, requestEntity, Object.class);
  }

}

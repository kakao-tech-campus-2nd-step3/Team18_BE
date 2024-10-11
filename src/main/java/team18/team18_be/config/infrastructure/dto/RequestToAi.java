package team18.team18_be.config.infrastructure.dto;

import java.util.List;

public record RequestToAi(
    String model,
    List<Message> messages
) {

}

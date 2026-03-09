package com.project.shopapp.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserResponse {
    @JsonProperty("message")
    private String message;

    @JsonProperty("user")
    private UserResponse user;
}

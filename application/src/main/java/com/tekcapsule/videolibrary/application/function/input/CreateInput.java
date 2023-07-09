package com.tekcapsule.videolibrary.application.function.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapsule.videolibrary.domain.model.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class CreateInput {
    private String title;
    private String topicCode;
    private String author;
    private String publisher;
    private String duration;
    private String videoUrl;
    private String summary;
    private String description;
    private String imageUrl;
    private Promotion promotion;
}
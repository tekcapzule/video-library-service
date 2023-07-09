package com.tekcapsule.videolibrary.domain.command;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapsule.core.domain.Command;
import com.tekcapsule.videolibrary.domain.model.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class CreateCommand extends Command {
    private String title;
    private String topicCode;
    private String author;
    private String publisher;
    private String duration;
    private String videoUrl;
    private String summary;
    private String description;
    private List<Module> modules;
    private String imageUrl;
    private Promotion promotion;
}
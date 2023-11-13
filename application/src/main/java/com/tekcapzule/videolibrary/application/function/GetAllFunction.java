package com.tekcapzule.videolibrary.application.function;

import com.tekcapzule.core.domain.EmptyFunctionInput;
import com.tekcapzule.core.utils.HeaderUtil;
import com.tekcapzule.core.utils.Outcome;
import com.tekcapzule.core.utils.Stage;
import com.tekcapzule.videolibrary.application.config.AppConfig;
import com.tekcapzule.videolibrary.domain.model.Video;
import com.tekcapzule.videolibrary.domain.service.VideoLibraryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;

@Component
@Slf4j
public class GetAllFunction implements Function<Message<EmptyFunctionInput>, Message<List<Video>>> {

    private final VideoLibraryService videoLibraryService;

    private final AppConfig appConfig;

    public GetAllFunction(final VideoLibraryService videoLibraryService, final AppConfig appConfig) {
        this.videoLibraryService = videoLibraryService;
        this.appConfig = appConfig;
    }


    @Override
    public Message<List<Video>> apply(Message<EmptyFunctionInput> getAllInputMessage) {

        Map<String, Object> responseHeaders = new HashMap<>();
        List<Video> cours = new ArrayList<>();
        String stage = appConfig.getStage().toUpperCase();
        try {
            log.info("Entering get all video Function");
            cours = videoLibraryService.findAll();
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.SUCCESS);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.ERROR);
        }
        return new GenericMessage<>(cours, responseHeaders);
    }
}
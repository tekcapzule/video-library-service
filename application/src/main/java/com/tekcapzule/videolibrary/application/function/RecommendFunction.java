package com.tekcapzule.videolibrary.application.function;

import com.tekcapzule.core.domain.Origin;
import com.tekcapzule.core.utils.HeaderUtil;
import com.tekcapzule.core.utils.Outcome;
import com.tekcapzule.core.utils.PayloadUtil;
import com.tekcapzule.core.utils.Stage;
import com.tekcapzule.videolibrary.application.config.AppConfig;
import com.tekcapzule.videolibrary.application.function.input.RecommendInput;
import com.tekcapzule.videolibrary.application.mapper.InputOutputMapper;
import com.tekcapzule.videolibrary.domain.command.RecommendCommand;
import com.tekcapzule.videolibrary.domain.service.VideoLibraryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Component
@Slf4j
public class RecommendFunction implements Function<Message<RecommendInput>, Message<Void>> {

    private final VideoLibraryService videoLibraryService;

    private final AppConfig appConfig;

    public RecommendFunction(final VideoLibraryService videoLibraryService, final AppConfig appConfig) {
        this.videoLibraryService = videoLibraryService;
        this.appConfig = appConfig;
    }


    @Override
    public Message<Void> apply(Message<RecommendInput> recommendInputMessage) {
        Map<String, Object> responseHeaders = new HashMap<>();
        Map<String, Object> payload = new HashMap<>();
        String stage = appConfig.getStage().toUpperCase();
        try {
            RecommendInput recommendInput = recommendInputMessage.getPayload();
            log.info(String.format("Entering recommend videolibrary Function -  videolibrary Id:%s", recommendInput.getVideoId()));
            Origin origin = HeaderUtil.buildOriginFromHeaders(recommendInputMessage.getHeaders());
            RecommendCommand recommendCommand = InputOutputMapper.buildRecommendCommandFromRecommendInput.apply(recommendInput, origin);
            videoLibraryService.recommend(recommendCommand);
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.SUCCESS);
            payload = PayloadUtil.composePayload(Outcome.SUCCESS);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.ERROR);
            payload = PayloadUtil.composePayload(Outcome.ERROR);
        }
        return new GenericMessage(payload, responseHeaders);

    }
}
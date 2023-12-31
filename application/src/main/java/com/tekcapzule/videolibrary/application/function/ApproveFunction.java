package com.tekcapzule.videolibrary.application.function;

import com.tekcapzule.core.domain.Origin;
import com.tekcapzule.core.utils.HeaderUtil;
import com.tekcapzule.core.utils.Outcome;
import com.tekcapzule.core.utils.PayloadUtil;
import com.tekcapzule.core.utils.Stage;
import com.tekcapzule.videolibrary.application.config.AppConfig;
import com.tekcapzule.videolibrary.application.function.input.ApproveVideoLibraryInput;
import com.tekcapzule.videolibrary.application.mapper.InputOutputMapper;
import com.tekcapzule.videolibrary.domain.command.ApproveCommand;
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
public class ApproveFunction implements Function<Message<ApproveVideoLibraryInput>, Message<Void>> {

    private final VideoLibraryService videoLibraryService;

    private final AppConfig appConfig;

    public ApproveFunction(final VideoLibraryService videoLibraryService, final AppConfig appConfig) {
        this.videoLibraryService = videoLibraryService;
        this.appConfig = appConfig;
    }

    @Override
    public Message<Void> apply(Message<ApproveVideoLibraryInput> approveVideoLibraryInputMessage) {
        Map<String, Object> responseHeaders = new HashMap<>();
        Map<String, Object> payload = new HashMap<>();
        String stage = appConfig.getStage().toUpperCase();
        try {
            ApproveVideoLibraryInput approveVideoLibraryInput = approveVideoLibraryInputMessage.getPayload();
            log.info(String.format("Entering approve videolibrary Function -  video Id:%s", approveVideoLibraryInput.getVideoId()));
            Origin origin = HeaderUtil.buildOriginFromHeaders(approveVideoLibraryInputMessage.getHeaders());
            ApproveCommand approveCommand = InputOutputMapper.buildApproveCommandFromApproveVideoLibraryInput.apply(approveVideoLibraryInput, origin);
            videoLibraryService.approve(approveCommand);
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
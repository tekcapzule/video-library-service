package com.tekcapzule.videolibrary.domain.service;

import com.tekcapzule.videolibrary.domain.command.ApproveCommand;
import com.tekcapzule.videolibrary.domain.command.CreateCommand;
import com.tekcapzule.videolibrary.domain.command.RecommendCommand;
import com.tekcapzule.videolibrary.domain.command.UpdateCommand;
import com.tekcapzule.videolibrary.domain.model.Video;

import java.util.List;


public interface VideoLibraryService {

    void create(CreateCommand createCommand);

    void update(UpdateCommand updateCommand);

    List<Video> findAll();

    List<Video> findAllByTopicCode(String code);
    void recommend(RecommendCommand recommendCommand);
    void approve(ApproveCommand approveCommand);
}

package com.tekcapsule.videolibrary.domain.service;

import com.tekcapsule.videolibrary.domain.command.CreateCommand;
import com.tekcapsule.videolibrary.domain.command.UpdateCommand;
import com.tekcapsule.videolibrary.domain.model.Video;

import java.util.List;


public interface VideoLibraryService {

    void create(CreateCommand createCommand);

    void update(UpdateCommand updateCommand);

    List<Video> findAll();

    List<Video> findAllByTopicCode(String code);
}

package com.tekcapsule.videolibrary.domain.service;

import com.tekcapsule.videolibrary.domain.command.CreateCommand;
import com.tekcapsule.videolibrary.domain.command.UpdateCommand;
import com.tekcapsule.videolibrary.domain.model.Course;

import java.util.List;


public interface VideoLibraryService {

    void create(CreateCommand createCommand);

    void update(UpdateCommand updateCommand);

    List<Course> findAll();

    List<Course> findAllByTopicCode(String code);
}

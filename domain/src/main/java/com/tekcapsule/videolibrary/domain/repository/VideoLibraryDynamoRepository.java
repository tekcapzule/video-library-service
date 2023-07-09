package com.tekcapsule.videolibrary.domain.repository;

import com.tekcapsule.core.domain.CrudRepository;
import com.tekcapsule.videolibrary.domain.model.Video;

import java.util.List;

public interface VideoLibraryDynamoRepository extends CrudRepository<Video, String> {

    List<Video> findAllByTopicCode(String topicCode);
}

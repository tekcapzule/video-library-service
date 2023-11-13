package com.tekcapzule.videolibrary.domain.repository;

import com.tekcapzule.core.domain.CrudRepository;
import com.tekcapzule.videolibrary.domain.model.Video;

import java.util.List;

public interface VideoLibraryDynamoRepository extends CrudRepository<Video, String> {

    List<Video> findAllByTopicCode(String topicCode);
}

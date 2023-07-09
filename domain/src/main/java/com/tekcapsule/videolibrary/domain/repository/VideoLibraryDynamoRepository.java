package com.tekcapsule.videolibrary.domain.repository;

import com.tekcapsule.core.domain.CrudRepository;
import com.tekcapsule.videolibrary.domain.model.Course;

import java.util.List;

public interface VideoLibraryDynamoRepository extends CrudRepository<Course, String> {

    List<Course> findAllByTopicCode(String topicCode);
}

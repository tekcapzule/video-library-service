package com.tekcapsule.videolibrary.domain.service;

import com.tekcapsule.videolibrary.domain.command.CreateCommand;
import com.tekcapsule.videolibrary.domain.command.RecommendCommand;
import com.tekcapsule.videolibrary.domain.command.UpdateCommand;
import com.tekcapsule.videolibrary.domain.model.Video;
import com.tekcapsule.videolibrary.domain.model.Status;
import com.tekcapsule.videolibrary.domain.repository.VideoLibraryDynamoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class VideoLibraryServiceImpl implements VideoLibraryService {
    private VideoLibraryDynamoRepository videoLibraryDynamoRepository;

    @Autowired
    public VideoLibraryServiceImpl(VideoLibraryDynamoRepository videoLibraryDynamoRepository) {
        this.videoLibraryDynamoRepository = videoLibraryDynamoRepository;
    }

    @Override
    public void create(CreateCommand createCommand) {

        log.info(String.format("Entering create video service - Module Code :%s", createCommand.getTopicCode()));

        Video video = Video.builder()
                .title(createCommand.getTitle())
                .topicCode(createCommand.getTopicCode())
                .author(createCommand.getAuthor())
                .publisher(createCommand.getPublisher())
                .duration(createCommand.getDuration())
                .resourceUrl(createCommand.getResourceUrl())
                .summary(createCommand.getSummary())
                .description(createCommand.getDescription())
                .imageUrl(createCommand.getImageUrl())
                .promotion(createCommand.getPromotion())
                .status(Status.ACTIVE)
                .recommendations(createCommand.getRecommendations())
                .publishedOn(createCommand.getPublishedOn())
                .build();

        video.setAddedOn(createCommand.getExecOn());
        video.setAddedBy(createCommand.getExecBy().getUserId());

        videoLibraryDynamoRepository.save(video);
    }

    @Override
    public void update(UpdateCommand updateCommand) {

        log.info(String.format("Entering update video service - Video ID:%s", updateCommand.getVideoId()));

        Video video = videoLibraryDynamoRepository.findBy(updateCommand.getVideoId());
        if (video != null) {
            video.setTitle(updateCommand.getTitle());
            video.setTopicCode(updateCommand.getTopicCode());
            video.setAuthor(updateCommand.getAuthor());
            video.setPublisher(updateCommand.getPublisher());
            video.setDuration(updateCommand.getDuration());
            video.setResourceUrl(updateCommand.getResourceUrl());
            video.setSummary(updateCommand.getSummary());
            video.setDescription(updateCommand.getDescription());
            video.setPromotion(updateCommand.getPromotion());
            video.setImageUrl(updateCommand.getImageUrl());
            video.setUpdatedOn(updateCommand.getExecOn());
            video.setUpdatedBy(updateCommand.getExecBy().getUserId());
            video.setRecommendations(updateCommand.getRecommendations());
            video.setPublishedOn(updateCommand.getPublishedOn());
            videoLibraryDynamoRepository.save(video);
        }
    }

    @Override
    public void recommend(RecommendCommand recommendCommand) {
        log.info(String.format("Entering recommend videolibrary service -  videolibrary Id:%s", recommendCommand.getVideoId()));

        Video video = videoLibraryDynamoRepository.findBy(recommendCommand.getVideoId());
        if (video != null) {
            Integer recommendationsCount = video.getRecommendations();
            recommendationsCount += 1;
            video.setRecommendations(recommendationsCount);

            video.setUpdatedOn(recommendCommand.getExecOn());
            video.setUpdatedBy(recommendCommand.getExecBy().getUserId());

            videoLibraryDynamoRepository.save(video);
        }
    }

    @Override
    public List<Video> findAll() {

        log.info("Entering findAll Video service");

        return videoLibraryDynamoRepository.findAll();
    }

    @Override
    public List<Video> findAllByTopicCode(String topicCode) {

        log.info(String.format("Entering findAllByTopicCode Video service - Module code:%s", topicCode));

        return videoLibraryDynamoRepository.findAllByTopicCode(topicCode);
    }


}

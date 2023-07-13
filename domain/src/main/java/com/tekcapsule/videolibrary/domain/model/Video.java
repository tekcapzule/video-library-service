package com.tekcapsule.videolibrary.domain.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapsule.core.domain.AggregateRoot;
import com.tekcapsule.core.domain.BaseDomainEntity;
import lombok.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
@DynamoDBTable(tableName = "Video")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Video extends BaseDomainEntity implements AggregateRoot {
    @DynamoDBHashKey(attributeName="videoId")
    @DynamoDBAutoGeneratedKey
    private String videoId;
    @DynamoDBAttribute(attributeName = "topicCode")
    private String topicCode;
    @DynamoDBAttribute(attributeName = "title")
    private String title;
    @DynamoDBAttribute(attributeName = "summary")
    private String summary;
    @DynamoDBAttribute(attributeName = "description")
    private String description;
    @DynamoDBAttribute(attributeName = "author")
    private String author;
    @DynamoDBAttribute(attributeName = "publisher")
    private String publisher;
    @DynamoDBAttribute(attributeName = "duration")
    private String duration;
    @DynamoDBAttribute(attributeName = "resourceUrl")
    private String resourceUrl;
    @DynamoDBAttribute(attributeName = "imageUrl")
    private String imageUrl;
    @DynamoDBAttribute(attributeName = "promotion")
    private Promotion promotion;
    @DynamoDBAttribute(attributeName = "status")
    @DynamoDBTypeConvertedEnum
    private Status status;
}


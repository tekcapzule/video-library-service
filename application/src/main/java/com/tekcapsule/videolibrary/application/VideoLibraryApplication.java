package com.tekcapsule.videolibrary.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.tekcapsule.videolibrary","com.tekcapsule.core"})
public class VideoLibraryApplication {
    public static void main(String[] args) {
        SpringApplication.run(VideoLibraryApplication.class, args);
    }
}

package com.tekcapzule.videolibrary.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.tekcapzule.videolibrary","com.tekcapzule.core"})
public class VideoLibraryApplication {
    public static void main(String[] args) {
        SpringApplication.run(VideoLibraryApplication.class, args);
    }
}

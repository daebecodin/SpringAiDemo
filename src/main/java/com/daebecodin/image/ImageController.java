package com.daebecodin.image;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(value = "get-image")
    public List<String> getImageResponse(@RequestParam String imageRequest,
                                         @RequestParam() String quality,
                                         @RequestParam() Integer n,
                                         @RequestParam() Integer height,
                                         @RequestParam() Integer width) {
        // passing in query values to thr service method
        ImageResponse imageResponse =  imageService.getImageResponse(
                imageRequest,
                quality, n,
                height,
                width
        );

        List<String> results = imageResponse.getResults().stream() //retrieve results
                .map(result -> result.getOutput().getUrl()) // map results -> for each result get output and url
                .toList(); // add all results to a list

        return results;
    }
}

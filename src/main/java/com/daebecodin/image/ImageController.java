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
    public List<String> getImageResponse(@RequestParam String imageRequest) {
        ImageResponse imageResponse =  imageService.getImageResponse(imageRequest);

        List<String> results = imageResponse.getResults().stream()
                .map(result -> result.getOutput().getUrl())
                .toList();

        return results;
    }
}

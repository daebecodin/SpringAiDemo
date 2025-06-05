package com.daebecodin.image;

import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    private final OpenAiImageModel openAiImageModel;
    private final ImageModel imageModel;

    public ImageService(ImageModel imageModel, OpenAiImageModel openAiImageModel) {
        this.imageModel = imageModel;
        this.openAiImageModel = openAiImageModel;
    }


    /**
     *
     * @param imageRequest out image request prompt
     * @param quality quality of image
     * @param n number of images
     * @param height image height
     * @param width image width
     * @return temp url of image
     */
    public ImageResponse getImageResponse(String imageRequest,
                                          String quality,
                                          Integer n,
                                          Integer height,
                                          Integer width) {
         ImageResponse response = openAiImageModel.call( // our response is the models call function provided with our image options
                 new ImagePrompt( // class representation of our prompt
                         imageRequest,
                         OpenAiImageOptions.builder()
//                                 .model("dall-e-2") // model
                                 .quality(quality) // image quality
                                 .N(n) // generated amount
                                 .height(height) // height
                                 .width(width) // width
                                 // many more options
                                 // try them out
                                 .build()
                 )
        );
        return response;
    }


}

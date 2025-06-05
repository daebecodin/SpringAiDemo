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
     * @param imageRequest is the request image the user queries
     * @return we return a temp url leading us to the image received
     */
    public ImageResponse getImageResponse(String imageRequest) {
         ImageResponse response = openAiImageModel.call(
                 new ImagePrompt(
                         imageRequest,
                         OpenAiImageOptions.builder()
//                                 .model("dall-e-2") // model
                                 .quality("hd") // image quality
                                 .N(1) // generated amount
                                 .height(1024) // height
                                 .width(1024) // width
                                 // many more options
                                 // try them out
                                 .build()
                 )
        );
        return response;
    }


}

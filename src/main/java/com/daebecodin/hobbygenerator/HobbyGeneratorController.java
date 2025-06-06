package com.daebecodin.hobbygenerator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HobbyGeneratorController {

    private final HobbyGeneratorService hobbyGeneratorService;

    public HobbyGeneratorController(HobbyGeneratorService hobbyGeneratorService) {
        this.hobbyGeneratorService = hobbyGeneratorService;
    }

    @GetMapping(value = "hobby-list")
    public String hobbyList(@RequestParam String age,
                            @RequestParam String location,
                            @RequestParam String dailyFreeTime,
                            @RequestParam String budget,
                            @RequestParam String physicalNotes,
                            @RequestParam String personality,
                            @RequestParam String preferences,
                            @RequestParam String currentInterests,
                            @RequestParam String goals,
                            @RequestParam String session
                            ) {

        return hobbyGeneratorService.generateHobbies(age,location,dailyFreeTime,budget,physicalNotes,personality,preferences,currentInterests,goals, session);
    }
}

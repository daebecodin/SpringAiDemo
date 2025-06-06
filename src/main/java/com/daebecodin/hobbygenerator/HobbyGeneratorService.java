package com.daebecodin.hobbygenerator;


import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class HobbyGeneratorService {

    /**
     * Instance of ChatModel
     */
    private final ChatModel chatModel;

    public HobbyGeneratorService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String generateHobbies( String age,
                                   String location,
                                   String dailyFreeTime,
                                   String budget,
                                   String physicalNotes,
                                   String personality,
                                   String preferences,
                                   String currentInterests,
                                   String goals,
                                   String session
    ) {
        String template = """
                You are an expert assistant. Take a deep breath and think step by step.
                
                                                                     Your task is to curate a list of hobbies and activities tailored to a specific user. Use all of the following user attributes when making your recommendations:
                
                                                                       • Age: {age} \s
                                                                       • Location: {location} \s
                                                                       • Daily Free Time (hours/day): {dailyFreeTime} \s
                                                                       • Budget (monthly or per‐activity): {budget} \s
                                                                       • Physical Notes (any limitations or skills): {physicalNotes} \s
                                                                       • Personality Traits (e.g., introvert/extrovert, creative, analytical): {personality} \s
                                                                       • Current Interests (topics, subjects, or passions): {currentInterest} \s
                                                                       • Goals (short-term and long-term objectives): {goals} \s
                                                                       • Session Context (any recent events, mood, or constraints): {session} \s
                
                                                                     **Instructions:** \s
                                                                     1. **Summarize Key Attributes** \s
                                                                        - Briefly restate (in one paragraph) the most important details about the user, based on the attributes above. \s
                
                                                                     2. **Define Evaluation Criteria** \s
                                                                        - Clearly list the criteria you will use to judge each hobby or activity. For example: \s
                                                                            • Alignment with age and skill level \s
                                                                            • Suitability for the user’s location (accessibility, climate, culture) \s
                                                                            • Time commitment vs. daily free time \s
                                                                            • Cost vs. budget constraints \s
                                                                            • Physical requirements (consider any limitations) \s
                                                                            • Personality fit (e.g., solo vs. group, creative vs. analytical) \s
                                                                            • Relevance to current interests and long‐term goals \s
                                                                            • Relevance to session context (e.g., stress relief, social connection, skill building) \s
                
                                                                     3. **Categorize Hobbies & Activities** \s
                                                                        Divide your recommendations into at least four broad categories (adjust as needed): \s
                                                                        - **Physical / Sports / Outdoors** (e.g., jogging, rock climbing, dance classes) \s
                                                                        - **Creative / Artistic / Crafting** (e.g., painting, DIY woodworking, creative writing) \s
                                                                        - **Social / Community / Group Activities** (e.g., local club meetups, volunteering, board‐game nights) \s
                                                                        - **Educational / Skill-Building / Intellectual** (e.g., language learning, coding projects, book clubs) \s
                
                                                                     4. **For Each Recommended Activity, Provide:** \s
                                                                        a. **Name of the Activity** \s
                                                                        b. **Brief Description** (1–2 sentences) \s
                                                                        c. **Why It Fits** (2–3 sentences explaining how it aligns with the user’s attributes, using the evaluation criteria above) \s
                                                                        d. **Estimated Weekly Time Commitment** (e.g., “3–5 hours per week”) \s
                                                                        e. **Estimated Cost** (e.g., free, low (≈ \\$10–\\$20/month), moderate (≈ \\$50–\\$100/month), any equipment needed) \s
                                                                        f. **Physical Considerations** (e.g., “requires standing and light cardio—suitable if no major joint issues”) \s
                                                                        g. **Location Notes** (e.g., “if you live in a small apartment, consider online classes” or “many local parks have free drop-in sessions”) \s
                
                                                                     5. **Prioritize & Rank** \s
                                                                        - Rank the activities in each category from most to least recommended. \s
                                                                        - At the end of each category, include a short bullet list of “Honorable Mentions” (1–2 extra ideas that might also work). \s
                
                                                                     6. **Provide a Final “Top 3 Overall” Summary** \s
                                                                        - After all categories, choose the top three activities across all categories that best match the user’s profile. \s
                                                                        - For each of these, write one concise sentence explaining why it’s the overall best fit. \s
                
                                                                     7. **Step-by-Step Reasoning** \s
                                                                        - Whenever you make a judgment call (e.g., “I choose rock climbing over jogging because …”), label it as **“Reasoning”** in parentheses or italics. This makes your process transparent. \s
                
                                                                     8. **Formatting & Style** \s
                                                                        - Use clear headings (e.g., “### Physical / Sports / Outdoors”) and numbered sub-lists. \s
                                                                        - Keep paragraphs short (2–3 sentences max). \s
                                                                        - Write in an engaging yet concise tone (avoid robotic language). \s
                                                                        - Use bold or italics to highlight key points. \s
                
                                                                     ___ \s
                
                                                                     **Example Outline of Your Response:** \s
                
                                                                     1. **Summary of User Attributes** \s
                                                                        _(one paragraph summarizing age, location, free time, budget, physical notes, personality, interests, goals, session)_ \s
                
                                                                     2. **Evaluation Criteria** \s
                                                                        1. Alignment with age and skill level \s
                                                                        2. Suitability for location \s
                                                                        3. … etc. \s
                
                                                                     3. **Hobby Categories** \s
                
                                                                        ### a) Physical / Sports / Outdoors \s
                                                                        1. **Rock Climbing** \s
                                                                           - Description: … \s
                                                                           - Why it fits: … \s
                                                                           - Time Commitment: … \s
                                                                           - Cost: … \s
                                                                           - Physical Considerations: … \s
                                                                           - Location Notes: … \s
                                                                           *(Reasoning: “I selected rock climbing because the user’s local gym offers introductory classes within budget and they have 5 hours free weekly.”)* \s
                
                                                                        2. **Jogging in Local Park** \s
                                                                           - … \s
                                                                           *(…)*
                
                                                                        **Honorable Mentions:** \s
                                                                        - Hiking \s
                                                                        - Yoga (online or studio) \s
                
                                                                        ### b) Creative / Artistic / Crafting \s
                                                                        1. **…** \s
                                                                        … \s
                
                                                                        **Honorable Mentions:** \s
                                                                        - … \s
                
                                                                        ### c) Social / Community / Group Activities \s
                                                                        1. **…** \s
                                                                        … \s
                
                                                                        **Honorable Mentions:** \s
                                                                        - … \s
                
                                                                        ### d) Educational / Skill-Building / Intellectual \s
                                                                        1. **…** \s
                                                                        … \s
                
                                                                        **Honorable Mentions:** \s
                                                                        - … \s
                
                                                                     4. **Top 3 Overall Recommendations** \s
                                                                        1. **…** – one-sentence explanation \s
                                                                        2. **…** – one-sentence explanation \s
                                                                        3. **…** – one-sentence explanation \s
                
                                                                     5. **(Optional) Next Steps / Tips** \s
                                                                        - “Try a free trial class before committing.” \s
                                                                        - “Track your enjoyment and progress weekly.” \s
                
                                                                     By following this structure, you will generate a detailed, well‐organized set of hobby and activity recommendations that fully leverage all of the user’s provided information.
                """;

        PromptTemplate promptTemplate = new PromptTemplate(template);

        Map<String, Object> useeInfo = Map.of(
                "age", age,
                "location", location,
                "dailyFreeTime", dailyFreeTime,
                "budget", budget,
                "physicalNotes", physicalNotes,
                "personality", personality,
                "preferences", preferences,
                "currentInterest", currentInterests,
                "goals", goals,
                "session", session

        );

        Prompt prompt = promptTemplate.create(useeInfo);

        return chatModel.call(prompt).getResult().getOutput().getText();
    }


}

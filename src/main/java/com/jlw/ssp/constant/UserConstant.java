package com.jlw.ssp.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserConstant {

    @Value("${user.config.group.id}")
    public String GROUP_ID;

    @Value("${user.config.min.score}")
    public String MIN_SCORE;

    @Value("${user.config.sounds.resources}")
    public String SOUNDS_RESOURCES_PATH;

    @Value("${user.config.ffmpeg.path}")
    public String FFMPEG_PATH;

    @Value("${user.config.app.id}")
    public String APP_ID;

    @Value("${user.config.api.secret}")
    public String API_SECRET;

    @Value("${user.config.api.key}")
    public String API_KEY;
}

package com.jlw.ssp.utils;

import com.jlw.ssp.enums.FileType;

import static com.jlw.ssp.constant.GlobalConstant.*;
import static com.jlw.ssp.enums.FileType.*;

public class CommandUtils {

    public static void convertFile(String ffmpegPath, int originType, int convertType, String filePath) throws Exception {
        FileType originFileType = FileType.getByType(originType);
        FileType convertFileType = FileType.getByConvertType(convertType);
        String newFilePath = "";
        String command = "";
        switch (originFileType) {
            case PCM:
                switch (convertFileType) {
                    case MP3:
                        newFilePath = filePath.replace(PCM.getSuffix(), MP3.getSuffix());
                        command = String.format(ffmpegPath + FFMPEG_PCM_TO_MP3, filePath, newFilePath);
                        break;
                    case WEBM:
                        throw new RuntimeException("No support pcm to webm");
                }
                break;
            case MP3:
                switch (convertFileType) {
                    case PCM:
                        newFilePath = filePath.replace(MP3.getSuffix(), PCM.getSuffix());
                        command = String.format(ffmpegPath + FFMPEG_MP3_TO_PCM, filePath, newFilePath);
                        break;
                    case WEBM:
                        throw new RuntimeException("No support mp3 to webm");
                }
                break;
            case WEBM:
                switch (convertFileType) {
                    case MP3:
                        newFilePath = filePath.replace(WEBM.getSuffix(), MP3.getSuffix());
                        command = String.format(ffmpegPath + FFMPEG_WEBM_TO_MP3, filePath, newFilePath);
                        break;
                    case PCM:
                        newFilePath = filePath.replace(WEBM.getSuffix(), PCM.getSuffix());
                        command = String.format(ffmpegPath + FFMPEG_WEBM_TO_PCM, filePath, newFilePath);
                }
                break;
        }
        System.out.println("command: " + command);
        Process process = Runtime.getRuntime().exec(command);
        int status = process.waitFor();
        if (status != 0) {
            throw new RuntimeException("status: " + status);
        }
    }
}

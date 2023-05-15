package com.jlw.ssp.enums;

public enum FileType {

    MP3(0, ".mp3", 0),
    PCM(1, ".pcm", 1),
    WEBM(2, ".webm", 2);

    int type;
    String suffix;
    int convertFlag;

    public static FileType getByConvertType(int convertType) {
        for (FileType fileType : FileType.values()) {
            if (fileType.convertFlag == convertType) {
                return fileType;
            }
        }
        return null;
    }

    public static FileType getByType(int type) {
        for (FileType fileType : FileType.values()) {
            if (fileType.convertFlag == type) {
                return fileType;
            }
        }
        return null;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    FileType() {
    }

    public int getConvertFlag() {
        return convertFlag;
    }

    public void setConvertFlag(int convertFlag) {
        this.convertFlag = convertFlag;
    }

    FileType(int type, String suffix, int convertFlag) {
        this.type = type;
        this.suffix = suffix;
        this.convertFlag = convertFlag;
    }
}

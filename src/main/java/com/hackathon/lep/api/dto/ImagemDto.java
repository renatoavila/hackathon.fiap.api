package com.hackathon.lep.api.dto;

import lombok.Getter; 
import lombok.Setter;

@Getter
@Setter
public class ImagemDto {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;

    public ImagemDto(String fileName, String fileDownloadUri, String fileType, long size) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }

	// Getters and Setters (Omitted for brevity)
}

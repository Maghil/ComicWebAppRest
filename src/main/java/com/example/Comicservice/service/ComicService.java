package com.example.Comicservice.service;

import com.google.api.services.drive.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComicService {

    private final GoogleDriveService googleDriveService;

    @Autowired
    public ComicService(GoogleDriveService googleDriveService) {
        this.googleDriveService = googleDriveService;
    }

    public String getComic() {
        List<File> files = googleDriveService.getDriveFiles();
        File file = files.get(0);
        return file.getId();
    }

    public String getNextComic(String fileId) {
        List<File> files = googleDriveService.getDriveFiles();
        for (File file : files) {
            String fileId1 = file.getId();
            if (fileId == fileId) {
                Integer index = files.indexOf(file);
                Integer size = files.size();
                if (index < size - 1) {
                    return (files.get(index + 1).getId());
                }
                return null;
            }
        }
        return null;
    }

    public String getPrevioussComic(String fileId) {
        List<File> files = googleDriveService.getDriveFiles();
        for (File file : files) {
            String fileId1 = file.getId();
            if (fileId == fileId) {
                Integer index = files.indexOf(file);
                if (index > 1) {
                    return (files.get(index - 1).getId());
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    public void serveRandomComic() {
        List<File> files = googleDriveService.getDriveFiles();
    }
}

package com.example.Comicservice.service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Service
public class GoogleDriveService {

    private static final String APPLICATION_NAME = "comic website";

    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    private final CredentialService credentialService;

    @Autowired
    public GoogleDriveService(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    public List<File> getDriveFiles() {
        try {
            final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credentialService.getCredentials(HTTP_TRANSPORT))
                    .setApplicationName(APPLICATION_NAME)
                    .build();

            FileList result = service.files().list()
                    .setPageSize(20)
                    .setFields("nextPageToken, files(id, name)")
                    .setQ("parents in '1Qtga8ZDkj68DTCTWDQhNVReuWQFXWiv3'")
                    .setOrderBy("createdTime")
                    .setPageSize(1000)
                    .execute();
            List<File> files = result.getFiles();
            if (files == null || files.isEmpty()) {
                System.out.println("No files found.");
                return null;
            } else {
                for (File file : files) {
                    System.out.printf("%s (%s)\n", file.getName(), file.getId());
                }
                return files;
            }
        }
        catch  (IOException | GeneralSecurityException exception){
            throw new RuntimeException(exception);
        }
    }
}

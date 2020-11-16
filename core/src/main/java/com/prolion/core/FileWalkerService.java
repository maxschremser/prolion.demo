package com.prolion.core;


import org.springframework.stereotype.Service;

@Service
public class FileWalkerService {
    public FileWalkerService() {
    }

    public String[] getFolders() {
        return new String[]{"maxi", "hugo", "test"};
    }

    public String[] getFilesizes() {
        return new String[]{"1MB", "10MB", "100MB"};
    }
}

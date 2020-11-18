package com.prolion.core.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.File;
import java.nio.file.Path;
import java.sql.Timestamp;

@Entity
public class FileWalker {
    @Id
    @GeneratedValue
    private Long id;
    private String path;
    private boolean directory;
    private String name;
    private String type;
    private Long size;
    private Timestamp modified;
    private Timestamp scanned;

    public FileWalker() {
    }

    public FileWalker(Path path, Timestamp scanned) {
        this.path = path.toString();
        File file = path.toFile();
        String filename = file.getName();
        this.name = filename;
        this.directory = file.isDirectory();
        int index = filename.lastIndexOf('.');
        if (!directory && index >= 0) {
            this.type = filename.substring(filename.lastIndexOf(".") + 1);
        }
        this.type = filename.substring(filename.lastIndexOf("."));
        this.size = file.length();
        this.modified = new Timestamp(file.lastModified());
        this.scanned = scanned;
    }

    public Long getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public boolean isDirectory() {
        return directory;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Long getSize() {
        return size;
    }

    public Timestamp getModified() {
        return modified;
    }

    public Timestamp getScanned() {
        return scanned;
    }
}

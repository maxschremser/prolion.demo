package com.prolion.core;


import com.prolion.core.config.FileWalkerConfigurationProperties;
import com.prolion.core.dao.FileWalkerRepository;
import com.prolion.core.model.FileWalker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Service
@EnableScheduling
@EnableJpaRepositories
public class FileWalkerService {
    private final Logger log = LoggerFactory.getLogger(FileWalkerService.class);
    private final FileWalkerConfigurationProperties configuration;
    private final Path path;
    private final FileWalkerRepository repository;

    public FileWalkerService(FileWalkerConfigurationProperties configuration, FileWalkerRepository repository) throws FileNotFoundException {
        this.configuration = configuration;
        this.repository = repository;
        this.path = Paths.get(this.configuration.getDir());
        if (!this.path.toFile().exists()) {
            throw new FileNotFoundException("prolion.service.dir (" + this.configuration.getDir() + ") does not exist.");
        }
        log.info("Starting FileWalkerService");
        log.info("Scanning Directory: {}", this.path.toString());
        log.info("Scanning Interval: {}s", this.configuration.getInterval().toString().substring(2));
    }

    @Scheduled(fixedRateString = "#{@fileWalkerConfigurationProperties.interval.toMillis()}")
    private void scanDirectory() throws IOException {
        log.debug("Scanning Directory: {}", this.configuration.getDir());
        Timestamp ts = new Timestamp(new Date().getTime());
        Files.walk(path, configuration.getMaxDepth(), FileVisitOption.FOLLOW_LINKS).forEach(entry -> {
            repository.save(new FileWalker(entry, ts));
        });
    }

    public Stream<FileWalker> getFolders() {
        List<FileWalker> folders = new ArrayList<>();
        repository.findAll().forEach(folders::add);
        return folders.stream().filter(FileWalker::isDirectory);
    }

    public String[] getFilesizes() {
        return new String[]{"1MB", "10MB", "100MB"};
    }
}

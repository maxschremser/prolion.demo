package com.prolion.rest.api;

import com.prolion.core.FileWalkerService;
import com.prolion.core.model.FileWalker;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.stream.Stream;

@Component
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_HTML})
@Produces(MediaType.APPLICATION_JSON)
@Path("/")
public class FileResource {
    private final FileWalkerService service;

    public FileResource(FileWalkerService service) {
        this.service = service;
    }

    @GET
    @Path("/folders")
    public Stream<FileWalker> getFolders() {
        return service.getFolders();
    }

    @GET
    @Path("/filesizes")
    public String[] getFilesizes() {
        return service.getFilesizes();
    }
}

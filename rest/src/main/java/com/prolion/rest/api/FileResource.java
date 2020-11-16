package com.prolion.rest.api;

import com.prolion.core.FileWalkerService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/api")
public class FileResource {
    private FileWalkerService service = new FileWalkerService();

    public FileResource() {
    }

    public FileResource(FileWalkerService service) {
        this.service = service;
    }

    @GET
    @Path("/folders")
    public String[] getFolders() {
        return service.getFolders();
    }

    @GET
    @Path("/filesizes")
    public String[] getFilesizes() {
        return service.getFilesizes();
    }
}

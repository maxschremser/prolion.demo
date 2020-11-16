package core;

import com.prolion.core.FileWalkerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

@Testable
public class TestFileWalkerService {
    private FileWalkerService service;

    public TestFileWalkerService() {
    }

    @BeforeEach
    void setUp() {
        this.service = new FileWalkerService();
    }

    @Test
    public void testGetFolders() {
        assert this.service.getFolders().length == 3;
    }

    @Test
    public void testGetFilesizes() {
        assert this.service.getFilesizes().length == 3;
    }
}

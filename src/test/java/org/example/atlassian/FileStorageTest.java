package org.example.atlassian;

import org.example.atlassian.FileStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileStorageTest {

    private FileStorage obj;

    @BeforeEach
    public void setup() {
        obj = new FileStorage();
    }

    @Test
    public void addFilesAndCheckTotalSpace() {
        var file1 = new File("file1", 1000L);
        var file2 = new File("file2", 2000L);
        var file3 = new File("file3", 3000L);
        obj.addFile(file1, "collection1");
        obj.addFile(file2, "collection2");
        obj.addFile(file3, "collection3");
        assertEquals(6000L, obj.getBytesOfAllFilesStored());
    }

    @Test
    public void searchForTop3Collections() {
        var file1 = new File("file1", 1000L);
        var file2 = new File("file2", 2000L);
        var file3 = new File("file3", 3000L);
        var file4 = new File("file4", 4000L);
        var file5 = new File("file5", 5000L);
        var file6 = new File("file6", 6000L);
        var file7 = new File("file7", 7000L);
        var file8 = new File("file8", 8000L);
        var file9 = new File("file9", 9000L);
        var file10 = new File("file10", 10000L);
        obj.addFile(file1, "collection1");
        obj.addFile(file2, "collection1");
        obj.addFile(file3, "collection2");
        obj.addFile(file4, "collection2");
        obj.addFile(file5, "collection3");
        obj.addFile(file6, "collection3");
        obj.addFile(file7, "collection4");
        obj.addFile(file8, "collection4");
        obj.addFile(file9, "collection5");
        obj.addFile(file10, "collection5");
        assertEquals(List.of("collection5", "collection4"), obj.getTopNCollectionsBySize(2));
    }
}

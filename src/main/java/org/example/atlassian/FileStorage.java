package org.example.atlassian;

import java.util.*;
import java.util.stream.Collectors;

public class FileStorage {

    private Map<String, ListOfFilesAndCollectionMetadata> filetoCollectionMap;

    public FileStorage() {
        filetoCollectionMap = new HashMap<>();
    }

    private Long sizeOfTotalFiles = 0L;

    public void addFile(File file, String collectionName) {
        if ((collectionName!=null || collectionName.length()==0)) {
            sizeOfTotalFiles += file.getFileSize();
            return;
        }
        if (!filetoCollectionMap.containsKey(collectionName)) {
            filetoCollectionMap.put(collectionName, new ListOfFilesAndCollectionMetadata(collectionName));
        }
        var listOfFilesAndCollectionMetaData = filetoCollectionMap.get(collectionName);
        listOfFilesAndCollectionMetaData.addFile(file);
        sizeOfTotalFiles += file.getFileSize();
    }

    public Long getBytesOfAllFilesStored() {
        return sizeOfTotalFiles;
    }

    public List<String> getTopNCollectionsBySize(int N) {
        if (N > filetoCollectionMap.size()) {
            throw new IllegalStateException("N is greater than collections in report");
        }
        List<ListOfFilesAndCollectionMetadata> filesAndCollectionMetadataSet = new ArrayList<>(filetoCollectionMap.values());
        Collections.sort(filesAndCollectionMetadataSet, new Comparator<ListOfFilesAndCollectionMetadata>() {
            @Override
            public int compare(ListOfFilesAndCollectionMetadata o1, ListOfFilesAndCollectionMetadata o2) {
                return Long.compare(o2.getTotalSizeOfCollection(), o1.getTotalSizeOfCollection());
            }
        });
        return filesAndCollectionMetadataSet.subList(0, N).stream().map(ListOfFilesAndCollectionMetadata::getCollectionName).collect(Collectors.toList());
    }
}

class File {

    private String fileName;
    private Long fileSize;

    public File(String fileName, Long fileSize) {
        this.fileName = fileName;
        this.fileSize = fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return fileName.equals(file.fileName) && fileSize.equals(file.fileSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, fileSize);
    }
}

class ListOfFilesAndCollectionMetadata {

    private List<File> files;
    private Long totalSizeOfCollection;

    private String collectionName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListOfFilesAndCollectionMetadata that = (ListOfFilesAndCollectionMetadata) o;
        return Objects.equals(files, that.files) && Objects.equals(totalSizeOfCollection, that.totalSizeOfCollection) && Objects.equals(collectionName, that.collectionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(files, totalSizeOfCollection, collectionName);
    }

    public void addFile(File file) {
        if (files!=null) {
            files.add(file);
            totalSizeOfCollection += file.getFileSize();
        }
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public void setTotalSizeOfCollection(Long totalSizeOfCollection) {
        this.totalSizeOfCollection = totalSizeOfCollection;
    }

    public ListOfFilesAndCollectionMetadata(String collectionName) {
        this.files = new ArrayList<>();
        this.totalSizeOfCollection = 0L;
        this.collectionName = collectionName;
    }

    public List<File> getFiles() {
        return files;
    }

    public Long getTotalSizeOfCollection() {
        return totalSizeOfCollection;
    }
}

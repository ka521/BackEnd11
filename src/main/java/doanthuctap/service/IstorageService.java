package doanthuctap.service;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface IstorageService {
    public String storeFile(MultipartFile file);

    public Stream<Path> loadAll(); //load all file inside a folder

    public byte[] readFileContent(String fileName);

    public void deleteAllFile();

}

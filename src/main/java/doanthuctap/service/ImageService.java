package doanthuctap.service;

import doanthuctap.response.ImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    public void save(MultipartFile img, Integer employee_id) throws IOException;

    public ImageResponse getImg(Integer id);
}

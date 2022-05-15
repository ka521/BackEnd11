package doanthuctap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import doanthuctap.common.MessageResponse;
import doanthuctap.response.ImageResponse;
import doanthuctap.service.ImageService;

@RestController
@RequestMapping("/api/image")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @GetMapping(value = "/{employee_id}")
    public ResponseEntity<?> getImg(@PathVariable Integer employee_id) {
        try {
            ImageResponse imageResponse = imageService.getImg(employee_id);
            return ResponseEntity.ok().header("Content-Type", imageResponse.getContextType()).body(imageResponse.getData());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message(e.getMessage()).build());
        }
    }

    @PostMapping("/upload/{employee_id}")
    public ResponseEntity<?> upload(@RequestBody MultipartFile img, @PathVariable Integer employee_id) {
        String message = "";
        try {
            imageService.save(img, employee_id);
            message = "Uploaded the Image successfully: " + img.getOriginalFilename();
            return ResponseEntity.ok().body(message);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}

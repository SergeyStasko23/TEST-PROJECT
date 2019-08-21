package ru.stacy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.stacy.dto.converters.ImageConverter;
import ru.stacy.entities.Image;
import ru.stacy.exceptions.NoImageException;
import ru.stacy.services.UploaderService;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/api")
public class UploaderController {
    private final UploaderService uploaderService;

    @Autowired
    public UploaderController(UploaderService uploaderService) {
        this.uploaderService = uploaderService;
    }

    @PostMapping("/uploader/directory")
    public ResponseEntity<String> uploadImageToDirectory(@RequestParam("file") MultipartFile file) {
        Image image = ImageConverter.toEntity(file);
        String response = uploaderService.uploadImageToDirectory(image);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/uploader/database")
    public ResponseEntity<?> uploadImageToDatabase(@RequestParam("file") MultipartFile file) {
        Image image = uploaderService.uploadImageToDatabase(ImageConverter.toEntity(file));

        return ResponseEntity.status(HttpStatus.CREATED)
                .header("fileName", image.getOriginalFilename())
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }

    @GetMapping("/images/{id}")
    public ResponseEntity<?> getImageById(@PathVariable Long id) {
        try {
            Image image = uploaderService.getImageById(id);

            if (image != null) {
                return ResponseEntity.ok()
                        .header("fileName", image.getOriginalFilename())
                        .contentType(MediaType.valueOf(image.getContentType()))
                        .contentLength(image.getSize())
                        .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
            } else {
                throw new NoImageException("Image №" + id + " not found");
            }
        } catch(NoImageException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

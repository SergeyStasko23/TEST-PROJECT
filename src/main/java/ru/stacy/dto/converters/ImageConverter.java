package ru.stacy.dto.converters;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.stacy.entities.Image;

import java.io.IOException;

@Service
public class ImageConverter {
    public static Image toEntity(MultipartFile file) {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFilename(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());

        try {
            image.setBytes(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }
}

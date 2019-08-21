package ru.stacy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stacy.entities.Image;
import ru.stacy.exceptions.NoImageException;
import ru.stacy.repositories.UploaderRepository;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Service
public class UploaderService {
    private static final String PATH_TO_FILE = "src"+File.separator+"main"+File.separator+"resources"+File.separator+"files"+File.separator;

    private final UploaderRepository uploaderRepository;

    @Autowired
    public UploaderService(UploaderRepository uploaderRepository) {
        this.uploaderRepository = uploaderRepository;
    }

    public String uploadImageToDirectory(Image image) {
        if(image != null) {
            try {
                byte[] bytes = image.getBytes();
                String url = PATH_TO_FILE + image.getOriginalFilename();

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(url));

                stream.write(bytes);
                stream.close();

                return url;
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return "Upload failed";
    }

    public Image uploadImageToDatabase(Image image) {
        return uploaderRepository.save(image);
    }

    public Image getImageById(Long id) throws NoImageException {
        return uploaderRepository.findImageById(id);
    }
}

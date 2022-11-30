package develhope.Flow_Open_Spring.controller;

import develhope.Flow_Open_Spring.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/file")
public class UploadController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public void upload(@RequestParam MultipartFile file, HttpServletResponse httpServletResponse) throws IOException, MissingServletRequestParameterException {
        httpServletResponse.setHeader("Content-Disposition", "attacchment; filename= diagramma ER");
        fileService.upload(file);
    }
}

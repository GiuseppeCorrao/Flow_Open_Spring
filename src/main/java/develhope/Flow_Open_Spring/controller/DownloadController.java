package develhope.Flow_Open_Spring.controller;

import develhope.Flow_Open_Spring.service.FileService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/file")
public class DownloadController {

    @Autowired
    private FileService fileService;

    /**
     * this is the function for download the image
     *
     * @author Emmanuele La Duca
     * @param fileName
     * @param httpServletResponse
     * @return
     * @throws IOException
     */
    @GetMapping("/download")
    public byte[] download(@RequestParam String fileName, HttpServletResponse httpServletResponse) throws IOException {
        String extension = FilenameUtils.getExtension(fileName);
        switch (extension) {
            case "gif":
                httpServletResponse.setContentType(MediaType.IMAGE_GIF_VALUE);
                break;
            case "jpeg":
            case "jpg":
                httpServletResponse.setContentType(MediaType.IMAGE_JPEG_VALUE);
                break;
            case "png":
                httpServletResponse.setContentType(MediaType.IMAGE_PNG_VALUE);
                break;
        }
        return fileService.download(fileName);
    }
}

package pub.wii.cook.springboot.controller;

import lombok.SneakyThrows;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;

@Controller
@RequestMapping("download")
public class DownloadController {
    @CrossOrigin(maxAge = 1, methods = {RequestMethod.POST})
    @RequestMapping(value = "file", method = RequestMethod.GET)
    @ResponseBody
    @SneakyThrows
    public ResponseEntity<String> file(HttpServletResponse response) {
        System.out.println("call check");
        InputStream is = new FileInputStream("/Users/wii/Data/tmp/001.js");
        IOUtils.copy(is, response.getOutputStream());
        response.flushBuffer();
        return ResponseEntity.ok("ok");
    }

    @CrossOrigin(maxAge = 1, methods = {RequestMethod.POST})
    @RequestMapping(value = "process/{file}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    @ResponseBody
    @SneakyThrows
    public void process(HttpServletResponse response, @PathVariable("file") String file) {
        InputStream is = new FileInputStream("/Users/wii/Data/tmp/" + file);
        IOUtils.copy(is, response.getOutputStream());
        response.setContentType("application/octet-stream");
        response.flushBuffer();
    }
}

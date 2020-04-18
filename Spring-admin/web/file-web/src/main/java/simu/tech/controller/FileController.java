package simu.tech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import simu.tech.entity.Result;
import simu.tech.feign.FileFeign;
import simu.tech.pojo.File;


@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileFeign fileFeign;

    @PostMapping("/uploadFiles/{id}/{name}")
    public Result uploadFiles( @RequestPart("file") MultipartFile[] file, @PathVariable("id") String id,@PathVariable("name") String name){
        return fileFeign.uploadFiles(file, id, name);
    }
}

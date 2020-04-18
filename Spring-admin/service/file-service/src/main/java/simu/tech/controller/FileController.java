package simu.tech.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import simu.tech.entity.Result;

@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping("/uploadFiles/{id}/{name}")
    public Result uploadFiles(@RequestParam("file") MultipartFile[] file, @PathVariable("name") String id,@PathVariable("id") String name){
        System.out.println(file);
        System.out.println(id);
        System.out.println(name);
        return new Result();
    }
}

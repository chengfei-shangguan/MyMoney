package simu.tech.feign;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import simu.tech.entity.Result;

@Component
@FeignClient(name = "file-service",configuration = FileFeign.MultipartSupportConfig.class)
public interface FileFeign {
    @PostMapping(value = "/file/uploadFiles/{id}/{name}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result uploadFiles(@RequestPart("file") MultipartFile[] file, @PathVariable("id") String id,@PathVariable("name") String name);

    class MultipartSupportConfig{
    @Bean
    public Encoder feignFormEncoder(){
        return new SpringFormEncoder();
    }
    }


}

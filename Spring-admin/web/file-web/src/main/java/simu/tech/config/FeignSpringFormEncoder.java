//package simu.tech.config;
//
//import feign.RequestTemplate;
//import feign.codec.EncodeException;
//import feign.codec.Encoder;
//import feign.form.ContentType;
//import feign.form.FormEncoder;
//import feign.form.MultipartFormContentProcessor;
//import feign.form.spring.SpringManyMultipartFilesWriter;
//import feign.form.spring.SpringSingleMultipartFileWriter;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.lang.reflect.Type;
//import java.util.Collections;
//import java.util.Map;
//
//public class FeignSpringFormEncoder extends FormEncoder {
//    public FeignSpringFormEncoder() {
//        this(new Default());
//    }
//
//    public FeignSpringFormEncoder(Encoder delegate) {
//        super(delegate);
//        MultipartFormContentProcessor processor = (MultipartFormContentProcessor)this.getContentProcessor(ContentType.MULTIPART);
//        processor.addWriter(new SpringSingleMultipartFileWriter());
//        processor.addWriter(new SpringManyMultipartFilesWriter());
//    }
//
//    public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
//        //注释掉原来的代码
////        if (!bodyType.equals(MultipartFile.class)) {
////            super.encode(object, bodyType, template);
////        } else {
////            MultipartFile file = (MultipartFile)object;
////            Map<String, Object> data = Collections.singletonMap(file.getName(), object);
////            super.encode(data, MAP_STRING_WILDCARD, template);
////        }
//
//        //修改为下面的代码
//        if (bodyType.equals(MultipartFile.class)) {
//            MultipartFile file = (MultipartFile) object;
//            Map<String, Object> data = Collections.singletonMap(file.getName(), object);
//            super.encode(data, MAP_STRING_WILDCARD, template);
//            return;
//        } else if (bodyType.equals(MultipartFile[].class)) {
//            MultipartFile[] file = (MultipartFile[]) object;
//            if(file != null) {
//                Map<String, Object> data = Collections.singletonMap("files", object);
//                super.encode(data, MAP_STRING_WILDCARD, template);
//                return;
//            }
//        }
//        super.encode(object, bodyType, template);
//    }
//}

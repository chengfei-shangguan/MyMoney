package simu.tech.pojo;

import org.springframework.web.multipart.MultipartFile;

public class File {
    private String id;
    private String name;
    private String size;
    private String path;
    private String dec;
    private MultipartFile[] files;

    public File() {
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDec() {
        return dec;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }

    public File(String id, String name, String size, String path, String dec, MultipartFile[] files) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.path = path;
        this.dec = dec;
        this.files = files;
    }
}

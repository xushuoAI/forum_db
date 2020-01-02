package org.dgut.community.util;

import org.apache.tomcat.util.codec.binary.Base64;
import org.dgut.community.entity.FourmArticle;
import org.dgut.community.entity.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class Util implements ApplicationListener<WebServerInitializedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Util.class);
    // public static final String getTime() {
    // Date d = new Date();
    // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    // return sdf.format(d);
    // }

    public static String upload(MultipartFile file, String userName, String name) {
        if (file.isEmpty()) {
            return "没有上传文件！";
        }

        String fileName = file.getOriginalFilename();
        String filePath = "/home/forum/" + userName + "/" + name;
        //String filePath = "D:/Workspace-STS4/springboot-community/src/main/resources/templates/" + userName + "/" + name;
        File dest = new File(filePath + fileName);
        if (!dest.exists()) {
            dest.mkdirs();
        }
        try {
            file.transferTo(dest);
            LOGGER.info("上传成功");
            return "上传成功";
        } catch (IOException e) {
            LOGGER.error(e.toString(), e);
        }
        return "上传失败！";
    }


    public static final String uploadBase64Image(String name, String base64Data) {
        //System.out.println(base64Data);
        /*base64格式
         * data:image/png;base64,xxx*/
        String dataPrix = "";
        String data = "";
        if (base64Data == null || "".equals(base64Data)) {
            return null;
        } else {
            String[] d = base64Data.split("base64,");
            if (d != null && d.length == 2) {
                dataPrix = d[0];
                data = d[1];
                // System.out.println("坎坎坷坷"+data);
            } else {
                return null;

            }
        }
        String suffix = "";
        if ("data:image/jpeg;".equalsIgnoreCase(dataPrix)) {//data:image/jpeg;base64,base64编码的jpeg图片数据
            suffix = ".jpg";
        } else if ("data:image/x-icon;".equalsIgnoreCase(dataPrix)) {//data:image/x-icon;base64,base64编码的icon图片数据
            suffix = ".ico";
        } else if ("data:image/gif;".equalsIgnoreCase(dataPrix)) {//data:image/gif;base64,base64编码的gif图片数据
            suffix = ".gif";
        } else if ("data:image/png;".equalsIgnoreCase(dataPrix)) {//data:image/png;base64,base64编码的png图片数据
            suffix = ".png";
        } else {
            return null;
        }
        String tempFileName = UUID.randomUUID().toString() + suffix;
        // File dest = new File("D:/Workspace-STS4/springboot-community/src/main/resources/templates/" + name);
        // if (!dest.exists()){
        // dest.mkdirs();
        // }
//        String imgFilePath = "/home/forum/" + name + tempFileName;//新生成的图片
        String imgFilePath = "D:/Workspace-STS4/springboot-community/src/main/resources/templates/" + name + tempFileName;//新生成的图片
        //Base64 decoder = new Base64();
        try {
            //Base64解码
            byte[] b = Base64.decodeBase64(data);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    //调整异常数据
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            String imgUrl = getUrl() + name + tempFileName;
            return imgUrl;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static final void setArticlePhotos(FourmArticle article) {
        article.getUser().setUserPassword(null);
        if (article.getArticlePhoto() != null) {
            String[] strings = article.getArticlePhoto().split(",");
            List<Image> photo = new ArrayList<>();
            for (String string : strings) {
                Image image = new Image();
                image.setImageUrl(string);
                photo.add(image);
            }
            article.setPhotos(photo);
        }
    }

    public static final void deleteFile(String path) {
        String[] splits = path.split(",");
//        String filePath = "/home/forum/";
        String filePath = "D:/Workspace-STS4/springboot-community/src/main/resources/templates/";
        for (String split : splits) {
            String sub = split.substring(split.lastIndexOf("/") + 1);
            File file = new File(filePath + sub);
            if (!"abc.jpg".equals(sub)) {
                if (file.exists()) {
                    file.delete();
                }
            }
        }
    }

    private static int serverPort;

    public static final String getUrl() {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "http://192.168.23.1/";
        // return "http://"+address.getHostAddress() + ":" + serverPort + "/";
//        return "http://111.229.39.131/";
    }

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        this.serverPort = event.getWebServer().getPort();
    }
}

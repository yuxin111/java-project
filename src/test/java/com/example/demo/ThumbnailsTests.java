package com.example.demo;

import com.example.demo.common.utils.ImageUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

/**
 * @Desc
 * @Return
 * @Author YuXin
 * @Date 2021/11/3
 **/
@SpringBootTest
public class ThumbnailsTests {
    @Test
    public void toTest() {
        try {
            ImageUtils.compressImg(new File("D:\\Source\\UploadImg\\fc0dae1b-98ed-49f1-9dde-14fbc0cee47a_微信图片_20210103000253.jpg"),0.8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void toTest1() throws IOException {
        File file = new File("D:\\Source\\UploadImg\\fc0dae1b-98ed-49f1-9dde-14fbc0cee47a_微信图片_20210103000253.jpg");
        System.out.println("fc0dae1b-98ed-49f1-9dde-14fbc0cee47a_微信图片_20210103000253.jpg".substring(0,"fc0dae1b-98ed-49f1-9dde-14fbc0cee47a_微信图片_20210103000253.jpg".lastIndexOf(".")));
    }
}

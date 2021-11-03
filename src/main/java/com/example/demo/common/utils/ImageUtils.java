package com.example.demo.common.utils;

import com.example.demo.config.exception.MyException;
import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;

/**
 * @Desc
 * @Return
 * @Author YuXin
 * @Date 2021/10/5
 **/
public class ImageUtils {
    /**
     * 压缩图片
     * @param: [originFile,scale] 原始图片,压缩比率
     * @return: java.io.File 压缩后图片
     * @author: yuxin
     * @date: 2021/11/3
     */
    public static File compressImg(File originFile,double scale) throws IOException {
        if (!originFile.exists()) {
            throw new MyException("图片不存在！");
        }
        String originFileName = originFile.getName();
        String suffix = originFileName.substring(originFileName.lastIndexOf("."));
        String targetFileName = originFileName.substring(0, originFileName.lastIndexOf(".")) + "_thumb" + suffix;
        File targetFile = new File(originFile.getParent() + File.separator + targetFileName);
        Thumbnails
                .of(originFile.getAbsolutePath())
                .scale(scale)
                .toFile(targetFile.getAbsolutePath());
        return targetFile;
    }
}

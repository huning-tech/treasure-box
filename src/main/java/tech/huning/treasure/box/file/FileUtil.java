package tech.huning.treasure.box.file;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

/**
 * 文件工具类
 *
 * <p>更多内容参看<a href="https://huning.tech"><b>胡宁Tech</b></a>
 * @author huning
 * @version 1.0
 */
public class FileUtil {

    /**
     * 文件删除
     * @param file 文件
     */
    public static void deleteFiles(File file){
        if(null == file || !file.exists()) {
            return;
        }
        if(file.isDirectory()) {
            Arrays.stream(Objects.requireNonNull(file.listFiles())).forEach(FileUtil::deleteFiles);
        }
        file.delete();
    }

}

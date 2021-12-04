package nio.file;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * https://www.baeldung.com/reading-file-in-java
 * @author yuhangbin
 * @date 2021/7/19
 **/
public class FileUtils {

    public static String readFile(String path) throws Exception{
        return Files.readString(Path.of(path));
    }

    public static void writeFile(String path, String content) throws Exception {
        Files.writeString(Path.of(path), content, StandardOpenOption.WRITE);
    }
}

package nio.file;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
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
        Files.write(Path.of(path), content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.WRITE);
    }
}

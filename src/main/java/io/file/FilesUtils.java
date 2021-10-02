package io.file;

import java.io.*;

/**
 * https://www.baeldung.com/reading-file-in-java
 * @author yuhangbin
 * @date 2021/9/19
 **/
public class FilesUtils {

    public static String readFile(String path) throws Exception {
        File file = new File(path);
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        }
        return result.toString();
    }

    public static void writeFile(String path, String content) throws Exception{
        File file = new File(path);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            writer.write(content);
        }
    }
}

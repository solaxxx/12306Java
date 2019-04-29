package per.solax.assist.util;

import java.io.*;

/**
 * @Author: solax
 * @Date: 2019/1/14
 */
public class FileUtil {

    public static File saveLocal (String folderPath, String fileName, String sString) {
        return saveLocal(folderPath, fileName, sString.getBytes());
    }

    public static File saveLocal (String folderPath, String fileName, byte [] s) {
        File file = null;
        try {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        InputStream in = new ByteArrayInputStream(s);
        file = new File(folderPath, fileName);
        FileOutputStream fos = new FileOutputStream(file);
        byte[] b = new byte[1024];
        int nRead = 0;
        while ((nRead = in.read(b)) != -1) {
            fos.write(b, 0, nRead);
        }
        fos.flush();
        fos.close();
        in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }
}

package com.yinrs.mdstream;

import java.io.*;

/**
 * Created by yinrs on 2019/9/24.
 */
public class MdStreamService {
    public String getContent(String path) throws FileNotFoundException {
        File file = new File(path);
        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream(file), "utf-8");
            StringBuffer sb = new StringBuffer();
            char[] chars = new char[1024];
            int length;
            while ((length = reader.read(chars, 0, 1024)) != -1) {
                sb.append(chars, 0, length);
            }
            return sb.toString();
        } catch (FileNotFoundException fnfe) {
            throw fnfe;
        } catch (UnsupportedEncodingException uee) {
            uee.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
        return null;
    }

    public String getFileName(String path) throws FileNotFoundException {
        File file = new File(path);
        if (file.exists()) {
            return file.getName();
        }
        throw new FileNotFoundException(path + " is not found.");
    }

    public void toFile(String path, String content) {
        PrintWriter pw = null;
        try {
            File file = new File(path + ".cases");
            file.createNewFile();
            pw = new PrintWriter(file);
            pw.print(content);
            pw.flush();
            System.out.println("succeed:  "+file.getAbsolutePath());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }
}

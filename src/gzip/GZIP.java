package gzip;

import org.junit.Test;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZIP {

    @Test
    public void fun() {
        String path = "H:\\Java\\IDEA\\work\\test\\out\\production\\proxy\\test.txt";
        try {
            new GZIP().GZip(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String path = "H:\\Java\\IDEA\\work\\test\\out\\production\\proxy\\test.txt";
        try {
            new GZIP().GZip(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void GZip(String filePath) throws IOException {
        if (filePath == null || filePath.trim() == "") {
            System.out.println("请输入文件路径");
        }
        BufferedReader in = new BufferedReader(new FileReader(filePath));
        FileOutputStream fileOut = new FileOutputStream(filePath.substring(0, filePath.indexOf(".")) + ".gz");
        BufferedOutputStream out = new BufferedOutputStream(new GZIPOutputStream(fileOut));
        System.out.println("Gzip start");
        int len;
        while ((len = in.read()) != -1) {
            out.write(len);
        }
        in.close();
        out.close();
        fileOut.close();
        System.out.println("stop");
        System.out.println("jieya start");
        BufferedReader in2 = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(filePath.substring(0, filePath.indexOf(".")) + ".gz"))));
        String s;
        int i = 1;
        while ((s = in2.readLine()) != null) {
            System.out.println("第" + i++ + "行:" + s);
        }
        System.out.println("jieya stop");

    }

}

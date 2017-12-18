package zip;

import java.io.*;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZIP {
    public static void main(String[] args) {
        new ZIP ().zip ("H:\\Java\\IDEA\\work\\test\\out\\production\\proxy\\test");
    }

    public void zip(String filePath) {
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream (filePath + ".zip");
            CheckedOutputStream cos = new CheckedOutputStream (fileOut, new Adler32 ());
            ZipOutputStream zop = new ZipOutputStream (cos);
            BufferedOutputStream bout = new BufferedOutputStream (zop);
            zop.setComment ("test zip");
            File file = new File (filePath);
            if (file.isDirectory ()) {
                String paths[] = file.list ();
                for (String path : paths) {
                    BufferedReader in = new BufferedReader (new FileReader (filePath + "\\" + path));
                    zop.putNextEntry (new ZipEntry ( path));
                    int c;
                    while ((c = in.read ()) != -1) {
                        bout.write (c);
                    }
                    in.close ();
                    bout.flush ();
                }
            }
            bout.close ();
            System.out.println (cos.getChecksum ().getValue ());
        } catch (FileNotFoundException e) {
            e.printStackTrace ();
        } catch (IOException e) {
            e.printStackTrace ();
        } finally {
            try {
                fileOut.close ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
        }
    }
}

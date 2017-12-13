package demo;

import java.io.*;

public class InPutStream implements Interface {
    BufferedWriter bf;
    FileOutputStream out;

    @Override
    public boolean write(File file, String str) {
        try {
            out = new FileOutputStream (file, true);
            //int a = 1 / 0;
            bf = new BufferedWriter (new OutputStreamWriter (out));
            bf.write (str + "\r\n");
            return true;
        } catch (Exception e) {
            //e.printStackTrace ();
            return false;
        } finally {
            try {
                if (bf != null) bf.close ();
                if (out != null) out.close ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
        }
    }
}

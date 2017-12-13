package demo;


import java.io.*;

import static java.lang.reflect.Proxy.newProxyInstance;

public class Test {
    public static void main(String[] args) {
        InPutStream i = new InPutStream ();
        InterHandler ih = new InterHandler (i);
        Class<?> cls = i.getClass ();
        Interface in = (Interface) newProxyInstance (cls.getClassLoader (), cls.getInterfaces (), ih);
        File file = new File (Class.class.getClass ().getResource ("/").getPath () + "test.txt");
        File bfFile = null;
        if (file.exists ()) {
            bfFile = bf (file);
        }
        if (in.write (file, "123") == true) {
            System.out.println ("写入成功");
            bfFile.delete ();
        } else {
            System.out.println ("写入失败");
            cover (file, bfFile);
            boolean delete = bfFile.delete ();
            System.out.println (delete);
        }
    }

    public static File bf(File file) {
        OutputStream out = null;
        InputStream in = null;
        File bfFile = new File (file.getPath () + "bf");
        try {
            in = new FileInputStream (file);
            out = new FileOutputStream (bfFile);
            byte[] b = new byte[1024];
            int len;
            while ((len = in.read (b)) != -1) {
                out.write (b, 0, len);
            }
            return bfFile;
        } catch (FileNotFoundException e) {
            e.printStackTrace ();
        } catch (IOException e) {
            e.printStackTrace ();
        } finally {
            try {
                out.close ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
        }
        return bfFile;
    }

    public static void cover(File file, File bfFile) {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream (bfFile);
            out = new FileOutputStream (file);
            byte[] b = new byte[1024];
            int len;
            while ((len = in.read (b)) != -1) {
                out.write (b, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace ();
        } catch (IOException e) {
            e.printStackTrace ();
        } finally {
            try {
                in.close ();
                out.close ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
        }
    }
}

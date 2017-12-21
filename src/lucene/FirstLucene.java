package lucene;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

public class FirstLucene {
    @Test
    public void testIndes() {
        try {
            //第一步创建IndesWrite对象
            // 1）指定索引库的存放位置Directory对象
            Directory directory = FSDirectory.open(new File("D:\\test"));
            // 2）指定一个分析器，对文档内容进行分析。
            //Analyzer analyzer=new StandardAnalyzer();
            Analyzer analyzer = new IKAnalyzer();
            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LATEST, analyzer);
            IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
            //第二步创建field对象,将field添加到document对象中
            Document document = new Document();
            File file = new File("D:\\LuceneTestTxt");
            File[] files = file.listFiles();
            for (File f : files) {
                //文件名称
                String file_name = file.getName();
                Field fileNameFiled = new StringField("fileNameFiled", file_name, Field.Store.YES);
                //文件大小
                long fiule_size = FileUtils.sizeOf(f);
                Field fileSizeFiled = new LongField("fileSizeFiled", fiule_size, Field.Store.YES);
                //文件路径
                String file_path = f.getPath();
                Field filePathField = new StoredField("filePathFiled", file_path);
                //文件内容
                String file_content = FileUtils.readFileToString(f);
                Field fileContentField = new TextField("fileContentFiled", file_content, Field.Store.YES);
                document.add(fileNameFiled);
                document.add(fileSizeFiled);
                document.add(filePathField);
                document.add(fileContentField);
                //第三步使用IndexWried对象将document对象写入索引库,进行索引创建,并将索引和document对象写入索引库
                indexWriter.addDocument(document);
            }
            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

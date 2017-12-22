package lucene;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

public class FirstLucene {

    @Test
    /**
     *创建索引
     */
    public void testIndex() {
        try {
            //第一步创建IndesWrite对象
            // 1）指定索引库的存放位置Directory对象
            Directory directory = FSDirectory.open(new File("D:\\test"));
            //Directory directory =new RAMDirectory();内存索引库
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
                String file_name = f.getName();
                System.out.println("file_name:" + file_name);
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

    @Test
    /**
     * 索引搜索
     */
    public void seletIndex() {
        try {
            //1创建一个Directouy对象,索引库存放的位置
            Directory directory = FSDirectory.open(new File("D:\\test"));
            //2创建一个indexResader对象
            IndexReader indexReader = DirectoryReader.open(directory);
            //3创建一个indexSearcher对象
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);
            //4创建一个TermQuery对象,指定查询的域和查询的关键词
            Query query = new TermQuery(new Term("fileNameFiled", "spring"));
            //5执行查询
            TopDocs search = indexSearcher.search(query, 2);
            //6返回查询结果,遍历
            ScoreDoc[] scoreDocs = search.scoreDocs;
            for (ScoreDoc scoreDoc : scoreDocs) {
                int doc = scoreDoc.doc;
                Document document = indexSearcher.doc(doc);
                String fileName = document.get("fileNameFiled");
                String fileSizeFiled = document.get("fileSizeFiled");
                String filePathFiled = document.get("filePathFiled");
                String fileContentFiled = document.get("fileContentFiled");
                System.out.println("fileName:" + fileName + "  " + "fileSizeFiled:" + fileSizeFiled + "  " + "filePathFiled:" + "  " + filePathFiled + "  " + "fileContentFiled:" + "  " + fileContentFiled);
                System.out.println("我就是个分隔符--------------------------------------------");
            }
            indexReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

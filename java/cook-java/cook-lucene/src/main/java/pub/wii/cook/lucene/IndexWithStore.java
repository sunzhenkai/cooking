package pub.wii.cook.lucene;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;

import org.apache.commons.lang3.Validate;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.IntPoint;
import org.apache.lucene.document.NumericDocValuesField;
import org.apache.lucene.document.SortedDocValuesField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;
import pub.wii.cook.java.base.GsonUtils;
import pub.wii.cook.java.utils.FileUtils;
import pub.wii.cook.java.utils.UnitUtils;

/**
 * Compare index file size between store field or not.
 * Download Data: https://datasets.imdbws.com/title.akas.tsv.gz
 */
public class IndexWithStore {

  public static final String SEPARATOR = "\\t";

  public static void head(String path, int lines) throws Exception {
    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    InputStream is = classloader.getResourceAsStream(path);
    Validate.notNull(is, "read input file failed");
    InputStreamReader streamReader = new InputStreamReader(is);
    BufferedReader br = new BufferedReader(streamReader);
    String[] header = br.readLine().split(SEPARATOR);
    System.out.println(GsonUtils.GSON.toJson(header));
    String line;
    for (int i = 0; i < lines && (line = br.readLine()) != null; ++i) {
      String[] data = line.split(SEPARATOR);
      System.out.println(GsonUtils.GSON.toJson(data));
    }
    // while ((line = br.readLine()) != null) {
    //   String[] data = line.split(SEPARATOR);
    //   System.out.println(GsonUtils.GSON.toJson(data));
    // }
  }

  public static void createIndex(String inputFilePath,
                                 String indexPath,
                                 boolean isStore,
                                 boolean isDocValue) throws Exception {
    long startTs = System.currentTimeMillis();
    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    InputStream is = classloader.getResourceAsStream(inputFilePath);
    Validate.notNull(is, "read input file failed");
    InputStreamReader streamReader = new InputStreamReader(is);
    BufferedReader br = new BufferedReader(streamReader);

    // create index dir
    Directory dir = FSDirectory.open(Paths.get(indexPath));
    Analyzer analyzer = new StandardAnalyzer();
    IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
    iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
    iwc.setRAMBufferSizeMB(1024);
    IndexWriter writer = new IndexWriter(dir, iwc);
    Field.Store store = isStore ? Field.Store.YES : Field.Store.NO;

    String line;
    String[] header = br.readLine().split(SEPARATOR);
    System.out.println(GsonUtils.GSON.toJson(header));
    while ((line = br.readLine()) != null) {
      String[] data = line.split(SEPARATOR);
      // System.out.println(GsonUtils.GSON.toJson(data));
      Document doc = new Document();
      doc.add(new StringField("titleId", data[0], store));
      doc.add(new IntPoint("ordering", Integer.parseInt(data[1])));
      doc.add(new StringField("title", data[2], store));
      doc.add(new StringField("region", data[3], store));
      doc.add(new StringField("language", data[4], store));
      doc.add(new StringField("types", data[5], store));
      doc.add(new StringField("attributes", data[6], store));
      doc.add(new StringField("isOriginalTitle", data[7], store));

      if (isDocValue) {
        doc.add(new SortedDocValuesField("titleId", new BytesRef(data[0])));
        doc.add(new NumericDocValuesField("ordering", Integer.parseInt(data[1])));
        doc.add(new SortedDocValuesField("title", new BytesRef(data[2])));
        doc.add(new SortedDocValuesField("region", new BytesRef(data[3])));
        doc.add(new SortedDocValuesField("language", new BytesRef(data[4])));
        doc.add(new SortedDocValuesField("types", new BytesRef(data[5])));
        doc.add(new SortedDocValuesField("attributes", new BytesRef(data[6])));
        doc.add(new SortedDocValuesField("isOriginalTitle", new BytesRef(data[7])));
      }

      writer.addDocument(doc);
    }

    writer.commit();
    System.out.println("Write index " + indexPath + " done, doc numbers: "
                       + writer.getDocStats().numDocs + ", cost: " + (System.currentTimeMillis() - startTs));
    writer.close();
  }

  private static Field getField(String name, String data, boolean isStore) {
    FieldType type = new FieldType();
    type.setIndexOptions(IndexOptions.DOCS);
    type.setStored(true);
    return new Field(name, data, type);
  }

  public static String indexWithStore = "tmp/index-with-store";
  public static String indexWithDocValue = "tmp/index-with-doc-value";
  public static String indexWithoutStore = "tmp/index-without-store";
  public static String inputFilePath = "data/title.akas.tsv";

  public static void index() throws Exception {
    createIndex(inputFilePath, indexWithStore, true, true);
    createIndex(inputFilePath, indexWithoutStore, false, false);
    createIndex(inputFilePath, indexWithDocValue, false, true);

    System.out.println(indexWithStore + ": " + UnitUtils.getReadableSize(FileUtils.getSize(new File(indexWithStore))));
    System.out
        .println(indexWithDocValue + ": " + UnitUtils.getReadableSize(FileUtils.getSize(new File(indexWithDocValue))));
    System.out
        .println(indexWithoutStore + ": " + UnitUtils.getReadableSize(FileUtils.getSize(new File(indexWithoutStore))));
  } /** output
   * Write index tmp/index-with-store done, doc numbers: 23603693, cost: 157049
   * Write index tmp/index-without-store done, doc numbers: 23603693, cost: 86950
   * Write index tmp/index-with-doc-value done, doc numbers: 23603693, cost: 133651
   * tmp/index-with-store: (924.7272033691406,MB)
   * tmp/index-with-doc-value: (550.8994102478027,MB)
   * tmp/index-without-store: (237.89043426513672,MB)
   */

  public static void main(String[] args) throws Exception {
    head(inputFilePath, 10);
  }
}

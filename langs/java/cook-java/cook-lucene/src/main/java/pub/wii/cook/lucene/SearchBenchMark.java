package pub.wii.cook.lucene;

import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

public class SearchBenchMark {

  private static void testSearch(String indexPath, int times) throws Exception {
    IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(indexPath)));
    IndexSearcher searcher = new IndexSearcher(reader);
    for (int i = 0; i < times; ++i) {
      long startTs = System.currentTimeMillis();
      FuzzyQuery query = new FuzzyQuery(new Term("title", "Carmencita"));
      // System.out.println("Searching for: " + query.toString("title"));
      TopDocs docs = searcher.search(query, 100);
      // System.out.println(docs.totalHits);
      System.out.println("Cost: " + (System.currentTimeMillis() - startTs));
    }
  }

  public static void main(String[] args) throws Exception {
    testSearch(IndexWithStore.indexWithStore, 10);
    System.out.println();
    testSearch(IndexWithStore.indexWithoutStore, 10);
    System.out.println();
    testSearch(IndexWithStore.indexWithStore, 10);
  }
}

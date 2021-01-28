package pub.wii.cook.java.utils;

import org.apache.commons.lang3.tuple.Pair;

public class UnitUtils {

  public static Pair<Double, StorageUnit> getReadableSize(double size, StorageUnit unit) {
    int iV = unit.getValue();
    while (size >= 1024 && iV < StorageUnit.EB.getValue()) {
      size /= 1024;
      ++iV;
    }
    return Pair.of(size, StorageUnit.fineByValue(iV));
  }

  public static Pair<Double, StorageUnit> getReadableSize(double size) {
    return getReadableSize(size, StorageUnit.BYTE);
  }

}

package pub.wii.cook.java;

import org.junit.Test;
import pub.wii.cook.java.utils.StorageUnit;
import pub.wii.cook.java.utils.UnitUtils;

import static org.junit.Assert.*;

public class UnitUtilsTest {

  @Test
  public void getReadableSize() {
    System.out.println(UnitUtils.getReadableSize(1024, StorageUnit.PB));
    System.out.println(UnitUtils.getReadableSize(1025, StorageUnit.PB));
    System.out.println(UnitUtils.getReadableSize(1025, StorageUnit.EB));
  }
}
package pub.wii.cook.java.utils;

public enum StorageUnit {
  BYTE(0), KB(1), MB(2), GB(3), TB(4), PB(5), EB(6);

  private final int value;
  private StorageUnit(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  public static StorageUnit fineByValue(int value) {
    switch (value) {
      case 0:
        return BYTE;
      case 1:
        return KB;
      case 2:
        return MB;
      case 3:
        return GB;
      case 4:
        return TB;
      case 5:
        return PB;
      case 6:
        return EB;
      default:
        return null;
    }
  }
}

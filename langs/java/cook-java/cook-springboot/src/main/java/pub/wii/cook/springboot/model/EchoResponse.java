package pub.wii.cook.springboot.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

@Data
@Accessors(chain = true)
public class EchoResponse {
  int port;
  Map<String, String> extra = new HashMap<>();
}

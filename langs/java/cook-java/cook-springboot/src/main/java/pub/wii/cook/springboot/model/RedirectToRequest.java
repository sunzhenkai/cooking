package pub.wii.cook.springboot.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RedirectToRequest {
    String path;
}

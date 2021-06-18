package pub.wii.cook.springboot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RedirectFromRequest {
    @JsonProperty("is_redirect")
    boolean isRedirect;
    String message;
}

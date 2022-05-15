package doanthuctap.response;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ImageResponse {
    private byte[] data;
    private String contextType;
}

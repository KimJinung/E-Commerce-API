package kimjinung.commerce.dto.common;


import lombok.Data;


@Data
public class ResponseDto<T> {
    private int statusCode;
    private Long timestamp;
    private T data;

    public ResponseDto(int statusCode, T data) {
        this.statusCode = statusCode;
        this.timestamp = System.currentTimeMillis();
        this.data = data;
    }
}

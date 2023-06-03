package kimjinung.commerce.dto.common;


import lombok.Data;


@Data
public class ResponseDto<T> {
    private int statusCode;
    private Long timestamp;
    private T response;

    public ResponseDto(int statusCode, T response) {
        this.statusCode = statusCode;
        this.timestamp = System.currentTimeMillis();
        this.response = response;
    }
}

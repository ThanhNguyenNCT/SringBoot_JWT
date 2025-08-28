package Cybersoft.ExJPA_Security.payload.respone;

import lombok.Data;

@Data
public class BaseRespone {
    private int code;
    private String message;
    private Object data;
}

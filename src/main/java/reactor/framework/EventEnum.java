package reactor.framework;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yuhangbin
 * @date 2021/12/5
 **/
@Getter
@AllArgsConstructor
public enum EventEnum {

    ACCEPT(0),
    READ(1)
    ;


    private final int code;
}

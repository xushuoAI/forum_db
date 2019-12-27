package org.dgut.community;

import org.dgut.community.resultenum.ResultEnum;

public class NotFoundException extends RuntimeException {

    private Integer code;

    public NotFoundException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}

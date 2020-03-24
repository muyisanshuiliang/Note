package com.example.demo.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author yanglang@cdzhnf.com
 * @date 2019/12/4 11:00
 * @description
 */
@Data
public class AvailableWaterResourceUpdatePO implements Serializable {

    private static final long serialVersionUID = -8143415858745390473L;


    private Double totalWaterAmount;

    private Double januaryAmount;

    private Double februaryAmount;

    private Double marchAmount;

    public Double getTotalWaterAmount(){
        return Optional.ofNullable(this.totalWaterAmount).orElse(0.0);
    }
    public Double getJanuaryAmount(){
        return Optional.ofNullable(this.januaryAmount).orElse(0.0);
    }

    public Double getFebruaryAmount(){
        return Optional.ofNullable(this.februaryAmount).orElse(0.0);
    }
    public Double getMarchAmount(){
        return Optional.ofNullable(this.marchAmount).orElse(0.0);
    }

}

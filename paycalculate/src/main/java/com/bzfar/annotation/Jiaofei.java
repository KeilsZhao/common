package com.bzfar.annotation;

import com.bzfar.util.BigDecimalUtil;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Component
@Builder
public class Jiaofei {

    /**
     * 财产案件
     *
     * @param fee
     * @return 计算结果
     */
	public List<Object> caichan(BigDecimal fee) {
	    List<Object> list = new ArrayList<Object>();
        BigDecimal result = new BigDecimal(0);
		if(BigDecimalUtil.ble(fee,new BigDecimal(10000))) {
			result = new BigDecimal(50);
		} else if(BigDecimalUtil.bgt(fee,new BigDecimal(10000)) && BigDecimalUtil.ble(fee,new BigDecimal(100000))) {
			result = fee.multiply(new BigDecimal(0.025)).subtract(new BigDecimal(200));
		} else if(BigDecimalUtil.bgt(fee,new BigDecimal(100000)) && BigDecimalUtil.ble(fee,new BigDecimal(200000))) {
            result = fee.multiply(new BigDecimal(0.02)).add(new BigDecimal(300));
		} else if(BigDecimalUtil.bgt(fee,new BigDecimal(200000)) && BigDecimalUtil.ble(fee,new BigDecimal(500000))) {
            result = fee.multiply(new BigDecimal(0.015)).add(new BigDecimal(1300));
		} else if(BigDecimalUtil.bgt(fee,new BigDecimal(500000)) && BigDecimalUtil.ble(fee,new BigDecimal(1000000))) {
            result = fee.multiply(new BigDecimal(0.01)).add(new BigDecimal(3800));
		} else if(BigDecimalUtil.bgt(fee,new BigDecimal(1000000)) && BigDecimalUtil.ble(fee,new BigDecimal(2000000))) {
            result = fee.multiply(new BigDecimal(0.009)).add(new BigDecimal(4800));
		} else if(BigDecimalUtil.bgt(fee,new BigDecimal(2000000)) && BigDecimalUtil.ble(fee,new BigDecimal(5000000))) {
            result = fee.multiply(new BigDecimal(0.008)).add(new BigDecimal(6800));
		}else if(BigDecimalUtil.bgt(fee,new BigDecimal(5000000)) && BigDecimalUtil.ble(fee,new BigDecimal(10000000))) {
            result = fee.multiply(new BigDecimal(0.007)).add(new BigDecimal(11800));
		} else if(BigDecimalUtil.bgt(fee,new BigDecimal(10000000)) && BigDecimalUtil.ble(fee,new BigDecimal(20000000))) {
            result = fee.multiply(new BigDecimal(0.006)).add(new BigDecimal(21800));
		}else {
            result = fee.multiply(new BigDecimal(0.005)).add(new BigDecimal(41800));
		}
		// 金额
        list.add(result.setScale(2,RoundingMode.HALF_UP).toString());
		// 减半
        list.add(halve(result));
		return list;
	}

    /**
     * 离婚案件
     *
     * @return 固定范围
     */
	public List<Object> liHun(BigDecimal fee){
        List<Object> list = new ArrayList<Object>();
        list.add("50 - 300");
        list.add("25 - 150");
        return list;
    }

    /**
     * 离婚案件（财产）
     *
     * @param fee
     * @return
     */
    public  List<Object> liHunCaiChan(BigDecimal fee){
        List<Object> list = new ArrayList<Object>();
        if (BigDecimalUtil.ble(fee,new BigDecimal(200000))) {
            return liHun(fee);
        } else {
            BigDecimal data = fee.subtract(new BigDecimal(200000)).multiply(new BigDecimal(0.005)).setScale(2,RoundingMode.HALF_UP);
            // 最低值
            BigDecimal min = data.add(new BigDecimal(50));
            String minHalve = halve(min);
            // 最大值
            BigDecimal max = data.add(new BigDecimal(300));
            String maxHalve = halve(max);
            list.add(min + " - " +max);
            list.add(minHalve + " - " +maxHalve);

        }
        return list;
    }

    /**
     * 人身权
     * @return
     */
    public List<Object> renShenQuan(BigDecimal fee){
        List<Object> list = new ArrayList<Object>();
        list.add("100 - 500");
        list.add("50 - 250");
        return list;
    }

    /**
     * 人身权（财产）
     *
     * @param fee
     * @return
     */
    public  List<Object> renShenQuanCaiChan(BigDecimal fee) {
        List<Object> list = new ArrayList<Object>();
        BigDecimal result = new BigDecimal(0);
        if (BigDecimalUtil.ble(fee,new BigDecimal(50000))) {
            return renShenQuan(fee);
        } else if (BigDecimalUtil.ble(fee,new BigDecimal(100000)) && BigDecimalUtil.bgt(fee,new BigDecimal(50000))) {
            result = (fee.subtract(new BigDecimal(50000))).multiply(new BigDecimal(0.01)).setScale(2,RoundingMode.HALF_UP);
        } else {
            result =  (fee.subtract(new BigDecimal(100000))).multiply(new BigDecimal(0.005)).setScale(2,RoundingMode.HALF_UP).add(new BigDecimal(500));
        }
        // 最低值
        BigDecimal min = result.add(new BigDecimal(100)).setScale(2,RoundingMode.HALF_UP);
        String minHalve = halve(min);
        // 最大值
        BigDecimal max = result.add(new BigDecimal(500)).setScale(2,RoundingMode.HALF_UP);
        String maxHalve = halve(max);
        list.add(min + " - " +max);
        list.add(minHalve + " - " +maxHalve);
        return list;
    }

    /**
     * 其他非财产案件
     *
     * @return
     */
    public List<Object> qiTaFeiCaiChan(BigDecimal fee) {
        List<Object> list = new ArrayList<Object>();
        list.add("50 - 100");
        list.add("25 - 50");
        return list;
    }

    /**
     * 知识产权
     *
     * @return
     */
    public List<Object> zhiShiChanQuan(BigDecimal fee){
        List<Object> list = new ArrayList<Object>();
        list.add("500 - 1000");
        list.add("250 - 500");
        return list;
    }

    /**
     * 知识产权（财产）
     * @param fee
     * @return
     */
    public List<Object> zhiShiChanQuanCaiChan(BigDecimal fee){
        List<Object> list = new ArrayList<Object>();
        BigDecimal result = new BigDecimal(0);
        if(BigDecimalUtil.ble(fee,new BigDecimal(10000))) {
            result = new BigDecimal(50);
        } else if(BigDecimalUtil.bgt(fee,new BigDecimal(10000)) && BigDecimalUtil.ble(fee,new BigDecimal(100000))) {
            result = fee.multiply(new BigDecimal(0.025)).subtract(new BigDecimal(200));
        } else if(BigDecimalUtil.bgt(fee,new BigDecimal(100000)) && BigDecimalUtil.ble(fee,new BigDecimal(200000))) {
            result = fee.multiply(new BigDecimal(0.02)).add(new BigDecimal(300));
        } else if(BigDecimalUtil.bgt(fee,new BigDecimal(200000)) && BigDecimalUtil.ble(fee,new BigDecimal(500000))) {
            result = fee.multiply(new BigDecimal(0.015)).add(new BigDecimal(1300));
        } else if(BigDecimalUtil.bgt(fee,new BigDecimal(500000)) && BigDecimalUtil.ble(fee,new BigDecimal(1000000))) {
            result = fee.multiply(new BigDecimal(0.01)).add(new BigDecimal(3800));
        } else if(BigDecimalUtil.bgt(fee,new BigDecimal(1000000)) && BigDecimalUtil.ble(fee,new BigDecimal(2000000))) {
            result = fee.multiply(new BigDecimal(0.009)).add(new BigDecimal(4800));
        } else if(BigDecimalUtil.bgt(fee,new BigDecimal(2000000)) && BigDecimalUtil.ble(fee,new BigDecimal(5000000))) {
            result = fee.multiply(new BigDecimal(0.008)).add(new BigDecimal(6800));
        }else if(BigDecimalUtil.bgt(fee,new BigDecimal(5000000)) && BigDecimalUtil.ble(fee,new BigDecimal(10000000))) {
            result = fee.multiply(new BigDecimal(0.007)).add(new BigDecimal(11800));
        } else if(BigDecimalUtil.bgt(fee,new BigDecimal(10000000)) && BigDecimalUtil.ble(fee,new BigDecimal(20000000))) {
            result = fee.multiply(new BigDecimal(0.006)).add(new BigDecimal(21800));
        }else {
            result = fee.multiply(new BigDecimal(0.005)).add(new BigDecimal(41800));
        }
        // 金额
        list.add(result.setScale(2,RoundingMode.HALF_UP).toString());
        // 减半
        list.add(halve(result));
        return list;
    }

    /**
     * 劳动争议
     *
     * @return
     */
    public List<Object> LaoDongZhengYi(BigDecimal fee) {
        List<Object> list = new ArrayList<Object>();
        list.add("10");
        list.add("5");
        return list;
    }


    /**
     * 破产案件
     *
     * @param fee
     * @return
     */
    public List<Object> getPochan(BigDecimal fee){
        List<Object> list = new ArrayList<Object>();
        BigDecimal result = new BigDecimal(0);
        if(BigDecimalUtil.blt(fee,new BigDecimal(10000))) {
            result = new BigDecimal(50);
        } else if(BigDecimalUtil.bge(fee,new BigDecimal(10000)) && BigDecimalUtil.blt(fee,new BigDecimal(100000))) {
            result = fee.multiply(new BigDecimal(0.025)).subtract(new BigDecimal(200));
        } else if(BigDecimalUtil.bge(fee,new BigDecimal(100000)) && BigDecimalUtil.blt(fee,new BigDecimal(200000))) {
            result = fee.multiply(new BigDecimal(0.02)).add(new BigDecimal(300));
        } else if(BigDecimalUtil.bge(fee,new BigDecimal(200000)) && BigDecimalUtil.blt(fee,new BigDecimal(500000))) {
            result = fee.multiply(new BigDecimal(0.015)).add(new BigDecimal(1300));
        } else if(BigDecimalUtil.bge(fee,new BigDecimal(500000)) && BigDecimalUtil.blt(fee,new BigDecimal(1000000))) {
            result = fee.multiply(new BigDecimal(0.01)).add(new BigDecimal(3800));
        } else if(BigDecimalUtil.bge(fee,new BigDecimal(1000000)) && BigDecimalUtil.blt(fee,new BigDecimal(2000000))) {
            result = fee.multiply(new BigDecimal(0.009)).add(new BigDecimal(4800));
        } else if(BigDecimalUtil.bge(fee,new BigDecimal(2000000)) && BigDecimalUtil.blt(fee,new BigDecimal(5000000))) {
            result = fee.multiply(new BigDecimal(0.008)).add(new BigDecimal(6800));
        }else if(BigDecimalUtil.bge(fee,new BigDecimal(5000000)) && BigDecimalUtil.blt(fee,new BigDecimal(10000000))) {
            result = fee.multiply(new BigDecimal(0.007)).add(new BigDecimal(11800));
        } else if(BigDecimalUtil.bge(fee,new BigDecimal(10000000)) && BigDecimalUtil.blt(fee,new BigDecimal(20000000))) {
            result = fee.multiply(new BigDecimal(0.006)).add(new BigDecimal(21800));
        }else {
            result = fee.multiply(new BigDecimal(0.005)).add(new BigDecimal(41800));
        }
        BigDecimal divide = result.divide(new BigDecimal(2)).setScale(2,RoundingMode.HALF_UP);
        if(BigDecimalUtil.bge(divide,new BigDecimal(300000))) {
            divide = new BigDecimal(300000);
        }
        list.add(divide.toString());
        list.add(halve(divide));
        return list;
    }

    /**
     * 管辖权
     * @return
     */
    public List<Object> guanXiaQuan(BigDecimal fee) {
        List<Object> list = new ArrayList<Object>();
        list.add("50 - 100");
        list.add("25 - 50");
        return list;
    }


    /**
     * 公示催告
     *
     * @return
     */
    public List<Object> gongShiCuiGao(BigDecimal fee) {
        List<Object> list = new ArrayList<Object>();
        list.add("100");
        list.add("50");
        return list;
    }

    /**
     * 支付令
     *
     * @param fee
     * @return
     */
	public List<Object> Zhifuling(BigDecimal fee){
        List<Object> list = new ArrayList<Object>();
        BigDecimal result = new BigDecimal(0);
        if(BigDecimalUtil.blt(fee,new BigDecimal(10000))) {
            result = new BigDecimal(50);
        } else if(BigDecimalUtil.bge(fee,new BigDecimal(10000)) && BigDecimalUtil.blt(fee,new BigDecimal(100000))) {
            result = fee.multiply(new BigDecimal(0.025)).subtract(new BigDecimal(200));
        } else if(BigDecimalUtil.bge(fee,new BigDecimal(100000)) && BigDecimalUtil.blt(fee,new BigDecimal(200000))) {
            result = fee.multiply(new BigDecimal(0.02)).add(new BigDecimal(300));
        } else if(BigDecimalUtil.bge(fee,new BigDecimal(200000)) && BigDecimalUtil.blt(fee,new BigDecimal(500000))) {
            result = fee.multiply(new BigDecimal(0.015)).add(new BigDecimal(1300));
        } else if(BigDecimalUtil.bge(fee,new BigDecimal(500000)) && BigDecimalUtil.blt(fee,new BigDecimal(1000000))) {
            result = fee.multiply(new BigDecimal(0.01)).add(new BigDecimal(3800));
        } else if(BigDecimalUtil.bge(fee,new BigDecimal(1000000)) && BigDecimalUtil.blt(fee,new BigDecimal(2000000))) {
            result = fee.multiply(new BigDecimal(0.009)).add(new BigDecimal(4800));
        } else if(BigDecimalUtil.bge(fee,new BigDecimal(2000000)) && BigDecimalUtil.blt(fee,new BigDecimal(5000000))) {
            result = fee.multiply(new BigDecimal(0.008)).add(new BigDecimal(6800));
        }else if(BigDecimalUtil.bge(fee,new BigDecimal(5000000)) && BigDecimalUtil.blt(fee,new BigDecimal(10000000))) {
            result = fee.multiply(new BigDecimal(0.007)).add(new BigDecimal(11800));
        } else if(BigDecimalUtil.bge(fee,new BigDecimal(10000000)) && BigDecimalUtil.blt(fee,new BigDecimal(20000000))) {
            result = fee.multiply(new BigDecimal(0.006)).add(new BigDecimal(21800));
        }else {
            result = fee.multiply(new BigDecimal(0.005)).add(new BigDecimal(41800));
        }
        BigDecimal divide = result.multiply(new BigDecimal(1)).divide(new BigDecimal(3), 2, RoundingMode.HALF_UP);
        list.add(divide.toString());
        list.add(halve(divide));
        return list;
	}

    /**
     * 商标、专利
     *
     * @return
     */
    public List<Object> shangBiao(BigDecimal fee) {
        List<Object> list = new ArrayList<Object>();
        list.add("100");
        list.add("50");
        return list;
    }

    /**
     * 其他行政案件
     *
     * @return
     */
    public List<Object> qiTaXingZheng(BigDecimal fee) {
        List<Object> list = new ArrayList<Object>();
        list.add("50");
        list.add("25");
        return list;
    }

    /**
     * 申请认定或撤销仲裁
     *
     * @return
     */
    public List<Object> zhongCai(BigDecimal fee) {
        List<Object> list = new ArrayList<Object>();
        list.add("400");
        list.add("200");
        return list;
    }

    /**
     * 执行案件
     *
     * @return
     */
    public List<Object> zhiXing(BigDecimal fee){
        List<Object> list = new ArrayList<Object>();
        list.add("50 - 500");
        list.add("25 - 250");
        return list;
    }

    /**
     * 执行案件（财产）
     *
     * @param fee
     * @return
     */
    public List<Object> zhiXingCaiChan(BigDecimal fee) {
        List<Object> list = new ArrayList<Object>();
        BigDecimal result = new BigDecimal(0);
        if(BigDecimalUtil.ble(fee,new BigDecimal(10000))) {
            result = new BigDecimal(50);
        }else if(BigDecimalUtil.bgt(fee,new BigDecimal(10000)) && BigDecimalUtil.ble(fee,new BigDecimal(500000))) {
            result = fee.multiply(new BigDecimal(0.015)).subtract(new BigDecimal(100));
        }else if(BigDecimalUtil.bgt(fee,new BigDecimal(500000)) && BigDecimalUtil.ble(fee,new BigDecimal(5000000))) {
            result = fee.multiply(new BigDecimal(0.01)).add(new BigDecimal(2400));
        }else if(BigDecimalUtil.bgt(fee,new BigDecimal(5000000)) && BigDecimalUtil.ble(fee,new BigDecimal(10000000))) {
            result = fee.multiply(new BigDecimal(0.005)).add(new BigDecimal(27400));
        }else if(BigDecimalUtil.bgt(fee,new BigDecimal(10000000)) ) {
            result = fee.multiply(new BigDecimal(0.001)).add(new BigDecimal(67400));
        } else{
            result = new BigDecimal(500);
        }
        result = result.setScale(2,RoundingMode.HALF_UP);
        list.add(result.toString());
        list.add(halve(result));
        return list;
    }

    /**
     * 保全
     *
     * @return
     */
    public List<Object> baoQuan(BigDecimal fee) {
        List<Object> list = new ArrayList<Object>();
        list.add("30");
        list.add("15");
        return list;
    }

    /**
     * 保全（财产）
     * @param fee
     * @return
     */
    public List<Object> baoQuanCaiChan(BigDecimal fee) {
        List<Object> list = new ArrayList<Object>();
        BigDecimal result = new BigDecimal(0);
        if(BigDecimalUtil.ble(fee,new BigDecimal(1000))) {
            result = new BigDecimal(30);
        }else if(BigDecimalUtil.bgt(fee,new BigDecimal(1000)) && BigDecimalUtil.ble(fee,new BigDecimal(100000))){
            result = fee.multiply(new BigDecimal(0.01)).add(new BigDecimal(20));
        }else  if(BigDecimalUtil.bgt(fee,new BigDecimal(100000)) && BigDecimalUtil.ble(fee,new BigDecimal(896000))){
            result = fee.multiply(new BigDecimal(0.005)).add(new BigDecimal(520));
        }else{
            result = new BigDecimal(5000);
        }
        result = result.setScale(2,RoundingMode.HALF_UP);
        list.add(result.toString());
        list.add(halve(result));
        return list;
    }

    /**
     * 计算减半金额
     *
     * @param money
     * @return
     */
	private String halve(BigDecimal money){
        return money.divide(new BigDecimal(2)).setScale(2,RoundingMode.HALF_UP).toString();
    }
}

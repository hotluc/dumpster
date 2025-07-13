package design_pattern.strategy.factory;

import java.util.Map;
import java.util.logging.Logger;

public class CouponCommodityService implements ICommodity{
    private Logger logger = Logger.getLogger(CouponCommodityService.class.getName());

    @Override
    public void sendCommodity(String uId, String commodityId, String bizId, Map<String, String> extMap) throws Exception {
        System.out.println("模拟发放优惠券：" + uId + " " + commodityId + " " + bizId + " " + extMap);
    }
}

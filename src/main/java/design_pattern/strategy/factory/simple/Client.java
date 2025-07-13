package design_pattern.strategy.factory.simple;

import design_pattern.strategy.factory.simple.util.XmlUtil;

public class Client {
    public static void main(String[] args) {
        String type = XmlUtil.getChartType();
        Chart chart = ChartFactory.getChart(type);
        chart.display();
    }
}

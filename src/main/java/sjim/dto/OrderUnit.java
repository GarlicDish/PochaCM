package sjim.dto;

public class OrderUnit {
	private int orderUnitNum;
	private String orderUnit;
	@Override
	public String toString() {
		return "OrderUnit [orderUnitNum=" + orderUnitNum + ", orderUnit=" + orderUnit + "]";
	}
	public int getOrderUnitNum() {
		return orderUnitNum;
	}
	public void setOrderUnitNum(int orderUnitNum) {
		this.orderUnitNum = orderUnitNum;
	}
	public String getOrderUnit() {
		return orderUnit;
	}
	public void setOrderUnit(String orderUnit) {
		this.orderUnit = orderUnit;
	}
	
	
}

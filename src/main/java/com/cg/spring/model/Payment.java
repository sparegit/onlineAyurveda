  
package com.cg.spring.model;




import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;




@Entity
public class Payment {
	public Payment( @NonNull String paymentType, @NotEmpty double itemTotal, @NotEmpty double shippingFee,
			@NotEmpty double totalPrice) {
		super();
		this.paymentType = paymentType;
		this.itemTotal = itemTotal;
		this.shippingFee = shippingFee;
		this.totalPrice = totalPrice;
	}
	public Payment() {
		
	}
	
	@Id
	@GeneratedValue
	private int paymentId;
	@NonNull
	private String paymentType;
	@NotEmpty
	private double itemTotal;
	@NotEmpty
	private double shippingFee;
	@NotEmpty
	private double totalPrice;
	@OneToOne(targetEntity = Order.class, cascade = CascadeType.ALL)
	@JoinColumn(name="order_fk",referencedColumnName = "orderId")
	private Order order;
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public double getItemTotal() {
		return itemTotal;
	}
	public void setItemTotal(double itemTotal) {
		this.itemTotal = itemTotal;
	}
	public double getShippingFee() {
		return shippingFee;
	}
	public void setShippingFee(double shippingFee) {
		this.shippingFee = shippingFee;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	

}
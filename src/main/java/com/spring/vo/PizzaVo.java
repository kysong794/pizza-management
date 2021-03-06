package com.spring.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PizzaVo {
	private String pcode;
	private String pname;
	private int cost;
	
	private String scode;
	private String sname;
	
	private int saleno;
	
	@DateTimeFormat(pattern = "yy-MM-dd")
	private Date saledate;
	private int amount;
	
	private Long sumcost;

	public String formatDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(this.saledate);
	}
}

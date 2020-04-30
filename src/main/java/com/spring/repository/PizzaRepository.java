package com.spring.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spring.vo.PizzaVo;

@Mapper
public interface PizzaRepository {
	
	//pRegsave = 전표등록
	void pRegSave(PizzaVo pizzaVo);
	
	String saleno();
	
	List<PizzaVo> scodelist();
	
	List<PizzaVo> pcodelist();
	
	List<PizzaVo> pcodeSaleList();
	
	List<PizzaVo> shopSaleList();
	
	List<PizzaVo> totalSaleList();
	
	void delete(int saleno);
	
	void update(PizzaVo pizzaVo);
}

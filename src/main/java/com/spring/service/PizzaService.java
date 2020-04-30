package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.repository.PizzaRepository;
import com.spring.vo.PizzaVo;

@Service
@Transactional
public class PizzaService {
	
	@Autowired
	private PizzaRepository pizzaRepository;
	
	//판매코드리스트
	public List<PizzaVo> scodelist(){
		return pizzaRepository.scodelist();
	}
	
	//피자코드리스트
	public List<PizzaVo> pcodelist(){
		return pizzaRepository.pcodelist();
	}
	
	//판매번호
	public String saleno() {
		return pizzaRepository.saleno();
	}
	
	//매출등록 (매출전표번호를 웹에 노출)
	public void pRegSave(PizzaVo pizzaVo) {
		
//		String saleno = pizzaRepository.saleno();
//		pizzaVo.setSaleno(saleno);
//		전표번호를 웹에 노출할때 필요
		
		pizzaRepository.pRegSave(pizzaVo);
	}
	
	public List<PizzaVo> pcodeSaleList(){
		return pizzaRepository.pcodeSaleList();
	}
	
	public List<PizzaVo> shopSaleList(){
		return pizzaRepository.shopSaleList();
	}
	
	public List<PizzaVo> totalSaleList(){
		return pizzaRepository.totalSaleList();
	}
	
	public void delete(int saleno) {
		pizzaRepository.delete(saleno);
	}
	
	public void update(PizzaVo pizzaVo) {
		pizzaRepository.update(pizzaVo);
	}
	
	public PizzaVo selectSale(int saleno) {
		return pizzaRepository.selectSale(saleno);
	}
}

package com.spring.pizza;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.service.PizzaService;
import com.spring.vo.PizzaVo;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/pizza")
@Slf4j
public class PizzaController {
	
	@Autowired
	private PizzaService pizzaService;
	
	//DB에서 값을 가져올때
	@GetMapping("/pReg") //인터넷 주소창의 주소
	public String pReg(HttpServletRequest req) {
		List<PizzaVo> scodelist = pizzaService.scodelist();
		List<PizzaVo> pcodelist = pizzaService.pcodelist();
		
		String saleno = pizzaService.saleno();
		
		req.setAttribute("scodelist",scodelist);
		req.setAttribute("pcodelist",pcodelist);
		req.setAttribute("saleno", saleno);
		return "pReg"; //jsp의 이름 혹은 위치 (주소가 아님)
	}
	
	// 추가 사항 발생
	// 수정했지롱
	// 수정함 ㅋㅋ
	
//	@PostMapping("/pRegSave") 서블릿에서 사용하던 방법
//	public String pRegSaveTest(/* HttpServletRequest req */ PizzaVo pizzaVo) {
//		
//		String saleno = (String) req.getAttribute("saleno");
//		String scode = (String) req.getAttribute("scode");
//		String saledate = (String) req.getAttribute("saledate");
//		String pcode = (String) req.getAttribute("pcode");
//		String amount = (String) req.getAttribute("amount");
//		
//		PizzaVo pizzaVo = new PizzaVo();
		
//		pizzaVo.setSaleno(saleno);
//		pizzaVo.setScode(scode);
//		pizzaVo.setSaledate(saledate);
//		pizzaVo.setPcode(pcode);
//		pizzaVo.setAmount(amount);
		
//		
//		pizzaService.pRegSave(pizzaVo);
//		return "redirect:/";
//	}
	
	//DB에 값을 넣을때
	@PostMapping("/pRegSave")
	public String pRegSave(PizzaVo pizzaVo) {
		
		pizzaService.pRegSave(pizzaVo);
		return "redirect:/pizza/pReg";
//		"redirect:"는 그 주소로 아무런 값을 가지지 않은상태로 가는것
	}
	
	//상품별 매출 현황
//	ModelAndView 라는것것을 사용,모델에 전부 넣고 모델을 보낸다,HttpServletRequest랑 비슷하면서 다름
	@GetMapping("/pcodeSaleList")
	public ModelAndView pcodeSaleList(ModelAndView mav) {
		List<PizzaVo> pcodeSaleList = pizzaService.pcodeSaleList();
		
		// 데이터를 set
		mav.addObject("pcodeSaleList", pcodeSaleList);
		mav.setViewName("pcodeSaleList");
		
		return mav;
	}
	
	//지점별 매출 현황
	@GetMapping("/shopSaleList")
	public String shopSaleList(HttpServletRequest req) {
		List<PizzaVo> shopSaleList = pizzaService.shopSaleList();
		
		req.setAttribute("shopSaleList", shopSaleList);
		return "shopSaleList";
	}
	
	//통합매출조회
	@GetMapping("/totalSaleList")
	public String totalSaleList(HttpServletRequest req) {
		List<PizzaVo> totalSaleList = pizzaService.totalSaleList();
		
		req.setAttribute("totalSaleList", totalSaleList);
		return "totalSaleList";
	}
	
	// 모든 요청은 한번 실행되면 현재 값이 사라짐 (그래서 redirect: 로 리턴해야함)
	//삭제
	@PostMapping("delete")
	public String delete(int saleno) {
		pizzaService.delete(saleno);
		return "redirect:/pizza/totalSaleList";
	}
	
	//수정
	@PostMapping("update")
	public String update(PizzaVo pizzaVo) {
		pizzaService.update(pizzaVo);
		return "redirect:/pizza/update";
	}
	
	@GetMapping("update")
	public String update() {

		return "redirect:/pizza/update";
	}
}

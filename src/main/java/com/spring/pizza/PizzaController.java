package com.spring.pizza;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		
		int saleno = pizzaService.saleno();
		
		req.setAttribute("scodelist",scodelist);
		req.setAttribute("pcodelist",pcodelist);
		req.setAttribute("saleno", saleno);
		return "pReg"; //jsp의 이름 혹은 위치 (주소가 아님)
	}
	
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
	
	
	/**
		원시 타입 & 참조 타입
		
		원시 타입 Primitive Type : 해당 값은 주소가 없음. 값만 존재하며, 값을 주지 않으면 기본 값이 들어감
		int a;
		a -> 0;
		ex) int a = 10; a -> 10;
		
		원시형 타입 int, float, double, long, ... 소문자로 시작
		참조 타입 Integer, Float, Double, Date, String, ... 대문자로 시작
		
		참조 타입 Reference Type : 주소와 값을 가짐
		Integer a;
		a -> null; 주소가 없으니까...
		ex) Integer a = 10; a -> 0xf51ae188f, Integer.valueOf(a) -> 10
	 */
	
	//DB에 값을 넣을때
	@PostMapping("/pRegSave")
	public String pRegSave(PizzaVo pizzaVo, RedirectAttributes attr) {		
		// validation(검사) 시작
		if (pizzaVo.getScode() == null || "".equals(pizzaVo.getScode())) {
			attr.addFlashAttribute("errorMessage", "지점을 선택해 주세요");
			return "redirect:/pizza/pReg";
		}

		if (pizzaVo.getSaledate() == null) {
			attr.addFlashAttribute("errorMessage", "날짜를 선택해 주세요");
			return "redirect:/pizza/pReg";
		}

		if (StringUtils.isEmpty(pizzaVo.getPcode())) {
			attr.addFlashAttribute("errorMessage", "피자를 선택해 주세요");
			return "redirect:/pizza/pReg";
		}
		
		if (pizzaVo.getAmount() <= 0) {
			attr.addFlashAttribute("errorMessage", "판매수량을 확인해 주세요");
			return "redirect:/pizza/pReg";
		}	

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
	public String update(PizzaVo pizzaVo, RedirectAttributes attr) {
		
		// validation(검사) 시작
		if (pizzaVo.getScode() == null || "".equals(pizzaVo.getScode())) {
			attr.addFlashAttribute("errorMessage", "지점을 선택해 주세요");
			return "redirect:/pizza/update?saleno="+pizzaVo.getSaleno();
		}

		if (pizzaVo.getSaledate() == null) {
			attr.addFlashAttribute("errorMessage", "날짜를 선택해 주세요");
			return "redirect:/pizza/update?saleno="+pizzaVo.getSaleno();
		}

		if (StringUtils.isEmpty(pizzaVo.getPcode())) {
			attr.addFlashAttribute("errorMessage", "피자를 선택해 주세요");
			return "redirect:/pizza/update?saleno="+pizzaVo.getSaleno();
		}
		
		if (pizzaVo.getAmount() <= 0) {
			attr.addFlashAttribute("errorMessage", "판매수량을 확인해 주세요");
			return "redirect:/pizza/update?saleno="+pizzaVo.getSaleno();
		}	
		
		pizzaService.update(pizzaVo);
		return "redirect:/pizza/totalSaleList";
	}
	
	// Query Parameter => Get 방식으로 주소 이동 시, 변수를 전달하기 위한 방법 중 하나
	// ex) 주소가 /pizza/update일 때, 변수 saleno를 전달하고 싶음
	// saleno=100001라면,
	// '?' <- QueryParameter를 시작한다는 선언
	// 전달하고자 하는 변수가 많을 경우, '&'로 구분
	// [saleno=100001&pcode=A001&scode=B002] (쿼리스트링)
	// /주소?saleno=100001&pcode=A001&scode=B002
	
	//return "redirect:/pizza/update?saleno=" + pizzaVo.getSaleno();
	//수정 화면으로 그대로 유지
	
	/**
	 * @RequestParam : 에서 http://localhost:8080/pizza/update?이름=값
	 * 	query string "이름=값"을 가리킨다.
	 * 	값을 가져오기 위해서 쓰는 애노테이션 이름은 변수 이름, 값은 변수에 할당한다.
	 * 
	 * @Param : MyBatis에서 Repository 메서드의 파라미터가 여러 개 일 때, 각각의 변수 이름을 지정하기 위해서 사용
	 * ex)
	 * 		Repository의 메서드
	 * 		List<Object> selectAll(
	 * 			@Param("objNo") int objNo, @Param("objName") String objName
	 * 		);
	 * 
	 * 		mapper
	 * 		<select>
	 * 			select * from
	 * 			where objNo = #{objNo} & objName = #{objName}
	 * 		</select>
	 * 
	 */
	@GetMapping("update") 
	public String update(@RequestParam int saleno, HttpServletRequest req) {
		PizzaVo sale = pizzaService.selectSale(saleno);

		List<PizzaVo> scodelist = pizzaService.scodelist();
		List<PizzaVo> pcodelist = pizzaService.pcodelist();
		
		req.setAttribute("sale", sale);
		req.setAttribute("scodelist",scodelist);
		req.setAttribute("pcodelist",pcodelist);
		
		return "update";
	}

}

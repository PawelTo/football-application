package pl.pawel.customResponse;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	@ResponseBody
	@GetMapping("/test")
	public ResponseEntity test() {
		MyErrorResponse err = new MyErrorResponse("błąd",123);
		//ResponseEntity resp = new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
		OkResponse okReps = new OkResponse("spoko", 321, LocalDate.of(2019, 8, 17));
		ResponseEntity resp = new ResponseEntity<>(okReps,HttpStatus.OK);
		return resp;
	}
}

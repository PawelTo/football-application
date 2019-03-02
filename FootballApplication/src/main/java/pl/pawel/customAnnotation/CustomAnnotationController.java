package pl.pawel.customAnnotation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomAnnotationController {

	@RequestMapping("/getAnnotatedClass")
	public String getAnnotatedClass() {
		CustomAnnotationProcessing cap = new CustomAnnotationProcessing();
		cap.printAnnotatedClass();
		return "start";
	}
}
package pl.pawel.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.pawel.service.dataFromWebSaver.DraftWebMatchReader;

@Controller
public class GetDataFromWebController {

	@RequestMapping("/getHTML")
	public String getDataFromWeb() {
		DraftWebMatchReader wMR = new DraftWebMatchReader("adres");
		try {
			wMR.getDocument();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "start";
	}
}

package pl.pawel.AOPTest;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**Test Class to learn AOP
 * @author Pawel
 *
 */
@Aspect
@Component
public class AspectLogClass {

	@Before(value = "execution(* pl.pawel.controller.*.*Page())")
	public void printPageInfo() {
		System.out.println("Running Controller method finished with *Page()");
	}
}

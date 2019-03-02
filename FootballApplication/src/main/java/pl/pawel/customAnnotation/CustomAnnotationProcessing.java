package pl.pawel.customAnnotation;

import java.lang.reflect.Field;
import java.util.Set;

import org.reflections.Reflections;

public class CustomAnnotationProcessing {

	public void printAnnotatedClass() {
		Set<Class<?>> annotatedClasses = getAnnotationClass();
		for(Class<?> annotatedClass : annotatedClasses) {
			Field[] fields = annotatedClass.getDeclaredFields();
			System.out.println("Class "+ annotatedClass.getSimpleName() +" has fields:");
			for (Field field : fields) {
				System.out.println("Field: "+field.getName());
			}
		}
	}
	
	private Set<Class<?>> getAnnotationClass() {
		Reflections reflection = new Reflections("pl.pawel.customAnnotation");
		Set<Class<?>> annotatedClasses = reflection.getTypesAnnotatedWith(MyCustomAnnotation.class);
		return annotatedClasses;
	}
}

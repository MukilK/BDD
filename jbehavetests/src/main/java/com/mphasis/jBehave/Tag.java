package com.mphasis.jBehave;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@Inherited
public @interface Tag {
	String[] value() default {};
}

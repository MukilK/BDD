package com.mphasis.jBehave;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
public @interface Tag {
    String[] value() default {};
}

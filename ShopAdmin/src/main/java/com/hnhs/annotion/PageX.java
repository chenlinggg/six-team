package com.hnhs.annotion;

import java.lang.annotation.*;

@Target( ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PageX {
}

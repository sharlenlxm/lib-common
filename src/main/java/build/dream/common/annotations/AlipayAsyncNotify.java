package build.dream.common.annotations;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AlipayAsyncNotify {
    String uuidKey();
}

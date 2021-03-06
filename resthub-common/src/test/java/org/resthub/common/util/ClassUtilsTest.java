package org.resthub.common.util;

import java.util.ArrayList;
import org.fest.assertions.api.Assertions;
import org.testng.annotations.Test;

public class ClassUtilsTest {

    @Test
    public void testGetGenericType() {
        Assertions.assertThat(ClassUtils.getGenericType(Object.class)).isNull();
        Assertions.assertThat(ClassUtils.getGenericType(SampleResource.class)).isNull();
    }

    @Test
    public void testGetGenericTypeFromBean() {
        Assertions.assertThat(ClassUtils.getGenericTypeFromBean(new Object())).isNull();
        Assertions.assertThat(ClassUtils.getGenericTypeFromBean(new SampleResource())).isNull();
        Assertions.assertThat(ClassUtils.getGenericTypeFromBean(new SampleResourceArrayList())).isEqualTo(
                SampleResource.class);
    }

    private static class SampleResource {
        private static final long serialVersionUID = 4925762850344399849L;
    }

    private static class SampleResourceArrayList extends ArrayList<SampleResource> {
        private static final long serialVersionUID = 4925762850344399849L;
    }

}

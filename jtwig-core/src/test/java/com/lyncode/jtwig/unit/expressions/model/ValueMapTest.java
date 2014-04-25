/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lyncode.jtwig.unit.expressions.model;

import com.lyncode.jtwig.JtwigContext;
import com.lyncode.jtwig.compile.CompileContext;
import com.lyncode.jtwig.expressions.api.Expression;
import com.lyncode.jtwig.expressions.model.Constant;
import com.lyncode.jtwig.expressions.model.ValueMap;
import com.lyncode.jtwig.render.RenderContext;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class ValueMapTest {
    @Test
    public void test() throws Exception {
        ValueMap underTest = new ValueMap(null)
                .add("key", new Constant<>(null));

        CompileContext context = mock(CompileContext.class);
        JtwigContext jtwigContext = mock(JtwigContext.class);

        Expression expression = underTest.compile(context);
        assertNotNull(expression);

        Map map = (Map) expression.calculate(RenderContext.create(null, jtwigContext, null));
        assertTrue(map.containsKey("key"));
    }
}

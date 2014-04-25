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

package com.lyncode.jtwig.expressions.model;

import com.lyncode.jtwig.compile.CompileContext;
import com.lyncode.jtwig.exception.CalculateException;
import com.lyncode.jtwig.exception.CompileException;
import com.lyncode.jtwig.expressions.api.Expression;
import com.lyncode.jtwig.parser.model.JtwigPosition;
import com.lyncode.jtwig.render.RenderContext;
import com.lyncode.jtwig.util.ObjectExtractor;

import java.util.ArrayList;

public class Variable extends AbstractCompilableExpression {
    private String name;

    public Variable(JtwigPosition position, String name) {
        super(position);
        this.name = name;
    }

    @Override
    public Expression compile(CompileContext context) throws CompileException {
        return new Compiled(position(), name);
    }

    public static class Compiled implements Expression {
        private final String name;
        private final JtwigPosition position;

        public Compiled(JtwigPosition position, String name) {
            this.position = position;
            this.name = name;
        }


        public FunctionElement.Compiled toFunction () {
            return new FunctionElement.Compiled(position, name, new ArrayList<Expression>());
        }

        @Override
        public Object calculate(RenderContext context) throws CalculateException {
            return context.model().map(name);
        }

        public Object extract(RenderContext context, ObjectExtractor extractor) throws ObjectExtractor.ExtractException {
            if (context.configuration().strictVariables() && extractor.contextIsEmpty())
                throw new ObjectExtractor.ExtractException(position+": Unable to retrieve property/field "+name+" from "+extractor.context());
            return extractor.extract(name);
        }
    }
}

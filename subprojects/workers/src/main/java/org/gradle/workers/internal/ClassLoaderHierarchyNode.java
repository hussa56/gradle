/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.workers.internal;

import com.google.common.base.Objects;
import org.gradle.internal.classloader.ClassLoaderSpec;

public class ClassLoaderHierarchyNode {
    private final ClassLoaderSpec self;
    private final ClassLoaderHierarchyNode parent;

    public ClassLoaderHierarchyNode(ClassLoaderSpec self) {
        this.self = self;
        this.parent = null;
    }

    public ClassLoaderHierarchyNode(ClassLoaderSpec self, ClassLoaderHierarchyNode parent) {
        this.self = self;
        this.parent = parent;
    }

    public ClassLoaderHierarchyNode withChild(ClassLoaderSpec spec) {
        ClassLoaderHierarchyNode childNode = new ClassLoaderHierarchyNode(spec, this);
        return childNode;
    }

    public ClassLoaderSpec getSpec() {
        return self;
    }

    public ClassLoaderHierarchyNode getParent() {
        return parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClassLoaderHierarchyNode that = (ClassLoaderHierarchyNode) o;
        return Objects.equal(self, that.self) &&
                Objects.equal(parent, that.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(self, parent);
    }
}

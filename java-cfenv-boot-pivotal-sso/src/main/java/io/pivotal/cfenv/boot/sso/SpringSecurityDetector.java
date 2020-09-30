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
package io.pivotal.cfenv.boot.sso;

import org.springframework.util.ClassUtils;

/**
 * @author Pivotal Application Single Sign-On
 */
public abstract class SpringSecurityDetector {
    private static boolean usingSpringSecurity;
    private static boolean usingLegacy;
    private static boolean usingSpringResourceServer;

    static {
        ClassLoader classLoader = SpringSecurityDetector.class.getClassLoader();
        usingSpringSecurity = ClassUtils.isPresent("org.springframework.security.core.Authentication", classLoader);
        usingLegacy = ClassUtils.isPresent("org.springframework.security.oauth2.common.DefaultOAuth2AccessToken", classLoader);
        usingSpringResourceServer = ClassUtils.isPresent("org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider", classLoader);
    }

    static boolean isSpringSecurityPresent() {
        return usingSpringSecurity;
    }

    static boolean isLegacySpringSecurityPresent() {
        return usingLegacy;
    }
    static boolean isSpringResourceServerPresent() { return usingSpringResourceServer; }


}

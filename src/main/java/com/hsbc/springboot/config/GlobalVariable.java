package com.hsbc.springboot.config;

/**
 * 〈功能概述〉<br>
 *
 * @className: GlobalVariable
 * @package: com.hsbc.springboot.config
 * @author: bruce
 * @date: 2025/1/25 7:28
 */
public class GlobalVariable {

    public static final String RULE_HASH = "rulesHash:rules";
    public static final int SYSTEM_PROPERTY_PARALLEL = Math.max(2, Runtime.getRuntime().availableProcessors());


}

//package com.example.demo.logTraceId.ttlMdc;
//
//import com.sun.javafx.collections.MappingChange;
//import org.slf4j.MDC;
//import org.slf4j.spi.MDCAdapter;
//import com.alibaba.ttl.TransmittableThreadLocal;
//
///**
// * @Description: --------------------------------------
// * @ClassName: TtlMdcAdapter.java
// * @Date: 2022/5/3 0003  19:47
// * @SoftWare: IntelliJ IDEA
// * --------------------------------------
// * @Author: lixj
// * @Contact: lixj_zj@163.com
// **/
//public class TtlMDCAdapter implements MDCAdapter {
//    /**
//     * 此处是关键
//     */
//    private final ThreadLocal<MappingChange.Map<String, String>> copyOnInheritThreadLocal = new TransmittableThreadLocal<>();
//
//    private static TtlMDCAdapter mtcMDCAdapter;
//
//    static {
//        mtcMDCAdapter = new TtlMDCAdapter();
//        MDC.mdcAdapter = mtcMDCAdapter;
//    }
//
//    public static MDCAdapter getInstance() {
//        return mtcMDCAdapter;
//    }
//}
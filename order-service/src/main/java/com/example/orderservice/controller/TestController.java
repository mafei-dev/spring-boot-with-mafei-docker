package com.example.orderservice.controller;

import com.netflix.appinfo.ApplicationInfoManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementServerProperties;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/trace")
@Slf4j
public class TestController {

    private final ServiceA serviceA;

    private final ManagementServerProperties managementServerProperties;
    private final ApplicationInfoManager applicationInfoManager;

    @GetMapping
    public Object traceTest() {
//        Map<String, String> metadata = applicationInfoManager.getInfo().getMetadata();
        Map<String, String> metadata = ApplicationInfoManager.getInstance().getInfo().getMetadata();
        metadata.put(LocalDateTime.now().toString(), LocalDateTime.now().toString());
//        applicationInfoManager.refreshLeaseInfoIfRequired();
        log.debug("traceTest");
        return "managementServerProperties.getPort()";
    }


}

@Component
@Slf4j
@AllArgsConstructor
class ServiceA {
    private final ServiceB serviceB;
    private final Tracer tracer;

    @NewSpan("ServiceA_method1")
    public void method1() {
        this.tracer.currentSpanCustomizer()
                .tag("tag:1", LocalDate.now().toString())
                .tag("tag:2", "added by mafei");

        log.debug("ServiceA:method1");
        serviceB.method1();
    }

}

@Component
@Slf4j
@AllArgsConstructor
class ServiceB {

    private final Tracer tracer;

    public void method1() {
        log.debug("ServiceB:method1");

        Span span = this.tracer.spanBuilder()
                .name("ServiceB:method1")
                .tag("tag:1", LocalDateTime.now().toString())
                .tag("tag:2", "added by mafei")
                .setParent(this.tracer.currentTraceContext().context())
                .start();

        try {
            throw new RuntimeException("dummy exception.");
        } catch (Exception exception) {
            exception.printStackTrace();
            span.error(exception);
        } finally {
            span.end();
        }
    }
}





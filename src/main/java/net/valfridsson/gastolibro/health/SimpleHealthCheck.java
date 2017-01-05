package net.valfridsson.gastolibro.health;

import com.codahale.metrics.health.HealthCheck;

public class SimpleHealthCheck  extends HealthCheck{
    protected Result check() throws Exception {
        return Result.healthy();
    }
}

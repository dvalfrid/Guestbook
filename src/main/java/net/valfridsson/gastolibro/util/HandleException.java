package net.valfridsson.gastolibro.util;

import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;

import javax.ws.rs.core.Response;

import static org.slf4j.LoggerFactory.getLogger;

@FunctionalInterface
public interface HandleException<A> {
    Logger LOGGER = getLogger(HandleException.class);

    A handle() throws Throwable;

    static Response logException(HandleException<Response> call, String logMessage) {
        try {
            LOGGER.info(logMessage);
            return call.handle();
        } catch (Throwable e) {
            LOGGER.error(e.getMessage(), e);
            return Response.serverError().entity(ImmutableMap.of(
                "type", "error",
                "text", e.getMessage())).build();
        }
    }
}

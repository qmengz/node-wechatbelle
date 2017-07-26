package com.shuyun.wechatbelle.node.resource;

import com.shuyun.wechatbelle.node.domain.Guide;
import com.shuyun.wechatbelle.node.service.GuideService;
import com.shuyun.wechatbelle.node.vo.GuideListJson;
import com.fasterxml.jackson.core.type.TypeReference;
import com.shuyun.motor.client.MotorClients;
import com.shuyun.motor.client.MotorResource;
import com.shuyun.motor.client.MotorResourceGroup;
import com.shuyun.motor.client.http.HttpResponse;
import com.shuyun.support.common.serialization.jackson.Jacksons;
import com.shuyun.support.web.req.ParamName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yangtao on 15/7/7.
 */
@Component
@Path("/guide")
public class GuideResource {
    private static Logger logger = LoggerFactory.getLogger(GuideResource.class);
    private static final AtomicInteger index = new AtomicInteger(0);
    private static String bigData;
    private static int bigSize = 524288;

    static {
        char[] cs = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
                'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
                'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8',
                '9'};
        Random r = new Random(System.currentTimeMillis());
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < bigSize; i++) {
            s.append(cs[r.nextInt(cs.length)]);
        }
        bigData = s.toString();
    }
    @Context
    HttpServletRequest httpServletRequest;

    @Autowired
    private GuideService guideService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Guide findById(@PathParam("id") Long id) {
        return guideService.findById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public GuideListJson findAll() {
        GuideListJson guideListJson = new GuideListJson();
        guideListJson.setRequest(new Date());
        guideListJson.setData(guideService.findAll());
        guideListJson.setResponse(new Date());
        return guideListJson;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Guide add(Guide guide) {
        guideService.add(guide);
        return guide;
    }

    @GET
    @Path("p")
    public Response proxy(
            @QueryParam("cServiceName")
            String cServiceName,
            @QueryParam("cApiVersion")
            String cApiVersion,
            @QueryParam("cSandbox")
            String cSandbox,
            @QueryParam("cEnvironment")
            String cEnvironment,
            @QueryParam("serverAppName")
            String serverAppName,
            @QueryParam("apiVersion")
            String apiVersion,
            @QueryParam("serverUrls")
            String serverUrls,
            @QueryParam("connectionTimeout")
            Integer connectionTimeout,
            @QueryParam("readTimeout")
            Integer readTimeout,
            @QueryParam("path")
            String path,
            @QueryParam("method")
            String method,
            @QueryParam("minUsedTime")
            @DefaultValue("0")
            Integer minUsedTime,
            @QueryParam("maxUsedTime")
            @DefaultValue("0")
            Integer maxUsedTime,
            @QueryParam("extParam")
            String extParam,
            @QueryParam("gzip")
            @DefaultValue("true")
            Boolean gzip) {
        try {
            MotorResourceGroup resourceGroup = MotorClients.resourceGroup(() -> {
                String sn = serverAppName;
                if (null != cEnvironment) {
                    sn += cEnvironment;
                }
                if (null != cSandbox) {
                    sn += cSandbox;
                }
                if (null != apiVersion) {
                    sn += apiVersion;
                }
                return sn;
            }, (creator) -> creator.resourceGroup(() -> serverAppName, (options) -> {
                if (null == serverUrls || serverUrls.isEmpty()) {
                    if (null != connectionTimeout && null != readTimeout) {
                        options.withConnectionTimeout(connectionTimeout).withReadTimeout(readTimeout);
                    }
                    options.withServiceName(serverAppName);
                } else {
                    if (null != connectionTimeout && null != readTimeout) {
                        options.withConnectionTimeout(connectionTimeout).withReadTimeout(readTimeout);
                    }
                    options.withServers(serverUrls);
                }
                if (null != cEnvironment) {
                    options.withEnvironment(cEnvironment);
                }
                if (null != cSandbox) {
                    options.withSandbox(cSandbox);
                }
                if (null != apiVersion) {
                    options.withApiVersion(apiVersion);
                }
            }));
            MotorResource resource = resourceGroup.resource(path);
            if (null != cServiceName) {
                resource.header(ParamName.CALLER_SERVICE.getHeaderName(), null);
                resource.header(ParamName.CALLER_SERVICE.getHeaderName(), cServiceName);
            }
            if (null != cApiVersion) {
                resource.header(ParamName.CALLER_VERSION.getHeaderName(), null);
                resource.header(ParamName.CALLER_VERSION.getHeaderName(), cApiVersion);
            }
            if (null != cSandbox) {
                resource.header(ParamName.CALLER_SANDBOX.getHeaderName(), null);
                resource.header(ParamName.CALLER_SANDBOX.getHeaderName(), cSandbox);
            }
            if (null != cEnvironment) {
                resource.header(ParamName.CALLER_ENVIRONMENT.getHeaderName(), null);
                resource.header(ParamName.CALLER_ENVIRONMENT.getHeaderName(), cEnvironment);
            }
            resource.param("minUsedTime", minUsedTime);
            resource.param("maxUsedTime", maxUsedTime);
            if (null != extParam) {
                Map<String, Object> params = Jacksons.defaultJacksons()
                        .toObject(extParam, new TypeReference<Map<String, Object>>() {}.getType());
                for (Map.Entry<String, Object> kv : params.entrySet()) {
                    if (kv.getValue() instanceof String) {
                        resource.param(kv.getKey(), kv.getValue());
                    } else {
                        resource.param(kv.getKey(), Jacksons.defaultJacksons().toString(kv.getValue()));
                    }
                }
            }
            if (!gzip) {
                resource.header(HttpHeaders.ACCEPT_ENCODING, null);
            }
            logger.debug("motor client request...");
            long st = System.currentTimeMillis();
            HttpResponse response = resource.request(method).execute(HttpResponse.class);
            logger.debug("motor client response...");
            logger.debug("motor client response get start...");
            String content = serverInfo() + "," + cSandbox + "," + cEnvironment + " -> " + response.getBodyString();
            long et = System.currentTimeMillis();
            logger.debug("motor client response get finish...");
            logger.debug("motor client total time " + (et - st));
            return Response.status(response.getStatus()).entity(content).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity(serverInfo() + " fail").build();
        }
    }

    @GET
    @Path("t")
    public Response target(
            @QueryParam("minUsedTime")
            @DefaultValue("0")
            Integer minUsedTime,
            @QueryParam("maxUsedTime")
            @DefaultValue("0")
            Integer maxUsedTime) {
        maxUsedTime = maxUsedTime - minUsedTime;
        if (maxUsedTime > 0) {
            Integer usedTime = new Random(System.currentTimeMillis()).nextInt(maxUsedTime) + minUsedTime;
            if (usedTime > 0) {
                try {
                    Thread.sleep(usedTime);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return Response.ok(serverInfo() + " success").build();
    }

    @GET
    @Path("s")
    public Response server() {
        return Response.ok(serverInfo()).build();
    }

    @GET
    @Path("h")
    public Response hello() {
        return Response.ok("hello world !").build();
    }

    @POST
    @Path("h")
    public Response world() {
        return Response.ok("hello world !").build();
    }

    @GET
    @Path("b")
    @Produces(MediaType.APPLICATION_JSON)
    public Response bigdata(
            @QueryParam("size")
            @DefaultValue("1024")
            Integer size) {
        logger.debug("gen big data start");
        String s;
        if (size < bigSize) {
            s = bigData.substring(0, size);
        } else if (size == bigSize) {
            s = bigData;
        } else {
            int c = size / bigSize;
            size = size % bigSize;
            StringBuilder sb = new StringBuilder(bigData);
            for (int i = 0; i < c - 1; i++) {
                sb.append(bigData);
            }
            sb.append(bigData.substring(0, size));
            s = sb.toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append('{').append("\"length\":\"" + bigSize + "\",\"content\":\"").append(s).append("\"}");
        Response resp = Response.status(200).entity(sb.toString()).build();
        logger.debug("gen big data finish");
        return resp;
    }

    @GET
    @Path("e")
    public Response error(
            @QueryParam("status")
            @DefaultValue("500")
            int status) {
        return Response.status(status).build();
    }

    private String serverInfo() {
        StringBuilder s = new StringBuilder();
        s.append(httpServletRequest.getLocalAddr()).append(':').append(httpServletRequest.getLocalPort()).append('/')
                .append(httpServletRequest.getRemoteAddr()).append(':').append(httpServletRequest.getRemotePort())
                .append('/').append(index.incrementAndGet());
        return s.toString();
    }
}

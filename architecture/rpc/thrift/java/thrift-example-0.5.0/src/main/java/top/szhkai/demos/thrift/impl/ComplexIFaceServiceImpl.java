package top.szhkai.demos.thrift.impl;

import com.google.gson.Gson;
import top.szhkai.demos.thrift.user.Request;
import top.szhkai.demos.thrift.user.Response;
import top.szhkai.demos.thrift.user.ComplexIFaceService;
import top.szhkai.demos.thrift.user.Wrapper;

import java.util.HashMap;
import java.util.Map;

public class ComplexIFaceServiceImpl implements ComplexIFaceService.Iface {
    static final Gson gson = new Gson();

    @Override
    public Response get(Request request) {
        Map<String, Wrapper> ms = new HashMap<>();
        for (int i = 0; i < request.getWsSize(); ++i) {
            ms.put(String.valueOf(i), request.ws.get(i));
        }
        return new Response().setMs(ms).setEcho(request.getWs()).setJsonEcho(gson.toJson(request));
    }
}

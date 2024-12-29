package com.hf.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//code by ChatGPT
@WebFilter("/surrender")  // 限制 '/surrender' 路径的请求
public class RateLimitFilter implements Filter {

    // 用来记录 IP 地址请求数据，存储 IP 和请求的时间戳
    private Map<String, UserRequestData> requestDataMap = new HashMap<>();
    private static final int MAX_REQUESTS_PER_MINUTE = 3; // 最大请求次数
    private static final long TIME_WINDOW_MS = 60 * 1000; // 时间窗口：60秒

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化方法（如果需要）
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 获取客户端的 IP 地址
        String ipAddress = httpRequest.getRemoteAddr();
        long currentTime = System.currentTimeMillis();

        // 清理过期的 IP 数据
        cleanUpExpiredData(currentTime);

        // 获取该 IP 地址的请求数据
        UserRequestData userRequestData = requestDataMap.get(ipAddress);

        // 如果没有记录该 IP 地址的数据，则初始化
        if (userRequestData == null) {
            userRequestData = new UserRequestData(currentTime, 1);
            requestDataMap.put(ipAddress, userRequestData);
        } else {
            // 如果请求在 60 秒的时间窗口内
            if (currentTime - userRequestData.getFirstRequestTime() <= TIME_WINDOW_MS) {
                // 请求次数加一
                userRequestData.setRequestCount(userRequestData.getRequestCount() + 1);
            } else {
                // 超过时间窗口，重置计数器
                userRequestData.setFirstRequestTime(currentTime);
                userRequestData.setRequestCount(1);
            }
        }

        // 如果请求次数超过最大限制，返回 429 错误
        if (userRequestData.getRequestCount() > MAX_REQUESTS_PER_MINUTE) {
            httpResponse.setStatus(429);  // HTTP 429 Too Many Requests
            httpResponse.getWriter().print(429);
            return;  // 直接返回，不继续执行后续过滤器或 Servlet
        }

        // 否则，继续执行请求链，允许请求进入 Servlet
        chain.doFilter(request, response);
    }

    // 清理过期的 IP 数据
    private void cleanUpExpiredData(long currentTime) {
        Iterator<Map.Entry<String, UserRequestData>> iterator = requestDataMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, UserRequestData> entry = iterator.next();
            UserRequestData userRequestData = entry.getValue();

            // 如果该请求数据已经过期（超出时间窗口），则从 Map 中删除
            if (currentTime - userRequestData.getFirstRequestTime() > TIME_WINDOW_MS) {
                iterator.remove();  // 删除过期的数据
            }
        }
    }

    @Override
    public void destroy() {
        // 销毁方法（如果需要）
    }

    // 用来存储用户请求的数据
    private static class UserRequestData {
        private long firstRequestTime;  // 第一次请求的时间戳
        private int requestCount;       // 当前时间窗口内的请求次数

        public UserRequestData(long firstRequestTime, int requestCount) {
            this.firstRequestTime = firstRequestTime;
            this.requestCount = requestCount;
        }

        public long getFirstRequestTime() {
            return firstRequestTime;
        }

        public void setFirstRequestTime(long firstRequestTime) {
            this.firstRequestTime = firstRequestTime;
        }

        public int getRequestCount() {
            return requestCount;
        }

        public void setRequestCount(int requestCount) {
            this.requestCount = requestCount;
        }
    }
}

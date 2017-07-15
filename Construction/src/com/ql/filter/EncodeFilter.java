package com.ql.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

@WebFilter("/*")
public class EncodeFilter implements Filter{
	private FilterConfig config =  null;
	private String encode = "UTF-8";
	@Override
	public void destroy() {
		config = null;
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(encode);
		response.setCharacterEncoding(encode);
		response.setContentType("text/html;charset='"+encode+"'");
		chain.doFilter(request,response);
	}
	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
        String charset = config.getServletContext().getInitParameter("charset");    
        if( charset != null && charset.trim().length() != 0)  
        {  
            this.encode = charset;  
        }  
		
	}
	/*class myHttpservletRequest extends HttpServletRequestWrapper{
		private boolean flag = true;
		private HttpServletRequest request = null;
		public myHttpservletRequest(HttpServletRequest request) {
			super(request);
			this.request = request;
		}
		@Override
		public Map<String, String[]> getParameterMap() {
			try{
				if(request.getMethod().equalsIgnoreCase("post")){
					request.setCharacterEncoding(encode);
					return request.getParameterMap();
				}
				else if(request.getMethod().equalsIgnoreCase("get")){
					Map<String,String[]> map = request.getParameterMap();
					if(flag){
					for(Map.Entry<String, String[]> entry:map.entrySet()){
						String[] ms = entry.getValue();
						for (int i = 0; i < ms.length; i++) {
						    ms[i] =new String(ms[i].getBytes("iso8859-1"),encode);
						}
						flag = false;
					}}
					return map;
				}
				else{
					return request.getParameterMap();
				}
			}catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
		}
		@Override
		public String[] getParameterValues(String name) {
			return getParameterMap().get(name);		}
		@Override
		public String getParameter(String name) {
			return  getParameterValues(name)==null?null:getParameterValues(name)[0];
		}
	}*/
}

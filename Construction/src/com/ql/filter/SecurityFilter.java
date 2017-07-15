package com.ql.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;;

@WebFilter("/*")
public class SecurityFilter implements Filter {
	private static final Logger log = Logger.getLogger(SecurityFilter.class);

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		log.debug("uri:" + uri);
		if ((null == req.getSession().getAttribute("user") && null == req.getSession().getAttribute("username"))
				&& null != uri) {
			if (uri.contains("UserManage") || uri.contains("usermanage")) {
				if ("/Construction/UserManage/".equals(uri) || "/Construction/UserManage".equals(uri)
						|| "/Construction/UserManage/AdminServlet/".equals(uri)
						|| "/Construction/UserManage/AdminServlet".equals(uri)
						|| "/Construction/UserManage/js/jquery-3.1.1.min.js".equals(uri)
						|| "/Construction/usermanage/show/Login_show/css/bootstrap.min.css".equals(uri)
						|| "/Construction/usermanage/show/Login_show/css/font-awesome.min.css".equals(uri)
						|| "/Construction/usermanage/show/Login_show/load.css".equals(uri)
						||"/Construction/usermanage/show/Login_show/images/bg_1.jpg".equals(uri)) {
					chain.doFilter(req, res);
					log.debug("UserManage 1");
					return;
				} else {
					res.sendRedirect(req.getContextPath() + "/UserManage");
					log.debug("UserManage 2");
					return;
				}
			} else {
				if ("/Construction/".equals(uri) || "/Construction/css/bootstrap.min.css".equals(uri)
						|| "/Construction/css/style.css".equals(uri)
						|| "/Construction/css/font-awesome.min.css".equals(uri)
						|| "/Construction/images/login/login-backimage.jpg".equals(uri)
						|| "/Construction/fonts/fontawesome-webfont.woff2".equals(uri)
						|| "/Construction/fonts/fontawesome-webfont.ttf".equals(uri)
						|| "/Construction/fonts/fontawesome-webfont.woff".equals(uri)
						|| "/Construction/fonts/fontawesome-webfont.woff2".equals(uri)
						|| "/Construction/user".equals(uri)) {
					log.debug("Construction 1");
					chain.doFilter(req, res);
					return;
				} else {
					log.debug("Construction 2");
					res.sendRedirect(req.getContextPath());
					return;
				}
			}
		} else if ((null != req.getSession().getAttribute("user") && null == req.getSession().getAttribute("username"))
				&& null != uri) {
			if (uri.contains("UserManage") || uri.contains("usermanage")) {
				if ("/Construction/UserManage/".equals(uri) || "/Construction/UserManage/AdminServlet".equals(uri)
						|| "/Construction/UserManage/js/jquery-3.1.1.min.js".equals(uri)
						|| "/Construction/usermanage/show/Login_show/css/bootstrap.min.css".equals(uri)
						|| "/Construction/usermanage/show/Login_show/css/font-awesome.min.css".equals(uri)
						|| "/Construction/usermanage/show/Login_show/load.css".equals(uri)
						||"/Construction/usermanage/show/Login_show/images/bg_1.jpg".equals(uri)) {
					chain.doFilter(req, res);
					log.debug("UserManage 3");
					return;
				} else {
					res.sendRedirect(req.getContextPath() + "/UserManage");
					log.debug("UserManage 4");
					return;
				}
			} else {
				chain.doFilter(request, response);
				return;
			}
		} else if ((null == req.getSession().getAttribute("user") && null != req.getSession().getAttribute("username"))
				&& null != uri) {
			if (uri.contains("UserManage") || uri.contains("usermanage")) {
				chain.doFilter(req, res);
				return;
			} else {
				if ("/Construction/".equals(uri) || "/Construction/css/bootstrap.min.css".equals(uri)
						|| "/Construction/css/style.css".equals(uri)
						|| "/Construction/css/font-awesome.min.css".equals(uri)
						|| "/Construction/images/login/login-backimage.jpg".equals(uri)
						|| "/Construction/fonts/fontawesome-webfont.woff2".equals(uri)
						|| "/Construction/fonts/fontawesome-webfont.ttf".equals(uri)
						|| "/Construction/fonts/fontawesome-webfont.woff".equals(uri)
						|| "/Construction/fonts/fontawesome-webfont.woff2".equals(uri)
						|| "/Construction/user".equals(uri)) {
					log.debug("Construction 1");
					chain.doFilter(req, res);
					return;
				} else {
					log.debug("Construction 2");
					res.sendRedirect(req.getContextPath());
					return;
				}
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}

package cn.main.tool;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;

public class CommonsMultipartResolverPhhc extends CommonsMultipartResolver {
    @Override
    public boolean isMultipart(HttpServletRequest request) {
        String url = request.getRequestURI();
        if (url != null && url.contains("ueditor/jsp/ueditorUploadImage")) {
            return false;
        } else {
            return super.isMultipart(request);
        }
    }
}

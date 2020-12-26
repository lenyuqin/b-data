package com.site.web.service.impl;

import com.site.web.entity.Logging;
import com.site.web.plugins.logging.enums.LoggingType;
import com.site.web.plugins.logging.enums.RequestMethod;
import com.site.web.mapper.LoggingMapper;
import com.site.web.service.LoggingService;
import com.site.web.utils.security.SecurityUtil;
import com.site.web.utils.servlet.ServletUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Describe: 日 志 服 务 接 口 实 现
 * @author lenyuqin
 * */
@Service
public class LoggingServiceImpl implements LoggingService {

    @Resource
    private LoggingMapper loggingMapper;

    @Override
    public boolean save(Logging logging) {
        logging.setOperateAddress(ServletUtil.getRemoteHost());
        logging.setMethod(ServletUtil.getRequestURI());
        logging.setCreateTime(LocalDateTime.now());
        logging.setRequestMethod(RequestMethod.valueOf(ServletUtil.getMethod()));
        logging.setOperateUrl(ServletUtil.getRequestURI());
        logging.setBrowser(ServletUtil.getBrowser());
        logging.setRequestBody(ServletUtil.getQueryParam());
        logging.setSystemOs(ServletUtil.getSystem());
        logging.setOperateName(null != SecurityUtil.currentUser() ? Objects.requireNonNull(SecurityUtil.currentUser()).getName() : "未登录用户");
        int result = loggingMapper.insert(logging);
        return result > 0;
    }

    @Override
    public List<Logging> data(LoggingType loggingType) {
        return loggingMapper.selectList(loggingType);
    }

    @Override
    public Logging getById(String id) {
        return loggingMapper.getById(id);
    }
}

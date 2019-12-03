package com.tujietg.template.service.impl;

import com.tujietg.template.common.utils.JwtTokenUtil;
import com.tujietg.template.mbg.mapper.UmsAdminMapper;
import com.tujietg.template.mbg.model.UmsAdmin;
import com.tujietg.template.mbg.model.UmsAdminExample;
import com.tujietg.template.mbg.model.UmsPermission;
import com.tujietg.template.service.UmsAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author tujietg
 * @date 2019/12/3 8:50 PM
 */
@Service
public class UmsAdminServiceImpl implements UmsAdminService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminServiceImpl.class);

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private UmsAdminMapper adminMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    JwtTokenUtil jwtTokenUtil;


    @Override
    public UmsAdmin getAdminByUsername(String username) {
        List<UmsAdmin> adminList = getAdminListByUsername(username);

        if (adminList != null && adminList.size() > 0) {
            return adminList.get(0);
        }
        return null;
    }

    @Override
    public UmsAdmin register(UmsAdmin umsAdminParam) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminParam, umsAdmin);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);
        List<UmsAdmin> adminList = getAdminListByUsername(umsAdmin.getUsername());
        if (adminList.size() > 0) {
            return null;
        }
        String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(encodePassword);
        adminMapper.insert(umsAdmin);
        return umsAdmin;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    @Override
    public List<UmsPermission> getPermissionList(Long adminId) {
        return null;
    }


    /**
     * 根据username获取管理员列表
     *
     * @param username
     * @return
     */
    private List<UmsAdmin> getAdminListByUsername(String username) {
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> adminList = adminMapper.selectByExample(example);
        return adminList;
    }
}

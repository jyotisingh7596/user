package com.jyotisingh.user.service;

import com.jyotisingh.user.entity.User;
import com.jyotisingh.user.repository.UserRepository;
import com.jyotisingh.user.vo.Department;
import com.jyotisingh.user.vo.ResponseTemplateVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public User saveUser(User user) {
        log.info("Inside saveUser method of UserService");
        return userRepository.save(user);
    }

    public ResponseTemplateVo getUserWithDepartment(Long userId) {
        log.info("Inside  getUserWithDepartment method of UserService");

        ResponseTemplateVo responseTemplateVo = new ResponseTemplateVo();
        User user = userRepository.findByUserId(userId);

        Department department = restTemplate.getForObject(
                "http://localhost:9001/departments/" + user.getDepartmentId(),
                Department.class);

        responseTemplateVo.setDepartment(department);
        responseTemplateVo.setUser(user);

        return responseTemplateVo;
    }
}

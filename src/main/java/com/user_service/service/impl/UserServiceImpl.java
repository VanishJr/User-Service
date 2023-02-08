package com.user_service.service.impl;

import com.user_service.dto.UserRequest;
import com.user_service.model.Address;
import com.user_service.model.Name;
import com.user_service.model.User;
import com.user_service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(UserRequest userRequest) {
        log.info("Create user {} start", userRequest.userName());
        Address address = Address.builder()
                .no(userRequest.no())
                .firstLine(userRequest.firstLine())
                .secondLine(userRequest.secondLine())
                .zipCode(userRequest.zipCode())
                .province(userRequest.province())
                .mobileNo(userRequest.mobileNo())
                .build();

        Name name =  Name.builder()
                .firstName(userRequest.firstName())
                .lastName(userRequest.lastName())
                .middleName(userRequest.middleName())
                .build();
        return userRepository.save(
                User.builder()
                        .password(userRequest.password())
                        .email(userRequest.email())
                        .userName(userRequest.userName())
                        .address(address)
                        .name(name)
                        .build());
    }

    @Override
    public User getUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            return userOptional.get();
        }else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User" +userId+ "not found");
    }

    @Override
    public User updateUser(Long userId, UserRequest userRequest) {
        log.info("Update user {} start", userRequest.userName());
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            User updatableUser = userOptional.get();
            updatableUser.setUserName(userRequest.userName());
            updatableUser.setPassword(userRequest.password());
            updatableUser.setEmail(userRequest.email());

            Address updatableAddress = updatableUser.getAddress();
            updatableAddress.setNo(userRequest.no());
            updatableAddress.setFirstLine(userRequest.firstLine());
            updatableAddress.setSecondLine(userRequest.secondLine());
            updatableAddress.setProvince(userRequest.province());
            updatableAddress.setZipCode(userRequest.zipCode());
            updatableAddress.setMobileNo(userRequest.mobileNo());
            updatableUser.setAddress(updatableAddress);

            Name updatableName = updatableUser.getName();
            updatableName.setFirstName(userRequest.firstName());
            updatableName.setLastName(userRequest.lastName());
            updatableName.setMiddleName(userRequest.middleName());
            updatableUser.setName(updatableName);
            return userRepository.save(updatableUser);
        }else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User" +userId+ "not found to Update");

    }

    @Override
    public void deleteUser(Long userId) {
        log.info("Delete user {} start", userId);
        userRepository.deleteById(userId);
    }
}

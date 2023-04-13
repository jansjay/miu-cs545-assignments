package edu.miu.cs545.spring.services;

import edu.miu.cs545.spring.aspect.ExecutionTime;
import edu.miu.cs545.spring.dto.PostDto;
import edu.miu.cs545.spring.dto.UserDto;
import edu.miu.cs545.spring.models.User;
import edu.miu.cs545.spring.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostService postService;

    @Override
    @ExecutionTime
    public UserDto getById(Long id) {
        return getUserDto(userRepository.findById(id).orElse(null));
    }

    @Autowired
    ModelMapper modelMapper;

    @Override
    public UserDto add(UserDto userDto) {
        return getUserDto(userRepository.save(getUser(userDto)));
    }

    @Override
    public UserDto update(UserDto userDto) {
        return getUserDto(userRepository.save(getUser(userDto)));
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Collection<UserDto> getAll() {
        Collection<UserDto> userDto = new ArrayList<>();
        userRepository.findAll().forEach(x->userDto.add(getUserDto(x)));
        return userDto;
    }

    @Override
    public Collection<PostDto> getUserPostsAll(Long id) { return postService.findPostsByUserId(id); }

    @Override
    public Collection<UserDto> getUsersWithPostsTitle(String title) {
        Collection<UserDto> userDto = new ArrayList<>();
        userRepository.findByPostsTitleIgnoreCase(title).forEach(x->userDto.add(getUserDto(x)));
        return userDto;
    }

    @Override
    public Collection<UserDto> getUsersWithNoOfPostsGreaterThan(Long count) {
        Collection<UserDto> userDto = new ArrayList<>();
        userRepository.getUsersWithNoOfPostsGreaterThan(count).forEach(x->userDto.add(getUserDto(x)));
        return userDto;
    }

    private UserDto getUserDto(User user){
        return modelMapper.map(user, UserDto.class);
    }

    private User getUser(UserDto userDto){
        return modelMapper.map(userDto, User.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByName(username).orElse(null);
    }
}

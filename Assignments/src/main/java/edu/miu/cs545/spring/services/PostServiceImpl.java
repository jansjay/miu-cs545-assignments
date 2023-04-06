package edu.miu.cs545.spring.services;

import edu.miu.cs545.spring.dto.PostDto;
import edu.miu.cs545.spring.models.Post;
import edu.miu.cs545.spring.models.User;
import edu.miu.cs545.spring.repositories.PostRepository;
import edu.miu.cs545.spring.repositories.PostV2Repository;
import edu.miu.cs545.spring.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostV2Repository postV2Repository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public PostDto getById(Long id) {
        return getPostDto(postRepository.findById(id).orElse(null));
    }

    @Override
    public Collection<PostDto> getByAuthor(String author) {
        return postV2Repository.getByAuthor(author);
    }

    @Override
    public PostDto add(PostDto post) {
        return getPostDto(saveUserAndGetPost(post));
    }

    @Override
    public PostDto update(PostDto post) {
        return getPostDto(saveUserAndGetPost(post));
    }

    @Override
    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Collection<PostDto> getAll() {
        Collection<PostDto> posts = new ArrayList<>();
        postRepository.findAll().forEach(x-> posts.add(getPostDto(x)));
        return posts;
    }
    @Override
    public Collection<PostDto> findPostsByUserId(Long userId){
        Collection<PostDto> posts = new ArrayList<>();
        postRepository.findPostsByUserId(userId).forEach(x-> posts.add(getPostDto(x)));
        return posts;
    }
    @Override
    public Collection<PostDto> findPostsByUserIdPostId(Long userId, Long postId){
        Collection<PostDto> posts = new ArrayList<>();
        postRepository.findPostsByUserIdPostId(userId, postId).forEach(x-> posts.add(getPostDto(x)));
        return posts;
    }

    @Override
    public Collection<PostDto> getPostsWithTitle(String title) {
        Collection<PostDto> postDto = new ArrayList<>();
        postRepository.findByTitleIgnoreCase(title).forEach(x->postDto.add(getPostDto(x)));
        return postDto;
    }

    private PostDto getPostDto(Post post){
        PostDto dto = modelMapper.map(post, PostDto.class);
        if(post != null){
            dto.setUserId(postRepository.getUserId(post.getId()));
        }
        return dto;
    }
    private Post saveUserAndGetPost(PostDto postDto){
        //Note: We have to do all these additional processing due to the
        //      assignment2 requirement of having unidirectional relationship
        Post post = modelMapper.map(postDto, Post.class);
        if(post != null){
            User user = userRepository.findById(postDto.getUserId()).orElse(null);
            if(user == null){
                throw new EntityNotFoundException("User not found");
            }
            if(user.getPosts().stream().noneMatch(x-> x.getId().equals(post.getId()))){
                user.getPosts().add(post);
                userRepository.save(user);
                return postRepository.findById(Objects.requireNonNull(user.getPosts().stream().max(Comparator.comparing(Post::getId)).orElse(null)).getId()).orElse(null);
            }
            else {
                Post existingPost = postRepository.findById(post.getId()).orElse(null);
                assert existingPost != null;
                existingPost.setContent(post.getContent());
                existingPost.setTitle(post.getTitle());
                postRepository.save(existingPost);
                return existingPost;
            }
        }
        return null;
    }
}


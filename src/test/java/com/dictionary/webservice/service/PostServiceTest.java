package com.dictionary.webservice.service;

import com.dictionary.webservice.domain.posts.Posts;
import com.dictionary.webservice.domain.posts.PostsRepository;
import com.dictionary.webservice.domain.posts.PostsSaveRequestDto;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest {

    @Autowired
    private PostsService postsService;

    @Autowired
    private PostsRepository postsRepsitory;

    @After
    public void cleanup(){
        postsRepsitory.deleteAll();
    }

    @Test
    public void Dto데이터가_posts테이블에_저장된다(){
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder().title("테스트 테이블").content("테스트").author("cyr9210@nate.com").build();

        postsService.save(dto);

        Posts posts = postsRepsitory.findAll().get(0);
        Assert.assertThat(posts.getAuthor(), Matchers.is(dto.getAuthor()));
        Assert.assertThat(posts.getContent(), Matchers.is(dto.getContent()));
        Assert.assertThat(posts.getTitle(), Matchers.is(dto.getTitle()));
    }
}

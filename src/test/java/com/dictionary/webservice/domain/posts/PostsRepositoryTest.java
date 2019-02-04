package com.dictionary.webservice.domain.posts;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepsitory;

    @After
    public void cleanup(){
        postsRepsitory.deleteAll();
    }

    @Test
    public void 게시글_저장_불러오기(){
        LocalDateTime now = LocalDateTime.now();

        postsRepsitory.save(Posts.builder().title("테스트 게시글").content("테스트 본문").author("cyr9210@ante.com").build());

        List<Posts> postsList = postsRepsitory.findAll();

        Posts posts = postsList.get(0);
//        Assert.assertThat(posts.getTitle(), Matchers.is("테스트 게시글"));
//        Assert.assertThat(posts.getContent(), Matchers.is("테스트 본문"));
        Assert.assertTrue(posts.getCreatedDate().isAfter(now));
        Assert.assertTrue(posts.getModifiedDate().isAfter(now));

    }
}

package com.example.room.repository;

import com.example.room.entity.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MemberRepositoryTest {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void all(){

    }

    @Test
    public void table() throws Exception {
        String testUserName= "테스트";
        Member member = new Member(testUserName);
        Member save = memberRepository.save(member);
        Optional<Member> memberById = memberRepository.findById(save.getId());
        if(memberById == null){
            throw new Exception("fail");
        }
        if(memberById.get() == null){
            throw new Exception("fail");
        }
        assertEquals(save.getId(), memberById.get().getId());
    }

}
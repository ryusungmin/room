package com.example.room.repository;

import com.example.room.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findById(Long aLong);

    List<Member> findByUsername(String username);
}

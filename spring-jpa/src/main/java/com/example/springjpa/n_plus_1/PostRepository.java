package com.example.springjpa.n_plus_1;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT p" +
            " FROM Post p" +
            " JOIN FETCH p.member m" +
            " JOIN FETCH m.followers f" +
            " WHERE f.fromMember.id = :memberId AND p.id < :lastPostId")
    List<Post> findByFetchJoin(@Param("memberId") Long memberId, @Param("lastPostId") Long lastPostId, Pageable pageable);

    List<Post> findByIdLessThanAndMemberIn(Long lastPostId, List<Member> members, Pageable pageable);

    @Query(value = "SELECT p" +
            " FROM Post p" +
            " JOIN Follow f" +
            " ON p.member.id = f.toMember.id" +
            " WHERE f.fromMember.id = :memberId AND p.id < :lastPostId")
    List<Post> findByJoinFollow(@Param("memberId") Long memberId, @Param("lastPostId") Long lastPostId, Pageable pageable);
}

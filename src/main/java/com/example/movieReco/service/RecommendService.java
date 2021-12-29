package com.example.movieReco.service;

import com.example.movieReco.domain.Member;
import com.example.movieReco.domain.Recommendation;
import com.example.movieReco.repository.MemberRepository;
import com.example.movieReco.repository.RecommendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RecommendService {

    private final RecommendRepository recommendRepository;
    private final MemberRepository memberRepository;

    public Long save(Member member, Recommendation recommendation){
        Member findMember = memberRepository.find(member.getId());
        consumeHeart(findMember, recommendation.getUserHeart());
        Long id = recommendRepository.save(recommendation);
        return id;
    }

    private void consumeHeart(Member member, long heart){
        long mHeart = member.getHeart();
        if(mHeart - heart < 0){
            throw new RuntimeException("Not enough hearts left");
        }
        member.setHeart(mHeart-heart);
    }

    public Recommendation find(Long id){
        return recommendRepository.find(id);
    }

    public Recommendation findRecommendation(Long id){
        return recommendRepository.findRecommendation(id);
    }


}
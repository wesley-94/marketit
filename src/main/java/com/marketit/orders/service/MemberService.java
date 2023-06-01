package com.marketit.orders.service;

import com.marketit.orders.domain.Member;
import com.marketit.orders.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

}

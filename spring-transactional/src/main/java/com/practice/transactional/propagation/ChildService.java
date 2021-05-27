package com.practice.transactional.propagation;

import com.practice.transactional.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
@RequiredArgsConstructor
public class ChildService {

    private final MemberRepository repository;

    @Transactional(propagation = Propagation.REQUIRED)
    public String getRequired(String name) {
        repository.findByName(name).ifPresent(member -> member.setName("Random"));
        return getCurrentTransactionName();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public String getSupports(String name) {
        repository.findByName(name).ifPresent(member -> member.setName("Random"));
        return getCurrentTransactionName();
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public String getMandatory(String name) {
        repository.findByName(name).ifPresent(member -> member.setName("Random"));
        return getCurrentTransactionName();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String getRequiresNew(String name) {
        repository.findByName(name).ifPresent(member -> member.setName("Random"));
        return getCurrentTransactionName();
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public String getNotSupported(String name) {
        repository.findByName(name).ifPresent(member -> member.setName("Random"));
        return getCurrentTransactionName();
    }

    @Transactional(propagation = Propagation.NEVER)
    public String getNever(String name) {
        repository.findByName(name).ifPresent(member -> member.setName("Random"));
        return getCurrentTransactionName();
    }

    @Transactional(propagation = Propagation.NESTED)
    public String getNested(String name) {
        repository.findByName(name).ifPresent(member -> member.setName("Random"));
        return getCurrentTransactionName();
    }

    // 현재 트랜잭션 이름 조회
    private String getCurrentTransactionName() {
        return TransactionSynchronizationManager.getCurrentTransactionName();
    }
}

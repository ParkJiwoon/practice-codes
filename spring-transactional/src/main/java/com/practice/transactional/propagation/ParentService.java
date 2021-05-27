package com.practice.transactional.propagation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ParentService {
    private final ChildService childService;

    @Transactional
    public String callTransaction(Propagation propagation, String name) {
        switch (propagation) {
            case REQUIRED:
                return childService.getRequired(name);
            case SUPPORTS:
                return childService.getSupports(name);
            case MANDATORY:
                return childService.getMandatory(name);
            case REQUIRES_NEW:
                return childService.getRequiresNew(name);
            case NOT_SUPPORTED:
                return childService.getNotSupported(name);
            case NEVER:
                return childService.getNever(name);
            case NESTED:
                return childService.getNested(name);
            default:
                return "Invalid Propagation";
        }
    }

    public String callNoTransaction(Propagation propagation, String name) {
        switch (propagation) {
            case REQUIRED:
                return childService.getRequired(name);
            case SUPPORTS:
                return childService.getSupports(name);
            case MANDATORY:
                return childService.getMandatory(name);
            case REQUIRES_NEW:
                return childService.getRequiresNew(name);
            case NOT_SUPPORTED:
                return childService.getNotSupported(name);
            case NEVER:
                return childService.getNever(name);
            case NESTED:
                return childService.getNested(name);
            default:
                return "Invalid Propagation";
        }
    }
}

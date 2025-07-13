package com.example.Authentication.services;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.Authentication.dtos.RefferalNodeDto;
import com.example.Authentication.entities.UserEntity;
import com.example.Authentication.repositories.RefferalRepositories;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // Spring will inject the repo via constructor
public class Refferalservice {

    private final RefferalRepositories refferalRepositories;

    /* ---------- 4. Hierarchical tree (names only) ---------- */
    public RefferalNodeDto getReferralTree(String rootReferralCode) {
        UserEntity root = refferalRepositories.findByReferralCode(rootReferralCode);
        if (root == null)
            return null;

        // Track visited e‑mails to break cycles
        Set<String> visited = new HashSet<>();
        return buildNameOnlyTree(root, visited);
    }

    /* ---- recursive helper that guards against circular references ---- */
    private RefferalNodeDto buildNameOnlyTree(UserEntity user,
            Set<String> visited) {

        if (!visited.add(user.getEmail())) {
            return null; // already processed → stop recursion
        }

        RefferalNodeDto node = new RefferalNodeDto(user.getName());

        List<RefferalNodeDto> childNodes = refferalRepositories.findByReferredByName(user.getName())
                .stream()
                .map(child -> buildNameOnlyTree(child, visited))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        node.setChildren(childNodes);
        return node;
    }
}

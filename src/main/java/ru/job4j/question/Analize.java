package ru.job4j.question;

import java.util.Set;
import java.util.stream.Collectors;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        Set<Integer> idUsers = current.stream().map(User::getId).collect(Collectors.toSet());
        int deleted = 0;
        int changed = 0;

        for (User u : previous) {
            if (!current.contains(u)) {
                if (idUsers.contains(u.getId())) {
                    changed++;
                } else {
                    deleted++;
                }
            }
        }

        return new Info(current.size() - previous.size() + deleted, changed, deleted);
    }
}
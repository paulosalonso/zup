package com.github.paulosalonso.zup.infrastructure.repository.specification;

public final class LikeValueResolver {

    private LikeValueResolver() {}

    public static String startsWith(String value) {
        return String.format("%s%%", value);
    }

    public static String endsWith(String value) {
        return String.format("%%%s%", value);
    }

    public static String contains(String value) {
        return String.format("%%%s%%", value);
    }
}

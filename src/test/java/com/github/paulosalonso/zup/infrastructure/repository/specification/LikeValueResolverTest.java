package com.github.paulosalonso.zup.infrastructure.repository.specification;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.github.paulosalonso.zup.infrastructure.repository.specification.LikeValueResolver.*;
import static org.assertj.core.api.Assertions.assertThat;

public class LikeValueResolverTest {

    @Test
    public void whenResolveStartsWithThenSuccess() {
        String likeValue = startsWith("value");
        assertThat(likeValue).isEqualTo("value%");
    }

    @Test
    public void whenResolveContainsThenSuccess() {
        String likeValue = contains("value");
        assertThat(likeValue).isEqualTo("%value%");
    }

    @Test
    public void whenResolveEndsWithThenSuccess() {
        String likeValue = endsWith("value");
        assertThat(likeValue).isEqualTo("%value");
    }
}

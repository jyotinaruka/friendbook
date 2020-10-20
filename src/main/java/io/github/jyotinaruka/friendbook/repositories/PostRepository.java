package io.github.jyotinaruka.friendbook.repositories;

import org.springframework.data.repository.CrudRepository;

import io.github.jyotinaruka.friendbook.model.Post;

public interface PostRepository extends CrudRepository<Post, Long>{

}

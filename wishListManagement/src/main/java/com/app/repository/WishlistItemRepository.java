package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.WishlistItem;

public interface WishlistItemRepository extends JpaRepository<WishlistItem, Long> {

}

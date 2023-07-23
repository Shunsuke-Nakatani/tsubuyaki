package jp.kobe_u.cs.daikibo.tsubuyaki.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.kobe_u.cs.daikibo.tsubuyaki.entity.Tsubuyaki;

@Repository

public interface TsubuyakiRepository extends JpaRepository<Tsubuyaki, Long> {
    // カスタムクエリの例
    @Query("SELECT t FROM Tsubuyaki t WHERE t.comment LIKE %:keyword%")
    List<Tsubuyaki> searchTsubuyaki(@Param("keyword") String keyword);
}


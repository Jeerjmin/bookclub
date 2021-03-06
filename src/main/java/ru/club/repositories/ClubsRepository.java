package ru.club.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.club.models.Club;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface ClubsRepository extends JpaRepository<Club, Long> {
    Optional<Club> findOneByTitle(String title);

    Optional<Club> findOneById(Long id);

    Page<Club> findAllByMembers_id(Long id, Pageable pageable);

    Page<Club> findAllByOwner_id(Long id, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM usrs_clubs WHERE user_id = ?1 AND club_id = ?2", nativeQuery = true)
    void deleteMemberByIdAndClubId(Long userId, Long clubId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE club SET title = ?2, description = ?3 WHERE id = ?1", nativeQuery = true)
    void setClubInfoById(Long id, String title, String description);

}

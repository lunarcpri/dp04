package repositories;

import domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;


@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {


    @Query("select m from Message m join m.recipients r where r.id=?1 OR m.sender.id=?1")
    Collection<Message> findAllByActor(int id);
}

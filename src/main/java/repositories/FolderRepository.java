package repositories;

import domain.Actor;
import domain.Folder;
import domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Integer> {

    @Query("select f from Folder f where f.actor.id = ?1 and f.folderType='INBOX'")
    Folder findInboxFolderByActorId(int id);

    @Query("select f from Folder f where f.actor.id = ?1 and f.folderType='SPAMBOX'")
    Folder findSpamboxFolderByActorId(int id);

    @Query("select f from Folder f where f.actor.id = ?1 and f.folderType='OUTBOX'")
    Folder findOutboxFolderByActorId(int id);

    @Query("select f from Folder f where f.actor.id = ?1 and f.folderType='TRASHBOX'")
    Folder findTrashboxFolderByActorId(int id);

    @Query("select f from Folder f where f.actor.id = ?1 and f.folderType='CUSTOM'")
    Collection<Folder> findCustomFoldersByActorId(int id);

    @Query("select f from Folder f join f.messages m where m.id=?2 and f.actor.id=?1")
    Folder findFolderByMessageAndActor(int actorid, int messageid);

    @Query("select f from User u join u.folders f where f.folderType='CUSTOM'")
    Collection<Folder> findAllCustomFolderByActor(int id);
}

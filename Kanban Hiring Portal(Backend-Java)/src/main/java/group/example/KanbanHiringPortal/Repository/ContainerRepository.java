package group.example.KanbanHiringPortal.Repository;

import group.example.KanbanHiringPortal.model.Container;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContainerRepository extends JpaRepository<Container,String> {

}

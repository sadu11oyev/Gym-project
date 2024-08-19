package uz.pdp.gymproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.gymproject.entity.Attachment;

import java.util.UUID;

public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
}
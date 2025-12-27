package com.fyp.system.service;

import com.fyp.system.enums.DocumentStatus;
import com.fyp.system.enums.DocumentType;
import com.fyp.system.model.Deadline;
import com.fyp.system.model.Document;
import com.fyp.system.model.DocumentVersion;
import com.fyp.system.repository.DeadlineRepository;
import com.fyp.system.repository.DocumentRepository;
import com.fyp.system.repository.DocumentVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private DocumentVersionRepository documentVersionRepository;

    @Autowired
    private DeadlineRepository deadlineRepository;

    @Transactional
    public void submitDocument(Long docId) {
        Document document = documentRepository.findById(docId)
                .orElseThrow(() -> new RuntimeException("Document not found"));
        
        document.setLocked(true);
        document.setStatus(DocumentStatus.SUBMITTED);
        documentRepository.save(document);
    }

    @Transactional
    public void uploadVersion(Long docId, String fileName) {
        Document document = documentRepository.findById(docId)
                .orElseThrow(() -> new RuntimeException("Document not found"));

        if (document.isLocked()) {
             // Only allow upload if REVISION_REQUESTED? Or if locked, NO upload.
             // "Disable all update/delete endpoints for that document until the status changes to REVISION_REQUESTED."
             // So if locked and NOT revision requested, throw error.
             if (document.getStatus() != DocumentStatus.REVISION_REQUESTED) {
                 throw new RuntimeException("Cannot upload new version. Document is locked.");
             }
        }
        
        // If status was REVISION_REQUESTED, uploading a new version might reset it to SUBMITTED? 
        // Or just allow upload. Let's assume uploading doesn't auto-submit unless explicitly called.
        // But usually uploading a revision implies addressing the feedback.
        // For now, just handle the version logic.

        DocumentVersion version = new DocumentVersion();
        version.setDocument(document);
        version.setFileName(fileName);
        version.setUploadTimestamp(LocalDateTime.now());
        
        // Check Deadline
        Optional<Deadline> deadlineOpt = deadlineRepository.findByDocumentType(document.getType());
        if (deadlineOpt.isPresent()) {
            if (version.getUploadTimestamp().isAfter(deadlineOpt.get().getDeadlineDate())) {
                version.setLate(true);
            }
        }

        // Set version number
        int nextVersion = document.getVersions().size() + 1;
        version.setVersionNumber(nextVersion);

        documentVersionRepository.save(version);
        
        // If it was REVISION_REQUESTED, maybe unlock it or keep it unlocked until submitted again?
        // "Once a student clicks 'Submit', the isLocked flag... must become true."
        // So uploading keeps it unlocked until 'Submit' is clicked again.
    }

    @Transactional
    public void approveDocument(Long docId) {
        Document document = documentRepository.findById(docId)
                .orElseThrow(() -> new RuntimeException("Document not found"));
        
        document.setStatus(DocumentStatus.APPROVED);
        documentRepository.save(document);
    }

    @Transactional
    public void requestRevision(Long docId) {
        Document document = documentRepository.findById(docId)
                .orElseThrow(() -> new RuntimeException("Document not found"));
        
        document.setStatus(DocumentStatus.REVISION_REQUESTED);
        document.setLocked(false); // Unlock for revision
        documentRepository.save(document);
    }

    @Transactional
    public void deleteDocument(Long docId) {
        Document document = documentRepository.findById(docId)
                .orElseThrow(() -> new RuntimeException("Document not found"));

        if (document.isLocked() && document.getStatus() != DocumentStatus.REVISION_REQUESTED) {
            throw new RuntimeException("Cannot delete a locked document.");
        }

        documentRepository.delete(document);
    }
}
